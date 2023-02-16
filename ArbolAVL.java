package ArbolesBinariosBusqueda;

import arboles.LinkedBinaryTree;
import arboles.NodoBin;

/**
 *
 * @author aldahir
 */
public class ArbolAVL <T extends Comparable<T>> extends LinkedBinaryTree<T>{   
    public ArbolAVL(){
        super();
    }
    
    public ArbolAVL(T elem){
        super(elem);
    }
    
    public void add(T elem){
        NodoBB<T> nuevo = new NodoBB(elem);
        
        if(this.isEmpty())
            raiz = nuevo;
        else
            add(nuevo,(NodoBB<T>) raiz);
        cont++;
    }
    
    private void add(NodoBB<T> nuevo, NodoBB<T> actual){
        if(nuevo.getElem().compareTo(actual.getElem())<=0){
            if(actual.getIzq()==null){
                actual.cuelgaIzq(nuevo);
                balancear(actual);
            }  
            else
                add(nuevo, actual.getIzq());
        }  
        else{
            if(actual.getDer()==null){
                actual.cuelgaDer(nuevo);
                balancear(actual);
            } 
            else
                add(nuevo, actual.getDer());
        }
         
    }
      
    private void balancear(NodoBB<T> actual){;
        if(actual.getPapa() != null){
            while(actual.getPapa()!= null && actual.getFe() != 2 && actual.getFe() != -2){
                System.out.println("entre");
                if(actual.getPapa().getElem() == actual.getPapa().getDer().getElem()){
                    actual.getPapa().incrementaFe();
                    System.out.println("der: "+actual.getPapa().getFe());
                } 
                else{
                    actual.getPapa().decrementaFe();
                    System.out.println(actual.getPapa().getFe());
                }
                    
                actual = actual.getPapa();  
            }
            if(actual.getFe() == Math.abs(2)){
                actual = rotar(actual); 
            }
        }    
    }
    
    public void borra(T elem){
        NodoBB<T> actual = (NodoBB<T>) find(elem, raiz);
        
        if(actual==null)
            return;
        if(actual.getDer()==null && actual.getIzq()==null )//caso hoja 
            actual = eliminaHoja(actual);
        else {
            if(actual.getDer()==null || actual.getIzq()==null)//un solo hijo
                actual = eliminaUnHijo(actual);
            else //dos hijos
                actual = eliminaDosHijos(actual);
        } 
        cont--;
        
        balanceaBorra(actual);
    }
    
    private void balanceaBorra(NodoBB<T> actual){
       while(actual.getPapa()!= null && actual.fe != -1 && actual.fe!= 1 ){
           if(actual.getPapa().getDer() == actual)
               actual.decrementaFe();
           else
               actual.incrementaFe();
           if(actual.fe == Math.abs(2)){
               actual = rotar(actual);
           }
           actual = actual.getPapa();
       } 
    }
    
