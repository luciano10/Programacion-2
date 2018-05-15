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
public class Lista {
    
    private Nodo raiz;
    
    public Lista() {
        raiz=null;
    }
      
    void insertar(int x)
    {
        Nodo nuevo = new Nodo ();
        nuevo.setValor(x);
        if (raiz==null) {
            raiz=nuevo;
        } else {
            if (x<raiz.getValor()) {
                nuevo.setSiguiente(raiz);
                raiz=nuevo;
            } else {
                Nodo reco=raiz;
                Nodo atras=raiz;
                while (x>=reco.getValor() && reco.getSiguiente()!=null) {
                    atras=reco;
                    reco=reco.getSiguiente();
                }
                if (x>=reco.getValor()) {
                    reco.setSiguiente(nuevo);
                } else {
                    nuevo.setSiguiente(reco);
                    atras.setSiguiente(nuevo);
                }
            }
        }
    }

    public void imprimir () {
        Nodo reco = raiz;
        while (reco != null) {
            System.out.print (reco.getValor() +"  ");
            reco = reco.getSiguiente();
        }
        System.out.println();
    }
        
    
    
}

