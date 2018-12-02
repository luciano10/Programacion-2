package cine.web.rest;
import cine.domain.*;
import cine.repository.*;
import cine.web.rest.errors.BadRequestAlertException;
import cine.web.rest.util.HeaderUtil;
import io.jsonwebtoken.io.IOException;

import com.codahale.metrics.annotation.Timed;
import com.google.errorprone.annotations.FormatString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Null;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
@RequestMapping("/api")
class CineResourse {
    private final Logger log = LoggerFactory.getLogger(FuncionResource.class);
    @Autowired
    private PeliculaRepository peliculaRepository;
    @Autowired
    private FuncionRepository funcionRepository;
    @Autowired
    private SalaRepository salaRepository;
    @Autowired
    private ButacaRepository butacaRepository;
    @Autowired
    private OcupacionRepository ocupacionRepository;
    @Autowired
    private EntradaRepository entradaRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    private static final String ENTITY_NAME = "ocupacion";


    //Metodos Agregados
    @GetMapping("/buscarfuncions/peliculas/{titulo}")//Buscar la Funcion de una Pelicula (Le pasas el titulo de la Pelicula)
    @Timed
    public List<Funcion> getFuncionPelicula(@PathVariable String titulo) {
        log.debug("REST request to get Funcion : {}", titulo);
        Pelicula pelicula = peliculaRepository.findByTitulo(titulo);//buscamos y traemos todas las peliculas con ese titulo

        List<Funcion> funciones = funcionRepository.findAllByPelicula(pelicula);//trae todas las funciones de esta pelicula
        return funciones;
    }

    @GetMapping("/sala/butacas_disponibles/{desc_sala}/{id_funcion}")//Ver las butacas libre de una Sala (Le Pasamos la descripcion de la Sala y la funcion)
    @Timed
    public List<Butaca> getSalaButacas(@PathVariable String desc_sala,@PathVariable Long id_funcion) {
        log.debug("REST request to get Sala : {}", desc_sala);
        Sala sala_desc = salaRepository.findByDescripcion(desc_sala);// traemos la sala con esta descripcion
        Funcion funcion = funcionRepository.findBySalaAndId(sala_desc,id_funcion);// buscamos sala de esa descripcion y la funcion

        List<Ocupacion> ocupacions= ocupacionRepository.findAllByFuncionAndButacaNotNull(funcion);// relacionamos la funcion espeficica con ocupacion 

        Iterable<Long> butacas_id = new ArrayList<>();

        for(int indice = 0;indice<ocupacions.size();indice++)
        {
            ((ArrayList<Long>) butacas_id).add(ocupacions.get(indice).getButaca().getId());
        }

        if(ocupacions == null || ocupacions.isEmpty()){
            return butacaRepository.findAllBySala(sala_desc);// mostramos butacas de esa sala espeficica
        }else   {
            List<Butaca> butacas_disponible = butacaRepository.findAllByIdNotInAndSala(butacas_id,sala_desc);// mostramos aquellas butacas libres donde no tan cargadas en ocupacion
            return butacas_disponible;

        }

    }
    @PostMapping("/reservar/butaca/{id_butacas}/{desc_sala}/{id_funcion}")//Revervar butaca disponible
    @Timed
    public List<Ocupacion> createOcupacion(@PathVariable String id_butacas, @PathVariable String desc_sala, @PathVariable Long id_funcion) throws URISyntaxException   {

        log.debug("REST request to save Ocupacion : {}", id_butacas);
        Sala sala_desc = salaRepository.findByDescripcion(desc_sala);// buscamos la sala por descripcion 
        Funcion funcion= funcionRepository.findBySalaAndId(sala_desc,id_funcion);// buscamos sala por descripcion y funcion 
        Calendar fecha = Calendar.getInstance();
        ZonedDateTime fecha_actual = ZonedDateTime.now();
        String[] butacas = id_butacas.split("-");// parseamos las butacas 
        List<Ocupacion> ocupacions= new ArrayList<>();
        Iterable<Long> butacas_id = new ArrayList<>();

        for(int indice = 0;indice<butacas.length;indice++)
        {
            ((ArrayList<Long>) butacas_id).add(Long.parseLong(butacas[indice]));
        }

        List<Butaca> butacas_disponible = butacaRepository.findAllById(butacas_id);// buscamos esas butacas parseadas de la lista en butcas cargadas 

        for(int indice = 0;indice<butacas_disponible.size();indice++)
        {
            Ocupacion ocupacion=new Ocupacion();
            ocupacion.setButaca(butacas_disponible.get(indice));
            ocupacion.setFuncion(funcion);
            ocupacion.setValor(funcion.getValor());
            ocupacion.setCreated(fecha_actual);
            ocupacion.setUpdated(fecha_actual);
            ocupacions.add(ocupacion);
        }

        List<Ocupacion> result_ocupacion = ocupacionRepository.saveAll(ocupacions);// cargamos ocupacion con esas butacas y las mostramos 

        return result_ocupacion;
    }

    
    @PostMapping("/creartickets/butaca/{id_cliente}/{cant_butaca}/{id_butacas}/{id_tarjeta}")
    @Timed
    public String createTicketWintOcupacion(@PathVariable Long id_cliente,@PathVariable Integer cant_butaca,@PathVariable String id_butacas,@PathVariable String id_tarjeta)throws IOException, java.io.IOException {
        log.debug("REST request to save Ticket : {}", id_butacas);
        LocalDate fecha_actual = LocalDate.now();
        ZonedDateTime fecha_actual2=ZonedDateTime.now();

        String[] butacas = id_butacas.split("-");// aca parseamos con el signo -
        Iterable<Long> butacas_id = new ArrayList<>();
        List<Ocupacion> ocupacions= new ArrayList<>();
        Ticket ticket_cargado= new Ticket();
        for(int indice = 0;indice<butacas.length;indice++)
        {
            ((ArrayList<Long>) butacas_id).add(Long.parseLong(butacas[indice]));
        }

        List<Butaca> butacas_disponible = butacaRepository.findAllById(butacas_id);

        for(int indice = 0;indice<butacas.length;indice++)//recorremos el arreglo y guardamos estas butacas id que fueron parseadas y comparadas con las butacas disponibles
        {
            ocupacions.add(ocupacionRepository.findByButaca(butacas_disponible.get(indice)));
        }
     
       
        
// cargar ticket 
        ticket_cargado.setFechaTransaccion(fecha_actual);
        ticket_cargado.setCreated(fecha_actual2);
        ticket_cargado.setUpdated(fecha_actual2);
        ticket_cargado.setButacas(cant_butaca);
        BigDecimal cant_but = new BigDecimal(cant_butaca);
        BigDecimal cant_valor = ocupacions.get(0).getValor();
        BigDecimal importe;            
        importe = cant_but.multiply(cant_valor);
        // se va a consumir la rest 
        URL url = new URL("http://localhost:9000/api/validacion/"+id_tarjeta+"/"+importe);//your url i.e fetch data from .
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();// definimos la conexion
        conn.setRequestMethod("POST");// metodo post 
        conn.setRequestProperty("Accept", "application/json");// tipo jpson
      conn.setRequestProperty("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOiJST0xFX0FETUlOLFJPTEVfVVNFUiIsImV4cCI6MTU0Mzc4OTc1OX0.Rz62bjL59ee8zi8xgvTijoaT1paPgDBRaKntrvg1MyB2U3D0YfLLZkuwNE8WZBn-Sgn7mRmgOHAMpoSIcaLqLw");
      // aca agregamos la autentificacion del jwt
if (conn.getResponseCode() != 200) {// si no lo recibe larga error
            throw new RuntimeException("Failed : HTTP Error code : "
                + conn.getResponseCode());
        }
        InputStreamReader in = new InputStreamReader(conn.getInputStream());
        BufferedReader br = new BufferedReader(in);// lo que recibe guarda en un buffer
        String output;
        output = br.readLine(); // leemos linea por linea el buffer y se guarda por string
        ticket_cargado.setImporte(importe);
        Optional<Cliente> cliente_ticket=clienteRepository.findById(id_cliente);
        ticket_cargado.setCliente(cliente_ticket.get());
        final String uuid = UUID.randomUUID().toString();
        ticket_cargado.setPagoUuid(output);
        String salida="";
        if(output!= null) {// si es ok lo guarda al tickect
        Ticket result_ticket = ticketRepository.save(ticket_cargado);
        
        for(int indice = 0;indice<butacas.length;indice++)
        {
            ocupacions.get(indice).setTicket(result_ticket);// en ocupacion guardamos y asociamos el ticket
            ocupacionRepository.save(ocupacions.get(indice));
        }
        salida= "operacion completa";
        }
        else {
        	ocupacionRepository.deleteAll(ocupacions);// aca borramos todas aquellas ocupaciones donde tienen relacionada las butacas elegidas 
        	salida ="saldo insuficiente";
        	
        }
        
        return salida ;
        

    }
    
