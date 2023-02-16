/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arboles;

import java.util.Iterator;

/**
 *
 * @author aldahir
 */
public interface BinaryTreeADT<T>{
    public boolean isEmpty();
    public int size();
    public boolean find(T elem);
    public Iterator<T> preOrden();
    public Iterator<T> inOrden();
    public Iterator<T> postOrden();
    
}
