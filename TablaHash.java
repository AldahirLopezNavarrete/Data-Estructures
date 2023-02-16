package tablashash;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* Esta clase representa de forma general una tabla hash y
   cuenta con la siguiente funcionalidad básica:
   - insertar elementos
   - buscar elementos
   - eliminar elementos 
   Utiliza la estrategia de encadenamiento separado para el manejo de colisiones
*/
public class TablaHash<T> {
    protected NodoHash<T>[] arreglo;
    protected int cont, tam; // cont representa el número de elementos y tam el tamaño de la tabla

    public TablaHash(int t) {
        this.arreglo = new NodoHash[t];
        this.tam = t;
        this.cont = 0;
    }

    public int getCont() {
        return cont;
    }

    public int getTam() {
        return tam;
    }

    //A pesar de que no es necesario para la funcionalidad de la clase, se agregó para fines prácticos y de experimentación.
    public double getFactorCarga() {
        double aux=-1;
        
        if(tam != 0){
            aux = (double) cont / (double) tam;
        }
      
        return aux;
    }

    //Permite insertar un elemento a la tabla de hash.
    protected void inserta(T elem) {
        int pos = funcionHash(elem.toString());
        cont++;

        NodoHash<T> temp = arreglo[pos];
        NodoHash<T> nuevo = new NodoHash<T>(elem);
        nuevo.setSig(temp);
        arreglo[pos] = nuevo;
        //mete el nuevo nodo en el arreglo(tabla) y hace que este apunte hacía lo que había previamente 
        //en esa posición del arreglo
    }

    //Permite buscar un elemento perteneciente a la tabla y regresa un boolean indicando si lo encontró
    protected boolean busca(T elem) {
        NodoHash<T> actual;
        int pos = funcionHash(elem.toString());

        actual = arreglo[pos];
        //recorre la lista enlazada que hay en el arreglo en esa posición
        // mientras no lo encuentre
        while (actual != null && !actual.getElem().equals(elem)) {
            actual = actual.getSig();
        }

        return actual != null; //si lo encontró actual no debe ser null, ya que no llegó al final
        //en caso de que no lo haya encontrado recorrió toda la lista y actual = null
    }

    //Método que se encarga de eliminar un elemento deseado.
    protected void elimina(T elem) {
        NodoHash<T> n = new NodoHash<T>(elem);
        int pos = funcionHash(elem.toString());

        if (arreglo[pos] == null) { //valida que haya al menos 1 nodo en esa posición
            return;
        }
        
        if (arreglo[pos].getElem().equals(elem)) {// es el primer nodo en arreglo[pos]
            arreglo[pos] = arreglo[pos].getSig();
            cont--;
        } else { // no es el primer elemento 
            NodoHash<T> actual = arreglo[pos];
            NodoHash<T> anterior = null; //guarda el nodo anterior

            //busca el nodo con el elemento
            while (actual.getSig() != null && !actual.getElem().equals(elem)) {
                anterior = actual;
                actual = actual.getSig();
            }
            // si lo encuentra quita los apuntadores hacía ese nodo
            if (actual.getElem().equals(elem)) {
                anterior.setSig(actual.getSig()); //anterior apunta al siguiente del nodo a eliminar
                actual.setSig(null); //rompe el enlace del nodo a eliminar con su siguiente
                cont--;
            }
            // si no lo encontró no hace nada
        }
    }

    //Toma una cadena, usa SHA256 para encriptarla y se convierte a un número entero para asignarle al elemento la posición que ocupará en la tabla.
    public int funcionHash(String str) {
        String aux = convertirSHA256(str);
        int aux2 = (int) (hexadecimalADecimal(aux) % tam);
        
        if(aux2<0){
            aux2=aux2*-1;
        }
        return aux2;
    }

    //Convierte un string a hexadecimal con ayuda del algoritmo de encriptación SHA-256. 
    private String convertirSHA256(String password) {
        MessageDigest md = null;

        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

        byte[] hash = md.digest(password.getBytes());
        StringBuffer sb = new StringBuffer();
        for (byte b : hash) {
            sb.append(String.format("%02x", b)); //convierte bytes comunes a bytes hexadecimales.
        }

        return sb.toString().toUpperCase();
    }

    //Convierte un String a un número int positivo
    //Truena con Int, aqui no se puede castear
    /*
    private int hex2Int(String cadena) {
        int res = 0;
        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            int num = (int) c;
            res = 256 * res + num;
        }
        return res;
    }
*/

    //Convierte un string en hexadecimal a un número entero (decimal).
    public long hexadecimalADecimal(String hexadecimal) {
        long decimal = 0;
        int potencia = 0;

        //Se recorrerá la cadena de derecha a izquierda.
        for (int i = hexadecimal.length() - 1; i >= 0; i--) {
            int valor = caracterHexadecimalADecimal(hexadecimal.charAt(i));
            long elevado = (long) Math.pow(16, potencia) * valor;
            decimal += elevado;
            // Avanzar en la potencia
            potencia++;
        }
        return decimal;
    }
    
    //Método de apoyo, convierte un caracter a su notación en hexadecimal.
    private static int caracterHexadecimalADecimal(char caracter) {
        switch (caracter) {
            case 'A':
                return 10;
            case 'B':
                return 11;
            case 'C':
                return 12;
            case 'D':
                return 13;
            case 'E':
                return 14;
            case 'F':
                return 15;
            default:
                return Integer.parseInt(String.valueOf(caracter));
        }
    }
    
    //str no disponible, ya que es solo ilustrativo
}
