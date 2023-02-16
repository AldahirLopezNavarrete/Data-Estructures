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
public class MinHeap <T extends Comparable<T>> implements MinHeapADT<T>{
    int cont;
    T[] datos;
    
    public MinHeap(){
        cont=0;
        datos=(T[])new Object[2];
    }
    
    public T getMin(){
        if(cont==0)
            return null; //o lanzar excepcion
        return datos[1];

    }

    private void expande(){
        T[] datostemp;
        
        if(cont<datos.length)
            return;
        datostemp = (T[])new Object[datos.length*2];
        for(int i=1; i < datos.length;i++)
            datostemp[i]=datos[i];
        datos = datostemp;

    }

    public void insert(T nuevo){
        int posNuevo=0;
        cont++;
        posNuevo=cont;
        int papas;
        
        expande();
        datos[posNuevo]= nuevo;
        papas = cont/2; 
        while(papas>1 && datos[papas].compareTo(nuevo)>0){
            datos[posNuevo]=datos[papas];
            datos[papas]=nuevo;
            posNuevo=papas;
            papas>>=1;
        } 
    }

//    public T removeMin(){
//      if(cont==0)
//        return null;
//
//      T min = datos[1];
//      int iMin, i=1;
//      datos[1] = datos[cont--]; //agarra cont y luego lo decrementa 
//      
//      while(!termine){
//        int hijoIz = i*2;
//        int hijoDer = i*2+1;
//
//        if(hijoDer<=cont){ //tiene dos hijos
//          if(datos[hijoIz].compareTo(datos[hijoDer])<0){ //checa qué hijo es el más chico
//            iMin = hijoIz;
//          } else {
//            iMin = hijoDer;
//          }
//          if(datos[iMin].compareTo(datos[i])<0)){//el hijo es más chico que el papá
//            temp=datos[iMin];
//            datos[iMin]=datos[i];
//            datos[i]=temp;
//            i=iMin;
//          }else termine=true; //el hijo más chico es mayor al papá
//
//        }///fin tiene dos hijos
//
//				else{ if(hijoizq<=cont){//solo tiene un hijo (Izq)
//                if(datos[hijoizq].compareTo(datos[i])<0)){//el hijo es más chico que el papá
//                  temp=datos[hijoizq];
//                  datos[hijoizq]=datos[i];
//                  datos[i]=temp;t
//                  i=hijoizq;
//                } 
//								else termine=true;
//         	 		}
//          		else  termine=true;
//				}
//		}
//		return min;
//	}

    @Override
    public T removeMin() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
		
}

