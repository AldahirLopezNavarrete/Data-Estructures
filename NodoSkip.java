package skipLists;

/**
 *
 * @author aldahir
 */

public class NodoSkip<T extends Comparable <T>>{
    NodoSkip<T> izquierda,derecha,arriba,abajo;
    T elem;
    
    public NodoSkip(T elemento){
        izquierda=null;
        derecha=null;
        arriba=null;
        abajo=null;
        elem=elemento;
    }
    public NodoSkip<T> getIzq(){
        return izquierda;
    }
    public NodoSkip<T> getDer(){
        return derecha;
    }
    public NodoSkip<T> getAbajo(){
        return abajo;
    }
    public NodoSkip<T> getArriba(){
        return arriba;
    }
    public T getElem(){
      return elem;
    }
    public void setIzq(NodoSkip<T> A){
        izquierda=A;
    }
    public void setDer(NodoSkip<T> A){
        derecha=A;
    }
    public void setArriba(NodoSkip<T> A){
        arriba=A;
    }
    public void setAbajo(NodoSkip<T> A){
        abajo=A;
    }
}