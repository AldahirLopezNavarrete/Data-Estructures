package colas;

import java.util.ArrayList;

/**
 *
 * @author aldahir
 */
public interface ColaADT <T> {
    public void agrega(T nuevo);
    public T quita();
    public T consultaPrimero();
    public boolean estaVacia();
    public String toString();
    //ejercicio 36
    public int cuentaElementos();
    public T consultaUltimo();
    public ArrayList<T> multiQuita(int n);
    
}
