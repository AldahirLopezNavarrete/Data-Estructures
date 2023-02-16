package ArbolesBinariosBusqueda;

import ArbolesBinariosBusqueda.BinarySearchTreeADT;
import arboles.LinkedBinaryTree;
import arboles.NodoBin;

/**
 *
 * @author aldahir
 */
public class LinkedBinarySearchTree <T extends Comparable<T>> extends LinkedBinaryTree<T> implements BinarySearchTreeADT<T>{
    public int numCompAdd, numCompB;

    public LinkedBinarySearchTree(){
        super();
        numCompAdd=0;
        numCompB=0;
    }
    
    public LinkedBinarySearchTree(T elem){
        super(elem);
        numCompAdd=0;
        numCompB=0;
    }
    
    public NodoBB<T> encuentraMin(){
        if(raiz != null)
            return encuentraMin((NodoBB<T>)raiz);
        else
            return null;            
    }
    
    private NodoBB<T> encuentraMin(NodoBB<T> actual){
        if(actual.getIzq() == null){
            return actual;
        }
        else
            return encuentraMin(actual.getIzq());      
    }
    
    @Override
    public void add(T elem){
        NodoBB<T> nuevo = new NodoBB(elem);
        
        numCompAdd++; 
        if(this.isEmpty())
            raiz = nuevo;
        else
            add(nuevo,(NodoBB<T>) raiz);
        cont++;
    }
    
    private void add(NodoBB<T> nuevo, NodoBB<T> actual){
        numCompAdd++;
        if(nuevo.getElem().compareTo(actual.getElem())<=0){
            numCompAdd++;
            if(actual.getIzq()==null)
                actual.cuelga(nuevo);
            else
                add(nuevo, actual.getIzq());
        }  
        else{
            numCompAdd++;
            if(actual.getDer()==null)
                actual.cuelga(nuevo);
            else
                add(nuevo, actual.getDer());
        }
            
    }
    
    public void borra(T elem){
        NodoBB<T> actual = (NodoBB<T>) find(elem, raiz);
        
        numCompB++;
        if(actual==null)
            return;
        numCompB++;
        if(actual.getDer()==null && actual.getIzq()==null )//caso hoja 
            actual = eliminaHoja(actual);
        else {
            numCompB++;
            if(actual.getDer()==null || actual.getIzq()==null)//un solo hijo
                actual = eliminaUnHijo(actual);
            else //dos hijos
                actual = eliminaDosHijos(actual);
        } 
        cont--;
        
    }
    
    public boolean find(T elem){
        return find(elem,raiz)!=null;
    }
    
    public NodoBin<T> find (T elem, NodoBin<T> pos){
        numCompB++;
        if(pos == null)
            return null;
        numCompB++;
        if(pos.getElem().equals(elem))
            return pos;
        
        NodoBin<T> temp = find(elem, pos.getIzq());
        numCompB++;
        if(temp == null)
            temp = find(elem, pos.getDer());
        
        return temp;
    }

    private NodoBB<T> eliminaHoja(NodoBB<T> actual){
        numCompB++;
        if(actual==raiz){
            raiz = null;
            return null;  
        }
        
        NodoBB<T> papa = actual.getPapa();
        numCompB++;
        if(papa.getIzq()== actual)
            papa.setIzq(null);
        else
            papa.setDer(null);

        return actual;
    }

    private NodoBB<T> eliminaUnHijo(NodoBB<T> actual){
        NodoBB<T> hijo;
        
        numCompB++;
        if(actual.getDer()==null)
            hijo =actual.getIzq();
        else
            hijo = actual.getDer();
        
        numCompB++;
        if(actual==raiz){
            raiz = hijo;
            hijo.papa = null;
        }  
        else
            actual.getPapa().cuelga(hijo);

        return hijo;
    }
    
    private NodoBB<T> eliminaDosHijos(NodoBB<T> actual){	
        NodoBB<T> sucInOrden = actual.getDer(); //sucesor in orden

        while(sucInOrden.getIzq()!=null){
            numCompB++;
            sucInOrden=sucInOrden.getIzq();
        }   
        
        actual.setElem(sucInOrden.getElem());
        numCompB++;
        if(actual.getDer() == sucInOrden){ //no avanzó
            actual.setDer(sucInOrden.getDer());
            numCompB++;
            if(actual.der != null)
                actual.getDer().setPapa(actual);
        }
        else{
            sucInOrden.getPapa().setIzq(sucInOrden.getDer());
            numCompB++;
            if(sucInOrden.getDer() != null)
                sucInOrden.getDer().setPapa(sucInOrden.getPapa());
        }   
        
        return actual;
    }
}
