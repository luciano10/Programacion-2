/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.um.programacion2_2018.servidor.cliente;

import java.io.Serializable;

/**
 *
 * @author arnal
 */
public class Producto implements Serializable {
	private String nombre;
	private double precio;
	int min=100;
        int max=500;
        int tiempo = (int) (Math.random() * max) + min;
	
	public Producto() {
		super();
	}
	
	public Producto(String nombre, double precio) {
		super();
		this.nombre = nombre;
		this.precio = precio;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public double getPrecio() {
		return precio;
	}
	
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	
	@Override
	public String toString() {
		return "Producto [nombre=" + nombre + ", precio=" + precio + ", min=" + min + ", max=" + max + ", tiempo="
				+ tiempo + "]";
	}

	public void procesar() throws InterruptedException {
		System.out.println("Procesando: " + this.nombre + "->Demora: " + this.tiempo);
		Thread.sleep(tiempo);
	}
	
	
}
