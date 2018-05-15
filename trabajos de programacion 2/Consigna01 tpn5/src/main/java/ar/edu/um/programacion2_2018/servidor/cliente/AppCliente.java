/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.um.programacion2_2018.servidor.cliente;

import java.io.IOException;

/**
 *
 * @author arnal
 */

public class AppCliente {
	//private Persona per1= new Persona ("pepe",2);
    public static void main(String[] args) throws IOException{  
        System.out.println("Inicia el cliente");
    	
    	Producto pr1 = new Producto("Arroz",40);
		Producto pr2 = new Producto("Carne",120);
		Producto pr3 = new Producto("Gaseosa",60);
		Producto pr4 = new Producto("Galletas",20);
				
		
		//CLIENTES
		Cliente cl1_1 = new Cliente(1,"Pepe");
		cl1_1.add(pr1);
        
		Cliente cl2 = new Cliente(2,"Carlos");
		cl2.add(pr2);
		
		Cliente cl3 = new Cliente(3,"Jose");
		cl3.add(pr4);
	
		
		ColaCaja cola = new ColaCaja();
		ColaCaja cola1 = new ColaCaja();
		ColaCaja cola2 = new ColaCaja();
		
        cola.getClientes().add(cl1_1);
        cola1.getClientes().add(cl2);
         
        cola2.getClientes().add(cl3);
     
       
        
            cola1.start();
        cola2.start();
        cola.start();
    
       
        
         

        
    }
   
}