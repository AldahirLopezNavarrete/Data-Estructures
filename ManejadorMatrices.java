/*Aldahir lopez
 * 28 cot 2020
 * metodos estaticos para manipular matrices de double
 */
public class ManejadorMatrices {
	//  m renglones , n columnas
	
	//a) Sumar los elementos de una columna de una matriz de double.
	public static double sumaCol(double[][]mat,int col,int m) { 
		double sum=0;
		
		for(int i=0;i<m;i++) {
			sum+=mat[i][col];
		}
		return sum;
	}
	
	//b) Sumar los elementos de un renglón de una matriz de double.
	public static double sumaRen(double[][]mat,int ren, int n){
		double sum=0;
		
		for(int i=0;i<n;i++) {
			sum+=mat[ren][i];
		}
		//sum=ManejadorArreglos.suma(mat[ren],n);
		return sum;
	}
	
	//c) Sumar todos los elementos de una matriz de double.
	public static double sumaElementosMatriz(double[][]mat,int m,int n) {
		double sum=0;
		
		for(int i=0;i<m;i++) {  //recorre renglones
			//sum+=ManejadorArreglos.suma(mat[i],n);
			for(int j=0;j<n;j++) {  //recorre columnas
				sum+=mat[i][j];
			}
		}
		return sum;
	}
	
	//d) Sumar los elementos de la diagonal principal de una matriz de double.
	public static double sumaElementosDiagonalPrincipal(double[][]mat,int m) {
		double sum=0;
		
		//se asume que la matriz es cuadrada...
		for(int i=0;i<m;i++) {
			sum+=mat[i][i];
		}
		
		return sum;
	}
	
	//e) Sumar los elementos de la diagonal secundaria de una matriz de double.
	public static double sumaElementosDiagonalSecundaria(double[][]mat,int m) {
		double sum=0;
		int j=m-1;
		
		//se asume que la matriz es cuadrada
		for(int i=0;i<m;i++) {
			sum+=mat[i][j];
			j--;
		}
		return sum;
	}
	
	// g) Encontrar el elemento más grande/pequeño de una columna de un arreglo
	// bidimensional de double y regresar la posición del mismo.
	
	public static int posMayColumna(double[][]mat,int col,int m) {
		int may=0;
		
		for(int i=1;i<m;i++) {
			if(mat[i][col]>mat[may][col]) {
				may=i;
			}	
		}
		return may;
	}
	
	public static int posMinColumna(double[][]mat,int col, int m) {
		int min=0;
		
		for(int i=1;i<m;i++) {
			if(mat[i][col]<mat[min][col]) {
				min=i;
			}
		}
		return min;
	}
	
	// h) Encontrar el elemento más grande/pequeño de un renglón de un arreglo
	// bidimensional de double y regresar la posición del mismo.
	
	public static int posMayRenglon(double[][]mat,int ren,int n) {
		int pos;
		
		pos=ManejadorArreglos.posMayor(mat[ren], n);
		return pos;
	}
	
	public static int posMinRenglon(double[][]mat,int ren,int n) {
		int pos;
		
		pos=ManejadorArreglos.posMenor(mat[ren], n);
		return pos;
	}
	
	public static int[] posMayorMatriz(double[][]mat,int m,int n) {
		int[]res=new int[2];
		int r1=0;
		int r2=0;
		
		for(int i=0;i<m;i++) { //recorre renglones
			for(int j=0;j<n;j++) {  //recorre columnas
				if(mat[i][j]>mat[r1][r2]) {
					r1=i;
					r2=j;
				}
			}
		}
		res[0]=r1;
		res[1]=r2;
		return res;
	}
	
	public static int[] posMenorMatriz(double[][]mat,int m,int n) {
		int[]res=new int[2];
		int r1=0;
		int r2=0;
		
		for(int i=0;i<m;i++) { //recorre renglones
			for(int j=0;j<n;j++) {  //recorre columnas
				if(mat[i][j]<mat[r1][r2]) {
					r1=i;
					r2=j;
				}
			}
		}
		res[0]=r1;
		res[1]=r2;
		return res;
	}
	
	
	public static double[][] sumaMatrices(double[][]mat1,int m1,int n1, double[][]mat2,int m2,int n2){
		double [][]res=null;
		
		if(m1==m2 && n1==n2) {
			res=new double[m1][n1];
			for(int i=0;i<m1;i++) {
				for(int j=0;j<n1;j++) {
					res[i][j]=mat1[i][j]+mat2[i][j];
				}
			}
		}
		
		return res;
	}
	
	public static double[][] generaTraspuesta(double[][]mat,int m,int n){
		double[][]res=new double[n][m];
		
		for(int i=0;i<m;i++) { //recorre renglones
			for(int j=0;j<n;j++) {  //recorre columnas
				res[j][i]=mat[i][j];
			}
		}
		return res;
	}
	
	public static boolean esIdentidad(double[][]mat,int m) {
		boolean res=true;
		int i,j;
		
		i=0;
		//la matriz es cuadrada 
		while(res && i<m) { //recorre renglones 
			j=0;
			while(res && j<m) {// recorre columnas 
				if(j!=i) {
					if(mat[i][j]!=0)
						res=false;
				}
				else
					if(mat[i][j]!=1)
						res=false;
				j++;
			}
			i++;
		}
		return res;
	}
	
	public static boolean esSimetrica(double[][]mat,int m) {
		boolean res=true;
		int i,j;
		
		i=0;
		while(res && i<m) {// recorre renglones
			j=0;
			while(res && j<m  ) {// recorre columnas
				if(i!=j) {
					if(mat[i][j]!=mat[j][i]) 
						res=false;
				}
				j++;
			}
			i++;
		}
		
		return res;
	}
	
	public static double[][] multiplicacionMatrices(double[][]a,int m,int n,double[][]b,int mB,int p) {
		double [][]res=null;
		
		if(n==mB) {   //columnas de a == renglones de b??
			res=new double[m][p];
			for(int j=0;j<m;j++) //recorre renglones nueva matriz 
				for(int i=0;i<n;i++)  //recorre columnas de a y renglones de b
					for(int k=0;k<p;k++)  //recorre columnas matriz resultado
						res[j][k]+=a[j][i] * b[i][k];;
		}
		
		return res;
	}
	
	public static int buscaElementoRenglon(double[][]mat,int ren, int n, double valor) {
		int pos;
		
		pos=ManejadorArreglos.busquedaSecuenciaDesordenada(mat[ren], n, valor);	
		return pos;
	}
	
	public static int buscaElementoColumna(double[][]mat, int m, int col, double valor) {
		int pos,i;
		boolean res=true;
		
		i=0;
		while(i<m && res) {
			if(mat[i][col]==valor){
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
	
	public static int[] buscaEnMatriz(double[][]mat,int m,int n,double valor) {
		int []pos=new int[2];
		int i,j;
		boolean exit=true;
		
		i=0;
		while(i<m && exit) {
			j=0;
			while(j<n && exit) {
				if(mat[i][j]==valor) {
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
	
	public static boolean matricesIguales(double[][]matA,int mA, int nA, double[][] matB, int mB,int nB) {
		boolean res=false;
		int i,j;
		
		if(mA==mB && nA==nB) {
			i=0;
			res=true;
			while(i<mA && res) {
				j=0;
				while(j<nA && res) {
					if(matA[i][j]!=matB[i][j]) {
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
