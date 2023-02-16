package ArbolesBinariosBusqueda;

import arboles.BinaryTreeADT;

/**
 *
 * @author aldahir
 */
public interface BinarySearchTreeADT<T extends Comparable<T>> extends BinaryTreeADT<T>{
    public void add(T elem);
}
