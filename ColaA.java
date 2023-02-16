package colas;

import java.util.ArrayList;

/**
 *
 * @author aldahir
 */
public class ColaA<T> implements ColaADT<T> {
    private T[] datos;
    private int fin;
    private int frente;
    private final int MAXIMO=20;

    public ColaA() {
        datos=(T[]) new Object[MAXIMO];
        frente=-1;
        fin=-1;
    }
    
    public ColaA(int max) {
        datos=(T[]) new Object[max];
        frente=-1;
        fin=-1;
    }
    
    public void agrega(T nuevo){
        if((fin+1)% datos.length==frente)
            expandeCapacidad();
        fin=(fin+1)%datos.length;
        datos[fin]=nuevo;
        if(frente==-1)
            frente=0;
    }
    
    public T quita(){
        if(estaVacia())
            throw new ExcepcionColeccionVacia("Cola sin elementos");
        
        T resultado=datos[frente];
        datos[frente]=null;
        if(frente==fin){//hay un solo dato en la cola
            frente=-1;
            fin=-1;
        }
        else
            frente=(frente+1)%datos.length;
        return resultado;       
    }
    
    public boolean estaVacia(){
        if(frente==-1 || fin==-1)
            return true;
        else
            return false;
    }
    
    public T consultaPrimero(){
        if(estaVacia())
            throw new ExcepcionColeccionVacia("Cola sin elementos");
        else
            return datos[frente];      
    }
    
    public void expandeCapacidad(){
        T [] nuevo=(T[]) new Object[datos.length*2];
        
        for(int i=0;i<datos.length;i++)
            nuevo[i]=datos[(frente+i)%datos.length]; 
        frente=0; 
    }
    
    @Override
    public String toString(){
        StringBuilder cad=new StringBuilder();
        
        if(!this.estaVacia()){
            int i=frente;
            while(i% datos.length != fin%datos.length){
                cad.append(datos[i% datos.length]+" ");
                i++;
            }
            cad.append(datos[i% datos.length]+"\n");
        }
            
        return cad.toString();
    }
    
    //solución ejercicio 36
    public T consultaUltimo(){
        if(estaVacia())
            throw new ExcepcionColeccionVacia("Cola sin elementos");
        else
            return datos[fin];
    }
    
    public int cuentaElementos(){
        int tot=0;
        
        if(!estaVacia()){
            if(fin>=frente)
                tot=fin-frente+1;
            else
                tot=datos.length-frente+fin+1;
        }
        
       return tot; 
    }
    
    public ArrayList<T> multiQuita(int n){
        ArrayList<T> resultado=new ArrayList();
        
        if(n<=cuentaElementos()){
            for(int i=0;i<n;i++)
                resultado.add(quita());
            return resultado;
        }
        else
            throw new ExcepcionColeccionVacia("Datos insuficientes");
    }
      
}