    @PostMapping("/consultar ticket/{id_ticket}")
    @Timed
    public List<Ocupacion> createdTickect(@PathVariable Long id_ticket) {
        log.debug("REST request to get Tickect : {}");
       Optional<Ticket> ticket= ticketRepository.findById(id_ticket);//aca traigo ese tickect con la id
        List<Ocupacion> ocupacions= ocupacionRepository.findAllByTicket(ticket.get());//buscamos ocupacion con ese tickect
		return ocupacions;
		
     
      

    }

    @PostMapping("/cargar_entradas")
    @Timed
    public List<Entrada> createdEntradas() {
        log.debug("REST request to get Entradas : {}");
        ZonedDateTime fecha_actual2=ZonedDateTime.now();
        List<Ocupacion> ocupacions= ocupacionRepository.findAllByTicketNotNullAndEntradaNull();//buscamos id tikect que tan cargados y las entradas null 
        List<Entrada> entradas_acrear = new ArrayList<>();
        for(int indice = 0;indice<ocupacions.size();indice++)
        {
        	//llenamos la entrada
            Entrada entrada = new Entrada();
            entrada.setValor(ocupacions.get(indice).getValor());
            entrada.setUpdated(fecha_actual2);
            entrada.setCreated(fecha_actual2);
            entrada.setDescripcion(ocupacions.get(indice).getButaca().getFila()+"-"+ocupacions.get(indice).getButaca().getNumero()+"__"+ocupacions.get(indice).getFuncion().getSala().getDescripcion());
            entradas_acrear.add(entrada);
        }
        List<Entrada> result_entradas = entradaRepository.saveAll(entradas_acrear);//guardamos las entradas
        for(int indice = 0;indice<ocupacions.size();indice++)
        {
            ocupacions.get(indice).setEntrada(entradas_acrear.get(indice));//aca asociamos a cada elemento de la lista esas entradas cargadas 
        }
        ocupacionRepository.saveAll(ocupacions);//guardamos en ocupacion 

        return result_entradas;

    }
  

}