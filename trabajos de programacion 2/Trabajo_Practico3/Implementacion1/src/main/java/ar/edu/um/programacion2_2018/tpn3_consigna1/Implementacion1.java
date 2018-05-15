/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.um.programacion2_2018.tpn3_consigna1;

import java.lang.reflect.Array;
//import java.util.ArrayList;
//import java.util.List;

/**
 *
 * @author arnal
 */
public class Implementacion1<T> implements Interfaz<T> {
//    List<T> arreglo = new ArrayList<T>();
  //  List<T> arreglo2 = new ArrayList<T>();
  public Implementacion1(Class<T> clazz) {
      T[] array = (T[]) Array.newInstance(clazz, 5);
}  
  int posicion;
 Object[] arreglo=(T[]) new Object[5];

 public T obtener(int indice) {
        //agrega validaciones correspondientes
        return (T) arreglo[indice];
    }
    @Override
    public void agregar(T elemento) {
arreglo[posicion]=elemento;
posicion++;
        try
		         {
arreglo[arreglo.length]=elemento;
                         }
		         catch(ArrayIndexOutOfBoundsException excepcion)
		         {
	T auxiliar[]=(T[]) arreglo;
        arreglo=arreglo=(T[]) new Object[posicion+5];
         for(int i=0;i<auxiliar.length;i++){
         arreglo[i]=auxiliar[i];
         }                
                         }
    



}
    
    @Override
    public void borrar(int posicion) {
   arreglo[posicion]=null;
    }

    @Override
    public void borraTodo() {
   for(int i=0;i<arreglo.length;i++){
arreglo[i]=null;
   }
    }
    }


     	

