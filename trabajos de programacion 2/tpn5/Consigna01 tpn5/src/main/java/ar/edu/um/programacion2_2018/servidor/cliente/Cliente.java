/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.um.programacion2_2018.servidor.cliente;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cliente implements Serializable {
private static final long serialVersionUID = 3924668273990924608L;	
    List<Producto> productos = new ArrayList<Producto>();
	private static int i = 1;
	private int id_cliente;
    private String nombre_cliente;
    
    public Cliente() {
		
	}
    
    public Cliente(List<Producto> prods) {
    	this.productos = prods;
    }
	
    public Cliente(int id_cliente,String nombre_cliente) {
		super();
		this.nombre_cliente=nombre_cliente;
        this.id_cliente= i++;
    }

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	
    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }
	

    public void add(Producto pr) {
		productos.add(pr);
	}
    
	@Override
	public String toString() {
		return "Cliente [productos=" + productos + ", id_cliente=" + id_cliente + ", nombre_cliente=" + nombre_cliente
				+ "]";
	}

	public void procesar() throws InterruptedException {
		System.out.println("Procesando Cliente n°: " + this.id_cliente);
		for (int i = 0; i < this.productos.size(); i++) {
			if(productos.get(i) != null) {
				System.out.println("Producto n:" + i);
				productos.get(i).procesar();
			}else {
				System.out.println("TERMINADO");
			}
		}
	}
}

