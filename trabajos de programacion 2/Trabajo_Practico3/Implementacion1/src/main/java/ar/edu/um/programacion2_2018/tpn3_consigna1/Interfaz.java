/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.um.programacion2_2018.tpn3_consigna1;

//import static javafx.scene.input.KeyCode.T;

/**
 *
 * @author arnal
 */
public interface Interfaz<T> {
        void agregar(T elemento);
        T obtener(int posicion);
        void borrar(int posicion);
        void borraTodo();
        
 
    
}
