package colas;
import java.util.ArrayList;
import stacks.PilaA;

/**
 *
 * @author aldahir
 * 23 marzo 21
 */

public class ColasQueues {
    
    public static <T> void invierteCola(ColaADT<T> c){
        PilaA<T> p=new PilaA();
        
        while(!c.estaVacia())
            p.push(c.quita());
        
        while(!p.isEmpty())
            c.agrega(p.pop());
    } 
    
    public static <T> void eliminaRepetidos(ColaADT<T> c){
        ArrayList<T> aux=new ArrayList<T>();
        
        while(!c.estaVacia()){
            if(!aux.contains(c.consultaPrimero()))
                aux.add(c.quita());
            else
                c.quita();
        }
        for(int i=0;i<aux.size();i++)
            c.agrega(aux.get(i));
    }
    
    public static <T> void eliminaOcurrencias(ColaADT<T> c,T dato){
        ArrayList<T> aux=new ArrayList<T>();
        
        while(!c.estaVacia()){
            if(c.consultaPrimero().equals(dato))
                c.quita();
            else
                aux.add(c.quita());
        }
        for(int i=0;i<aux.size();i++)
            c.agrega(aux.get(i));
        
    }
    
    //problema 37
    public static void ponElMayorAlFinal(ColaADT<Persona> pasajeros,int j){
        ColaADT<Persona> colaAux=new ColaA();
        Persona aux;
        
        aux=pasajeros.quita();
        for(int i=0;i<j;i++){
            if(aux.calculaEdad()<pasajeros.consultaPrimero().calculaEdad()){
                colaAux.agrega(aux);
                aux=pasajeros.consultaPrimero();
            }
            else
                colaAux.agrega(pasajeros.quita());
        }
        colaAux.agrega(aux);
        
        while(!colaAux.estaVacia()){
            pasajeros.agrega(colaAux.quita());
        } 
    }
    
    public static void ordenaPasajerosPorEdad(ColaADT<Persona> pasajeros){
        for(int i=pasajeros.cuentaElementos()-1;i>1;i--)
            ponElMayorAlFinal(pasajeros,i);
    }

    public static void main(String[] args) {
        ColaADT<String> colores=new ColaA();
        ColaADT<Integer> numeros=new ColaA();
        ColaADT<Double> precios=new ColaA();
        
        colores.agrega("rojo");
        colores.agrega("rojo");
        colores.agrega("azul");
        colores.agrega("blanco");
        colores.agrega("blanco");
        System.out.println("Cola original:\n"+colores.toString());
        invierteCola(colores);
        System.out.println("Cola invertida: \n"+colores.toString());
        
        //numeros.agrega(52);
        invierteCola(numeros);
        System.out.println(numeros.toString());
        
        eliminaRepetidos(colores);
        System.out.println(colores.toString());
        
        precios.agrega(80.5);
        precios.agrega(80.5);
        precios.agrega(50.1);
        precios.agrega(33.2);
        precios.agrega(25.0);
        precios.agrega(80.5);
        eliminaOcurrencias(precios,80.5);
        System.out.println(precios.toString());
        
        
        //ejercicio 37
        ColaADT<Persona> pasajeros=new ColaA();
        pasajeros.agrega(new Persona("aldahir",2002));
        pasajeros.agrega(new Persona("michel",2007));
        pasajeros.agrega(new Persona("stephany",2005));
        pasajeros.agrega(new Persona("gabriel",1975));
        pasajeros.agrega(new Persona("monserrat",1987));

        ordenaPasajerosPorEdad(pasajeros);
        System.out.println(pasajeros.toString());
        
        
        
    }
    
}
