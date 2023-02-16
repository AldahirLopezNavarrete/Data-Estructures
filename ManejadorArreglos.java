import java.util.ArrayList;
/*Aldahir Lopez
 * 28 sept 2020
 * Clase contenedora de metodos éstaticos que manipulan arreglos
 */
public class ManejadorArreglos {

	public static double suma(double[]a,int n) {
		double sum;
		
		sum=0;
		for(int i=0;i<n;i++)
			sum+=a[i];
		return sum;
	}
	
	public static double promedio(double[]a,int n) {
		double prom=0;
		
		prom=suma(a,n);
		return prom/n;
	}
	
	public static int posMayor(double[]a,int n) {
		double may=a[0];
		int posMay=0;
		
		for(int i=1;i<n;i++) {
			if(a[i]>may) {
				may=a[i];
				posMay=i;
			}	
		}
		return posMay;
	}
	
	public static int posMenor(double[]a,int n) {
		double min=a[0];
		int posMin=0;
		
		for(int i=1;i<n;i++) {
			if(a[i]<min) {
				min=a[i];
				posMin=i;
			}	
		}
		return posMin;
	}
	
	
	public static int cuantosMayX(double[]a,int n, double x) {
		int cont=0;
		
		for(int i=0;i<n;i++) {
			if(a[i]>x)
				cont++;
		}
		return cont;
	}
	
	public static int cuantosMenX(double[]a,int n,double x) {
		int cont=0;
	
		for(int i=0;i<n;i++) {
			if(a[i]<x)
				cont++;
		}
		return cont;
	}
	
	public static ArrayList<Integer> cualesMayX(double[]a,int n,double x){
		ArrayList<Integer> lista=new ArrayList<Integer>();
		
		for(int i=0;i<n;i++) {
			if(a[i]>x) {
				lista.add(i);
			}
		}
		return lista;
		
	}
	
	public static ArrayList<Integer> cualesMenX(double[]a,int n,double x){
		ArrayList<Integer>lista=new ArrayList<Integer>();
		
		for(int i=0;i<n;i++) {
			if(a[i]<x) {
				lista.add(i);
			}
		}
		return lista;
	}
	
	public static void swap(double[]a,int posX,int posY) {
		double aux;
		
		aux=a[posX];
		a[posX]=a[posY];
		a[posY]=aux;
	}
	
	public static void invierte(double[]a,int n) {
		for(int i=0;i<n/2;i++) {
			swap(a,i,n-i-1);
		}
	}
	
	public static void kCorrimientosDer(double[]a,int n,int k) { 
		for(int i=1;i<=k;i++) {
			for(int j=n-1;j>0;j--) {
				a[j]=a[j-1];
			}
			a[0]=0;
		}
	}
	
	public static void kCorrimientosIzq(double[]a,int n,int k) {
		for(int i=1;i<=k;i++) {
			for(int j=0;j<n-1;j++) {
				a[j]=a[j+1];
			}
			a[n-1]=0;
		}
	}
	
	public static int posMenor(double[]a,int n, int inicio) {
		int posMin=inicio;
		
		for(int i=inicio+1;i<n;i++) {
			if(a[posMin]>a[i]) {
				posMin=i;	
			}
		}
		return posMin;
	}
	
	public static void seleccionDirecta(double[]a,int n) {
		int min; //este metodo ordena de menor a mayor un arreglo
		
		for(int i=0;i<n-1;i++) {
			min=posMenor(a,n,i);
			swap(a,min,i);
		}
		
	}
	
	public static int busquedaSecuenciaDesordenada(double[]a,int n,double valor) {
		int i=0;
		
		while(i<n && a[i]!=valor) {
			i++;
		}
		if(i==n)
			i=-1;
		return i;
	}
	
	public static int busquedaSecuenciaOrdenada(double[]a,int n,double valor) {
		int i=0;
		
		while(i<n && a[i]<valor) {
			i++;
		}
		if(i==n || a[i]!=valor)
			i=-i-1;
		return i;
	}
	
	public static int busquedaBinaria(double[]a,int n, double valor ) {
		int pos;
		int inicio=0;
		int fin= n-1;
		int mitad=(inicio+fin)/2;;
		
		while(inicio<=fin && a[mitad]!=valor) {
			if(valor<a[mitad])
				fin=mitad-1;
			else
				inicio=mitad+1;
			mitad=(inicio+fin)/2;
		}
		if(inicio>fin )//elemento  encontrado	
			pos=mitad;
		else
			pos=-inicio-1;

		return pos;
	}
	
	public static void unCorrimientoDer(double[]a,int n,int pos) {
		for(int i=n;i>pos;i--) {
			a[i]=a[i-1];
		}
		a[pos]=0;
	}
	
	public static void unCorrimientoIzq(double[]a,int n, int inicio) {
		for(int i=inicio;i<n-1;i++) {
			a[i]=a[i+1];
		}
		a[n-1]=0;
	}
	
	public static int inserta(double[]a,int n,int pos, double valor) {
		if(n<a.length) {
			unCorrimientoDer(a,n,pos);
			a[pos]=valor;
			n++;
		}
		return n;
	}
	
	public static int insertaEnOrden(double[]a,int n, double valor) {
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
	
	public static int insertaAlFinal(double[]a,int n,double valor) {
		int nn;
		
		nn=inserta(a,n,n,valor);
		return nn;
	}
	
	public static int insertaAlInicio(double[]a,int n,double valor) {
		return inserta(a,n,0,valor);
	}
	
	public static int elimina(double[]a,int n, int pos) {
		unCorrimientoIzq(a,n,pos);
		n--;
		a[n]=0;             //deberia ser null
		return n;
	}
	
	public static int eliminaEnOrdenado(double[]a, int n, double valor) {
		int pos;
		
		pos=busquedaSecuenciaOrdenada(a,n,valor);
		if(pos>=0) {        //lo encontro 
			n=elimina(a,n,pos);
		}
		return n;
	}
	
	public static int eliminaEnDesordenado(double[]a, int n, double valor) {
		int pos;
		
		pos=busquedaSecuenciaDesordenada(a,n,valor);
		if(pos>=0) {        //lo encontro 
			n=elimina(a,n,pos);
		}
		return n;
	}
	
}
