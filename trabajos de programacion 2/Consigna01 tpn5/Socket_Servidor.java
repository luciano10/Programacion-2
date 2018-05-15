/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.um.programacion2_2018.servidor.cliente;

/**
 *
 * @author arnal
 */
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import ar.edu.um.programacion2_2018.servidor.cliente.Conexion;

public class Socket_Servidor extends Conexion //Se hereda de conexión para hacer uso de los sockets y demás
{
	
    public Socket_Servidor() throws IOException{super("servidor");} //Se usa el constructor para servidor de Conexion

    public void startServer()//Método para iniciar el servidor
    {
    	try{
    	
           
			System.out.println("hola cliente ");
			cs = ss.accept(); //Accept comienza el socket y espera una conexión desde un cliente

            System.out.println("Cliente en línea");

    
                ObjectInputStream entrada = new ObjectInputStream (cs.getInputStream()); 
                System.out.println(entrada.readObject());
             

                System.out.println("Datos del servidor de descarga recibido");
            

        }catch(Exception e){}
    }

        
    }