    //recibe el nodo cuyo |fe| = 2 y regresa la nueva raíz del subárbol
    public NodoBB<T> rotar(NodoBB<T> actual){
        //lo primero a hacer es identificar el tipo de rotación
        NodoBB<T> papa, alfa, beta, gamma, A, B, C, D;
        
        System.out.println("rotacion");
        if(actual.getFe() == -2 && actual.getIzq().getFe() == 1){ // izq-Der
            alfa = actual;
            papa = alfa.getPapa();
            beta = alfa.getIzq();
            gamma = beta.getDer();
            A = beta.getIzq();
            B = gamma.getIzq();
            C = gamma.getDer();
            D = alfa.getDer();
                    
            beta.cuelgaIzq(A);
            beta.cuelgaDer(B);
            alfa.cuelgaIzq(C);
            alfa.cuelgaDer(D);
            gamma.cuelgaIzq(beta);
            gamma.cuelgaDer(alfa);   
            
            if(papa == null)
                raiz = gamma;
            else
                papa.cuelga(gamma);
            
            if(gamma.getFe() == -1){
                alfa.setFe(1);
                beta.setFe(0);
                gamma.setFe(0);
            }
            else{
                if(gamma.getFe() == 1){
                    alfa.setFe(0);
                    beta.setFe(-1);
                    gamma.setFe(0);
                }
                else{ //es cero
                    alfa.setFe(0);
                    beta.setFe(0);
                    gamma.setFe(0);
                }
            }
            
            return gamma;
        }
        else{
            if(actual.getFe() == -2 && (actual.getIzq().getFe() == -1
                || actual.getIzq().getFe() == 0) ){ //Izq-izq
                alfa = actual;
                papa = alfa.getPapa();
                beta = alfa.getIzq();
                gamma = beta.getIzq();
                A = gamma.getIzq();
                B = gamma.getDer();
                C = beta.getDer();
                D = alfa.getDer();
                
                gamma.cuelgaIzq(A);
                gamma.cuelgaDer(B);
                alfa.cuelgaIzq(C);
                alfa.cuelgaDer(D);
                beta.cuelgaIzq(gamma);
                beta.cuelgaDer(alfa);
                
                if(papa == null)
                    raiz = beta;
                else
                    papa.cuelga(beta);
                
                beta.setFe(0);
                gamma.setFe(0);
                alfa.setFe(0);
                
                return beta;
                
            }
            else{
                if(actual.getFe() == 2 && actual.getDer().getFe() == -1){ //Der-izq
                    alfa = actual;
                    papa = alfa.getPapa();
                    beta = alfa.getDer();
                    gamma = beta.getIzq();
                    A = alfa.getIzq();
                    B = gamma.getIzq();
                    C = gamma.getDer();
                    D = beta.getDer();
                    
                    alfa.cuelgaIzq(A);
                    alfa.cuelgaDer(B);
                    beta.cuelgaIzq(C);
                    beta.cuelgaDer(D);
                    gamma.cuelgaIzq(alfa);
                    gamma.cuelgaDer(beta);
                    
                    if(papa == null)
                        raiz = gamma;
                    else
                        papa.cuelga(gamma);
                    
                    if(gamma.getFe() == -1){
                        alfa.setFe(0);
                        beta.setFe(1);
                        gamma.setFe(0);
                    }
                    else{
                        if(gamma.getFe() == 1){
                            alfa.setFe(-1);
                            beta.setFe(0);
                            gamma.setFe(0);
                        }
                        else{ //es cero
                            alfa.setFe(0);
                            beta.setFe(0);
                            gamma.setFe(0);
                        }
                        
                    }
                    return gamma;
                }
                else{ // Der-Der
                    alfa = actual;
                    papa = alfa.getPapa();
                    beta = alfa.getDer();
                    gamma = beta.getDer();
                    A = alfa.getIzq();
                    B = beta.getIzq();
                    C = gamma.getIzq();
                    D = gamma.getDer();
                    
                    alfa.cuelgaIzq(A);
                    alfa.cuelgaDer(B);
                    gamma.cuelgaIzq(C);
                    gamma.cuelgaDer(D);
                    beta.cuelgaIzq(alfa);
                    beta.cuelgaDer(gamma);
                    
                    if(papa == null)
                        raiz = beta;
                    else
                        papa.cuelga(beta);
                    
                    beta.setFe(0);
                    gamma.setFe(0);
                    alfa.setFe(0);
                
                    return beta;      
                }
            }
        }
    }
    
    private NodoBB<T> eliminaHoja(NodoBB<T> actual){
        if(actual==raiz){
            raiz = null;
            return null;  
        }
        
        NodoBB<T> papa = actual.getPapa();
        if(papa.getIzq()== actual)
            papa.setIzq(null);
        else
            papa.setDer(null);

        return actual;
    }

    private NodoBB<T> eliminaUnHijo(NodoBB<T> actual){
        NodoBB<T> hijo;
        
        if(actual.getDer()==null)
            hijo =actual.getIzq();
        else
            hijo = actual.getDer();
        
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
            sucInOrden=sucInOrden.getIzq();
        }   
        
        actual.setElem(sucInOrden.getElem());
        if(actual.getDer() == sucInOrden){ //no avanzó
            actual.setDer(sucInOrden.getDer());
            if(actual.der != null)
                actual.getDer().setPapa(actual);
        }
        else{
            sucInOrden.getPapa().setIzq(sucInOrden.getDer());
            if(sucInOrden.getDer() != null)
                sucInOrden.getDer().setPapa(sucInOrden.getPapa());
        }   
        
        return actual;
    }
}
