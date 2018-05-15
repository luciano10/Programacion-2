/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.um.programacion2_2018.tpn3_consigna2;

/**
 *
 * @author arnal
 */
public class Nodo {
    private int valor;
    private Nodo siguiente;
 
    public void Nodo(){
        this.valor = 0;
        this.siguiente = null;
    }
    
    
    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }   
}