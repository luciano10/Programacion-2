/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.um.programacion2_2018.servidor.cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.rmi.UnknownHostException;

public class Cajero extends Thread{
	private Socket s_Cola;
	private static int i = 1;
	private int numCaja;
	ObjectInputStream entrada;

	
	public Cajero() {
		
	}
	
	public Cajero(Socket s_Cola) {
		super();
		numCaja = i++;
		this.s_Cola = s_Cola;
	}

	public void run(){
		try {
			System.out.println("-----------Inicia cajero " + this.numCaja + "--------------");
			ObjectInputStream entrada = new ObjectInputStream (s_Cola.getInputStream());
			while (true) {
				Cliente cli;
				cli = (Cliente)entrada.readObject();
				try {
					System.out.println("CAJERO: " + this.numCaja);
					cli.procesar();
				}catch (NullPointerException e) {
					System.out.println("----------Caja " + this.numCaja + " vacia ---------");
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
