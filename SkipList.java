package skipLists;
import java.lang.Math;

/**
 *
 * @author aldahir
 * @param <T>
 */
public class SkipList<T extends Comparable <T>>{
    NodoSkip<T> cabeza,cola;
    int cont, numNiveles;

    public SkipList(){
        cabeza=new NodoSkip<T>(null);
        cola=new NodoSkip<T>(null);
        ligaID(cabeza,cola);
        cont=0;
        numNiveles=1;
    }

    //asignar derecha e izquierda
    public void ligaID(NodoSkip<T> izquierdo,NodoSkip<T> derecha){
        izquierdo.setDer(derecha);
        derecha.setIzq(izquierdo);
    }
    //arriba getAbajo
    public void ligaArAb(NodoSkip<T> arriba,NodoSkip<T> abajo){
        arriba.setAbajo(abajo);
        abajo.setArriba(arriba);
    }

    public String toString(){
        NodoSkip<T> actual=cabeza;
        String s="";
        
        while(actual.getAbajo()!=null)
            actual=actual.getAbajo();
        actual=actual.getDer();
        while(actual.getElem()!=null){
            s+=actual.getElem()+" ";
            actual=actual.getDer();
        }
        return s; 
    }

    private NodoSkip<T> busca(T elem){
        NodoSkip<T> actual = cabeza;
        boolean termine = false;
        
        while(!termine){
        //te sigues moviendo a la --> hasta que el nodo en el que estás sea menor al que estás buscando
        //Cuando encuentras uno mayor que el elemento se busca bajar
            while(actual.derecha.elem!=null && elem.compareTo(actual.derecha.elem)>=0){
                actual = actual.derecha;
            }
            //bajas
            if(actual.abajo!=null){
                actual = actual.abajo;
            } else {
                termine = true;
            }
        }
        return actual;
    }

    public NodoSkip<T> inserta(T elem){
        int i=1;
        double volado;
        NodoSkip<T> aux = busca(elem);

        NodoSkip<T> nuevo= new NodoSkip<T>(elem);
        nuevo.setIzq(aux);
        nuevo.setDer(aux.getDer());
        aux.getDer().setIzq(nuevo);
        aux.setDer(nuevo);
        //ligaID(nuevo,aux.derecha);
        //ligaID(aux,nuevo);
        cont++;
        volado=Math.random();
        while(volado>.5 && (double)i < 1.0+Math.log(cont)/Math.log(2)){
            if(i>=numNiveles)
                expandeListas();

          while(aux.getArriba()==null && aux.getIzq()!=null)
                aux=aux.getIzq();

            aux=aux.getArriba();
            NodoSkip<T> nuevo2 = new NodoSkip<T>(elem);
            ligaID(nuevo2,aux.derecha);
            ligaID(aux,nuevo2);
            ligaArAb(nuevo2,nuevo);
            nuevo=nuevo2;
            volado=Math.random();
            i++;
      }
        return aux;
    }

    private void expandeListas(){
        NodoSkip<T> cabezaN=new NodoSkip<T>(null);
        NodoSkip<T> colaN=new NodoSkip<T>(null);

        ligaID(cabezaN,colaN);
        ligaArAb(cabezaN, cabeza);
        ligaArAb(colaN, cola);
        this.cabeza=cabezaN;
        this.cola=colaN;
        numNiveles++;
    }
    
    //elimina solo la primera ocurrencia
    public boolean borra(T elem){
        //método busca previamente programado en clase
        NodoSkip<T> aux = busca(elem);
        boolean res;
        
        if(aux.getElem().equals(elem)){
            //sin elementos encima
            if(aux.getArriba() == null){
                ligaID(aux.getIzq(),aux.getDer());
            }
            else{
                //con elementos encima
                ligaID(aux.getIzq(),aux.getDer());
                NodoSkip<T> actual = aux.getArriba();
                
                while(actual != null){
                    ligaID(actual.getIzq(),actual.getDer());
                    actual.getAbajo().setArriba(null);
                    actual.setAbajo(null);
                    actual = actual.getArriba();
                }  
            }
            res = true;
            cont--; //decrementa el número de elementos en la skip list
        }
        else
            res= false;
        
        return res;// devuelve un booleano indicando si lo pudo eliminar o no
    }
}