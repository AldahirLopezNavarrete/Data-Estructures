/* Aldahir lopez
 * 11 nov 2020
 * metodos genericos del manejador matrices 
 */
public class ManejadorMatricesGenerico {
	
	public static <T extends Comparable<T>> int posMayColumna(T[][]mat,int col,int m) {
		int may=0;
		
		for(int i=1;i<m;i++) {
			if(mat[i][col].compareTo(mat[may][col])>0) {
				may=i;
			}	
		}
		return may;
	}
	
	public static <T extends Comparable <T>> int posMinColumna(T[][]mat,int col, int m) {
		int min=0;
		
		for(int i=1;i<m;i++) {
			if(mat[i][col].compareTo(mat[min][col])<0) {
				min=i;
			}
		}
		return min;
	}
	
	
	public static <T extends Comparable<T>> int posMayRenglon(T[][]mat,int ren,int n) {
		int pos;
		
		pos=ManejadorArreglosGenerico.posMayor(mat[ren], n);
		return pos;
	}
	
	public static <T extends Comparable<T>>int posMinRenglon(T[][]mat,int ren,int n) {
		int pos;
		
		pos=ManejadorArreglosGenerico.posMenor(mat[ren], n);
		return pos;
	}
	
	public static <T extends Comparable <T>> int[] posMayorMatriz(T[][]mat,int m,int n) {
		int[]res=new int[2];
		int r1=0;
		int r2=0;
		
		for(int i=0;i<m;i++) { //recorre renglones
			for(int j=0;j<n;j++) {  //recorre columnas
				if(mat[i][j].compareTo(mat[r1][r2])>0) {
					r1=i;
					r2=j;
				}
			}
		}
		res[0]=r1;
		res[1]=r2;
		return res;
	}
	
	public static <T extends Comparable <T>> int[] posMenorMatriz(T[][]mat,int m,int n) {
		int[]res=new int[2];
		int r1=0;
		int r2=0;
		
		for(int i=0;i<m;i++) { //recorre renglones
			for(int j=0;j<n;j++) {  //recorre columnas
				if(mat[i][j].compareTo(mat[r1][r2])<0) {
					r1=i;
					r2=j;
				}
			}
		}
		res[0]=r1;
		res[1]=r2;
		return res;
	}
	
	public static <T> boolean esSimetrica(T[][]mat,int m) {
		boolean res=true;
		int i,j;
		
		i=0;
		while(res && i<m) {// recorre renglones
			j=0;
			while(res && j<m  ) {// recorre columnas
				if(i!=j) {
					if(!mat[i][j].equals(mat[j][i])) 
						res=false;
				}
				j++;
			}
			i++;
		}
		
		return res;
	}
	
	public static <T> int buscaElementoRenglon(T[][]mat,int ren, int n,T valor) {
		int pos;
		
		pos=ManejadorArreglosGenerico.busquedaSecuenciaDesordenada(mat[ren], n, valor);	
		return pos;
	}
	
	public static <T> int buscaElementoColumna(T[][]mat, int m, int col, double valor) {
		int pos,i;
		boolean res=true;
		
		i=0;
		while(i<m && res) {
			if(mat[i][col].equals(valor)){
				res=false;
			}
			i++;
		}
		if(!res)
			pos=i-1;
		else
			pos=-1;
		
		return  pos;
	}
	
	public static <T> int[] buscaEnMatriz(T[][]mat,int m,int n,T valor) {
		int []pos=new int[2];
		int i,j;
		boolean exit=true;
		
		pos[0]=-1;
		pos[1]=-1;
		i=0;
		while(i<m && exit) {
			j=0;
			while(j<n && exit) {
				if(mat[i][j].equals(valor)) {
					exit=false;
					pos[0]=i;
					pos[1]=j;
				}
				j++;
			}
		i++;
		}
		return pos;
	}
	
	public static <T> boolean matricesIguales(T[][]matA,int mA, int nA, T[][] matB, int mB,int nB) {
		boolean res=false;
		int i,j;
		
		if(mA==mB && nA==nB && matA.getClass()==matB.getClass()) {
			i=0;
			res=true;
			while(i<mA && res) {
				j=0;
				while(j<nA && res) {
					if(!matA[i][j].equals(matB[i][j])) {
						res=false;
					}	
					j++;
				}
				i++;
			}
		}
		
		return res;
	}
}