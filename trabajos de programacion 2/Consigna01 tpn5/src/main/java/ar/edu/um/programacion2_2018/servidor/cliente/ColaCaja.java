/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.um.programacion2_2018.servidor.cliente;

import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

public class ColaCaja extends Thread{
        
	private Queue<Cliente> clientes = new LinkedList<Cliente>();

	public ColaCaja() { 
		super(																																																																																													);
	}
	
	public Queue<Cliente> getClientes() {
		return clientes;
	}

	public Cliente getCliente() {
		return clientes.poll();
	}

	public void setClientes(Queue<Cliente> clientes) {
		this.clientes = clientes;
	}

	@Override
	public void run() {
		System.out.println("Iniciando Cola");
		try {
			Socket s_cola = new Socket(InetAddress.getByName(null),	1234);
			ObjectOutputStream salida = new ObjectOutputStream(s_cola.getOutputStream());
			while(true) {
        	salida.writeObject(this.getCliente());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
