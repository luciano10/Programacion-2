/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.um.programacion2_2018.tpn3_consigna1;

/**
 *
 * @author arnal
 */
public class App {
public static void main( String[] args )
    {

 Implementacion1<String> imp=new Implementacion1(String.class);
 imp.agregar("pepe");
 imp.agregar("juan");
 imp.agregar("pepe");
 imp.agregar("juan");
 imp.agregar("pepe");
imp.agregar("pepe");
imp.agregar("pepe");
imp.agregar("pepe");
// imp.agregar("pepe");
 
        System.out.println(imp.obtener(0)); 
        System.out.println(imp.obtener(1)); 
        System.out.println(imp.obtener(2)); 
        System.out.println(imp.obtener(3)); 
        System.out.println(imp.obtener(4)); 
        System.out.println(imp.obtener(5)); 
        System.out.println(imp.obtener(6)); 
    System.out.println(imp.obtener(7)); 
imp.borraTodo();
        System.out.println(imp.obtener(0)); 
        System.out.println(imp.obtener(1)); 
        System.out.println(imp.obtener(2)); 
        System.out.println(imp.obtener(3)); 
        System.out.println(imp.obtener(4)); 
        System.out.println(imp.obtener(5)); 
        System.out.println(imp.obtener(6)); 
    System.out.println(imp.obtener(7)); 

    }
}
