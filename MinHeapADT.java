/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Heaps;

/**
 *
 * @author aldahir
 */
public interface MinHeapADT<T> {
    public T getMin();
    public T removeMin();
    public void insert(T elem);
}
