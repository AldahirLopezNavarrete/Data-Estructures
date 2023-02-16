import java.util.ArrayList;
/*Aldahir
 * 23 oct 2020
 * creacion metodos genericos con arreglos...
 */

public class ManejadorArreglosGenerico {
	public static <T extends Comparable<T>> int posMenor(T[]a,int n) {
		T min=a[0];
		int posMin=0;
		
		for(int i=1;i<n;i++) {
			if(a[i].compareTo(min)<0) {
				min=a[i];
				posMin=i;
			}	
		}
		return posMin;
	}
	
	//metodos funcionan con arreglos de tipo clase, nunca primitivo
	public static <T extends Comparable<T>> int posMayor(T[]a,int n) {
		T may=a[0];  //extends HEREDA interfaz comparable 
		int posMay=0;
		
		for(int i=1;i<n;i++) {
			if(a[i].compareTo(may)>0) {
				may=a[i];
				posMay=i;
			}
		}
		return posMay;
	}
	
	public static <T extends Comparable<T>> int cuantosMayX(T[]a,int n, T x) {
		int cont=0;
		
		for(int i=0;i<n;i++) {
			if(a[i].compareTo(x)>0)
				cont++;
		}
		return cont;
	}
	
	public static <T extends Comparable<T>> int cuantosMenX(T[]a,int n,T x) {
		int cont=0;
	
		for(int i=0;i<n;i++) {
			if(a[i].compareTo(x)<0)
				cont++;
		}
		return cont;
	}
	
	public static <T extends Comparable<T>> ArrayList<Integer> cualesMayX(T[]a,int n,T x){
		ArrayList<Integer> lista=new ArrayList<Integer>();
		
		for(int i=0;i<n;i++) {
			if(a[i].compareTo(x)>0) {
				lista.add(i);
			}
		}
		return lista;
		
	}
	
	public static <T extends Comparable<T>> ArrayList<Integer> cualesMenX(T[]a,int n,T x){
		ArrayList<Integer>lista=new ArrayList<Integer>();
		
		for(int i=0;i<n;i++) {
			if(a[i].compareTo(x)<0) {
				lista.add(i);
			}
		}
		return lista;
	}
	
	public static <T> void swap(T[]a,int posX,int posY) {
		T aux;
		
		aux=a[posX];
		a[posX]=a[posY];
		a[posY]=aux;
	}
	
	public static <T> void invierte(T[]a,int n) {
		for(int i=0;i<n/2;i++) {
			swap(a,i,n-i-1);
		}
	}
	
	public static <T extends Comparable<T>> int posMenor(T[]a,int n,int inicio) {
		int posMin=inicio;
		
		for(int i=inicio+1;i<n;i++) {
			if(a[posMin].compareTo(a[i])>0) {
				posMin=i;	
			}
		}
		return posMin;
	}
	
	public static <T extends Comparable<T>> void seleccionDirecta(T[]a,int n) {
		int min; //este metodo ordena de menor a mayor un arreglo
		
		for(int i=0;i<n-1;i++) {
			min=posMenor(a,n,i);
			swap(a,min,i);
		}
	}
	
	public static <T extends Comparable<T>> int busquedaSecuenciaOrdenada(T[]a,int n,T valor) {
		int i=0;
		
		while(i<n && a[i].compareTo(valor)<0) {
			i++;
		}
		if(i==n || !a[i].equals(valor))
			i=-i-1;
		return i;
	}
	
	public static <T> int busquedaSecuenciaDesordenada(T[]a,int n,T valor) {
		int i=0;
		
		while(i<n && !a[i].equals(valor)) {
			i++;
		}
		if(i==n)
			i=-1;
		return i;
	}
	
	public static <T extends Comparable<T>> int busquedaBinaria(T[]a,int n, T valor ) {
		int pos;
		int inicio=0;
		int fin= n-1;
		int mitad=(inicio+fin)/2;;
		
		while(inicio<=fin && a[mitad]!=valor) {
			if(valor.compareTo(a[mitad])<0)
				fin=mitad-1;
			else
				inicio=mitad+1;
			mitad=(inicio+fin)/2;
		}
		if(inicio>fin )//elemento no encontrado	
			pos=mitad;
		else
			pos=-inicio-1;

		return pos;
	}
	
	public static <T> void unCorrimientoDer(T[]a,int n,int pos) {
		for(int i=n;i>pos;i--) {
			a[i]=a[i-1];
		}
	}
	
	public static <T> void unCorrimientoIzq(T[]a,int n, int inicio) {
		for(int i=inicio;i<n-1;i++) {
			a[i]=a[i+1];
		}
	}
	
	public static <T> int inserta(T[]a,int n,int pos, T valor) {
		if(n<a.length) {
			unCorrimientoDer(a,n,pos);
			a[pos]=valor;
			n++;
		}
		return n;
	}
	
	public static <T extends Comparable<T>> int insertaEnOrden(T[]a,int n, T valor) {
		int pos;
		
		if(n<a.length) {          //cabe
			pos=busquedaSecuenciaOrdenada(a,n,valor);
			
			if(pos<0) {           //no existe
				pos=-pos-1;
				unCorrimientoDer(a,n,pos);
				a[pos]=valor;
				n++;
			}
		}
		return n;
	}
	
	public static <T> int insertaAlFinal(T[]a,int n,T valor) {
		int nn;
		
		nn=inserta(a,n,n,valor);
		return nn;
	}
	
	public static <T> int insertaAlInicio(T[]a,int n,T valor) {
		return inserta(a,n,0,valor);
	}
	
	public static <T> int elimina(T[]a,int n, int pos) {
		unCorrimientoIzq(a,n,pos);
		n--;
		a[n]=null;             
		return n;
	}
	
	public static<T extends Comparable<T>> int eliminaEnOrdenado(T[]a, int n, T valor) {
		int pos;
		
		pos=busquedaSecuenciaOrdenada(a,n,valor);
		if(pos>=0) {        //lo encontro 
			n=elimina(a,n,pos);
		}
		return n;
	}
	
	public static <T> int eliminaEnDesordenado(T[]a, int n, T valor) {
		int pos;
		
		pos=busquedaSecuenciaDesordenada(a,n,valor);
		if(pos>=0) {        //lo encontro 
			n=elimina(a,n,pos);
		}
		return n;
	}
}
