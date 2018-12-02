package cinepago.web.rest;
import cinepago.domain.Pago;
import cinepago.domain.Tarjeta;
import cinepago.repository.ClienteRepository;
import cinepago.repository.PagoRepository;
import cinepago.repository.TarjetaRepository;
import cinepago.web.rest.errors.BadRequestAlertException;
import cinepago.web.rest.util.HeaderUtil;
import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api")
public class ValidacionResourse {
@Autowired
private TarjetaRepository tarjetaRepository;
@Autowired
private ClienteRepository clienteRepository;
@Autowired
private PagoRepository pagoRepository;

private final Logger log = LoggerFactory.getLogger(ValidacionResourse.class);

    @PostMapping("/validacion/{num_tarjeta}/{importe}")
    @Timed
    public String createdPago(@PathVariable String num_tarjeta,@PathVariable BigDecimal importe) throws URISyntaxException {
        log.debug("REST request to save Pago : {}");


        Tarjeta peticion_tarjeta=tarjetaRepository.findByNumero(num_tarjeta);//busacmos y traemos la tarjeta con ese numero

        int saldo_int = peticion_tarjeta.getSaldo().intValue();
        ZonedDateTime fecha_actual2=ZonedDateTime.now();
        final String uuid = UUID.randomUUID().toString();
        String salida="";
        int importe_int = importe.intValue();
         if(saldo_int<importe_int)  {
            salida=null;
         }
         else if(saldo_int>=importe_int)  {//cargamos pago 
             Pago pago=new Pago();
             pago.setImporte(importe);
             pago.setTarjeta(peticion_tarjeta);
             pago.setCreated(fecha_actual2);
             pago.setUpdated(fecha_actual2);
             pago.setPagoUuid(uuid);
             pagoRepository.save(pago);

             BigDecimal resta = new BigDecimal(saldo_int-importe_int);

             peticion_tarjeta.setSaldo(resta);

             tarjetaRepository.save(peticion_tarjeta);
            salida=uuid;
         }

         return salida;


     }


}