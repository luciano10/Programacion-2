/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.um.programacion2_2018.servidor.cliente;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException; 

public class AppServidor {
	
	public AppServidor() {
		
	}
    
	public static void main(String[] args) throws IOException {
        //Socket_Servidor serv = new Socket_Servidor(); 
		ServerSocket s_serv = null;
	
        System.out.println("Iniciando servidor\n");
        //serv.startServer(); //Se inicia el servidor
        
        try {
        	s_serv = new ServerSocket(1234);
        	while(true) {
        		Socket s_Cola = s_serv.accept();
        		System.out.println("Nueva cola aceptada");
        		Cajero tmp = new Cajero(s_Cola);
        		tmp.start();
        	}
        }catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(s_serv != null) {
				s_serv.close();
			}
		}
        
    }
}
