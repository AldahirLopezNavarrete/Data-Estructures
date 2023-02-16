package tablashash;

/* Esta clase representa los nodos que almacena la tabla hash.
   Cada nodo se compone de el elemento que guarda y un apuntador
   hacia otro nodo.
*/
public class NodoHash <T> {
    private T elemento;
    NodoHash<T> sig; //apuntador a otro nodo

    public NodoHash(T elem){
        this.elemento = elem;
        this.sig = null;
    }

    public T getElem() {
        return elemento;
    }
    
    public void setElem(T elemento) {
        this.elemento = elemento;
    }
    
    public NodoHash<T> getSig() {
        return sig;
    }
    
    public void setSig(NodoHash<T> sig) {
        this.sig = sig;
    }
}
