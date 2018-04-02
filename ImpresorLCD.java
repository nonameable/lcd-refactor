
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ImpresorLCD {


    // Objeto diccionario que contiene los segmentos para cada numero
    private DiccionarioSegmentos diccionarioSegmentos;

    // Impresion actual que contiene todas las caracteristicas de la impresion a realizar
    private Impresion impresion;

    private static final String CARACTER_VERTICAL = "|";
    private static final String CARACTER_HORIZONTAL = "-";
    private static final String POSICION_X = "X";
    private static final String POSICION_Y = "Y";

    // matriz de impresion. Sera un lienzo sobre el cual se escribiran los caracteres horizontales y veticales
    private String[][] matrizImpresion;

    // Variables de estado que son parametros de la impresion actual

    public ImpresorLCD() {

        // inicializar diccionarioSegmentos
        diccionarioSegmentos = new DiccionarioSegmentos();
    }

    /**
     *
     * Metodo encargado de inicializar las variables de estado de la impresion con los valores
     * dados por el usuario
     * @param tamanioDigito tamanio de cada digito para la impresion
     * @param numeroDigitos numero de digitos a imprimir en la impresion
     * @param espacioEntreDigitos espacio que debe existir entre los digitos
     */    
    private void inicializarImpresion(int tamanioDigito, int numeroDigitos, int espacioEntreDigitos) {

        // Calcula el numero de filas cada digito
        int filasDigito = (2 * tamanioDigito) + 3;

        // Calcula el numero de columna de cada digito
        int columnasDigito = tamanioDigito + 2;

        // Calcula el total de filas de la matriz en la que se almacenaran los digitos
        int totalFilas = filasDigito;

        // Calcula el total de columnas de la matriz en la que se almacenaran los digitos
        int totalColumnas = (columnasDigito * numeroDigitos
                + (espacioEntreDigitos * numeroDigitos)); 

        this.impresion = new Impresion(filasDigito, columnasDigito, totalFilas, totalColumnas, tamanioDigito, espacioEntreDigitos);

    }

    /**
     *
     * Metodo que retorna los segmentos asociados a un numero que entra por parametro
     * @param numero numero del cual se quieren obtener los segmentos
     */    
    private ArrayList<Integer> obtenerSegmentosPara(int numero) {

        Integer i = new Integer(numero);
        ArrayList<Integer> segmentos = diccionarioSegmentos.darSegmentosPara(i);
        return segmentos;
    }




    /**
     *
     * Metodo encargado de añadir una linea a la matriz de Impresion
     * @param punto Punto Pivote
     * @param posFija Posicion Fija
     * @param caracter Caracter Segmento
     */    
    private void adicionarLinea(int[] punto, String posFija, String caracter) {
        int tamanioDigito = impresion.darTamanioDigito();
        if (posFija.equalsIgnoreCase(POSICION_X)) 
        {
            for (int y = 1; y <= tamanioDigito; y++) 
            {
                int valorCoordenada = punto[1] + y;
                matrizImpresion[punto[0]][valorCoordenada] = caracter;
            }
        } 
        else 
        {
            for (int i = 1; i <= tamanioDigito; i++) 
            {
                int valorCoordenada = punto[0] + i;
                matrizImpresion[valorCoordenada][punto[1]] = caracter;
            }
        }
    }

    /**
     * Metodo encargado de un segmento a la matriz de Impresion     *
     * @param segmento Segmento a adicionar
     */  
    private void adicionarSegmento(int segmento) {
        switch (segmento) {
            case 1:
                adicionarLinea(impresion.darPf1Actual(), POSICION_Y, CARACTER_VERTICAL);
                break;
            case 2:
                adicionarLinea(impresion.darPf2Actual(), POSICION_Y, CARACTER_VERTICAL);
                break;
            case 3:
                adicionarLinea(impresion.darPf5Actual(), POSICION_Y, CARACTER_VERTICAL);
                break;
            case 4:
                adicionarLinea(impresion.darPf4Actual(), POSICION_Y, CARACTER_VERTICAL);
                break;
            case 5:
                adicionarLinea(impresion.darPf1Actual(), POSICION_X, CARACTER_HORIZONTAL);
                break;
            case 6:
                adicionarLinea(impresion.darPf2Actual(), POSICION_X, CARACTER_HORIZONTAL);
                break;
            case 7:
                adicionarLinea(impresion.darPf3Actual(), POSICION_X,CARACTER_HORIZONTAL);
                break;
            default:
                break;
        }
    }

    /**
     *
     * Metodo encargado de definir los segmentos que componen un digito y
     * a partir de los segmentos adicionar la representacion del digito a la matriz
     *
     * @param numero Digito
     */
    private void adicionarDigito(int numero) {

        // Establece los segmentos de cada numero
        List<Integer> segmentos = new ArrayList<>();

        segmentos = obtenerSegmentosPara(numero);
        
        Iterator<Integer> iterator = segmentos.iterator();
        while (iterator.hasNext()) {
            adicionarSegmento(iterator.next());
        }
    }

    /**
     *
     * Metodo encargado de insertar los digitos en la matriz lienzo
     * Esto lo hara llenando la matriz con simbolos "-" y "|" que representaran los digitos
     * @param int[] digitos array con los digitos a a Imprimir
     */    
    private void insertarDigitosEnMatriz(int[] digitos) 
    {
        // Variable que permite moverse en la dimension X de la matriz de impresion
        for (int digito : digitos) {
            adicionarDigito(digito);
            impresion.moverPivotX();         
        }

    }


    /**
     *
     * Metodo que retorna un int array con los digitos contenidos en un String.
     * En caso de que alguno de los caracteres del String no sea un digito,
     * lanza una IllegalArgumentException. 
     *
     * @param digitosComoString String que contiene la cadena de digitos
     */
    private int[] obtenerDigitos(String digitosComoString) throws IllegalArgumentException{
        // Obtiene la cadena de digitos a imprimir
        char[] digitosComoCharArray = digitosComoString.toCharArray();
        int[] digitos = new int[digitosComoCharArray.length];
        
        for (int i = 0; i < digitosComoCharArray.length; i++) {
            
            //Valida que el caracter sea un digito
            char digitoComoChar = digitosComoCharArray[i];
            if( ! Character.isDigit(digitoComoChar))
            {
                throw new IllegalArgumentException("Caracter " + digitoComoChar
                    + " no es un digito");
            }

            int numero = Integer.parseInt(String.valueOf(digitoComoChar));
            digitos[i] = numero;
        }
        return digitos;
    }


    /**
     * Método que imprime en consola la matriz que entra por parametro. En caso de que la matriz
     * no este inicializada, lanza NullPointerException
     * @param matriz String[][] con la matriz de strings que se quiere imprimir
     */
    private void imprimirMatriz(String[][] matriz){
        
        if( matriz != null){
            String matrizComoString = Utilidades.matrizComoString(matriz);
            System.out.println(matrizComoString);
        }
        else {
            throw new NullPointerException("La matriz aún no ha sido inicializada");
        }
        
    }

    /**
     * Método que construye una matriz de strings para llenar luego con los simbolos que representaran digtos
     */
    private void construirMatrizVacia(){
        
        int totalFilas = impresion.darTotalFilas();
        int totalColumnas = impresion.darTotalColumnas();

        // crea matriz para almacenar los numero a imprimir
        String[][] matrizImpresion = new String[totalFilas][totalColumnas];


        // Inicializa matriz
        for (int i = 0; i < totalFilas; i++) {
            for (int j = 0; j < totalColumnas; j++) {
                matrizImpresion[i][j] = " ";
            }
        }

        this.matrizImpresion = matrizImpresion;
        
    }


     /**
     *
     * Metodo encargado de procesar la entrada que contiene el tamanioDigito del segmento
     * de los digitos y los digitos a imprimir
     *
     * @param ordenDeImpresion Entrada que contiene el tamanioDigito del segmento de los digito
     * y el numero a imprimir
     * @param espacioEntreDigitos Espacio Entre digitos
     */  
    public void imprimir(String ordenDeImpresion, int espacioEntreDigitos) {
        
        String[] parametros;
        
        int tamanioDigito;

        if (!ordenDeImpresion.contains(",")) {
            throw new IllegalArgumentException("Cadena " + ordenDeImpresion
                    + " no contiene caracter ,");
        }
        
        //Se hace el split de la cadena
        parametros = ordenDeImpresion.split(",");
        
        //Valida la cantidad de parametros
        if(parametros.length>2)
        {
           throw new IllegalArgumentException("Cadena " + ordenDeImpresion
                    + " contiene mas caracter ,"); 
        }
        
        //Valida la cantidad de parametros
        if(parametros.length<2)
        {
           throw new IllegalArgumentException("Cadena " + ordenDeImpresion
                    + " no contiene los parametros requeridos"); 
        }
        
        //Valida que el parametro tamanioDigito sea un numerico
        if(Utilidades.esNumerico(parametros[0]))
        {
            tamanioDigito = Integer.parseInt(parametros[0]);
            
            // se valida que el tamanioDigito este entre 1 y 10
            if(tamanioDigito <1 || tamanioDigito >10)
            {
                throw new IllegalArgumentException("El parametro tamanioDigito ["+tamanioDigito
                        + "] debe estar entre 1 y 10");
            }
        }
        else
        {
            throw new IllegalArgumentException("Parametro Size [" + parametros[0]
                    + "] no es un numero");
        }

        String digitosComoString = parametros[1];
        int[] digitos = obtenerDigitos(digitosComoString);


        // Inicializacion de parametros para la orden de impresion correspondiente
        inicializarImpresion(tamanioDigito, digitos.length, espacioEntreDigitos);

        // construimos la matriz lienzo vacia para luego llenarla con los caracteres verticales y horizaontales
        construirMatrizVacia();
        
        // Insertamos los digitos en la matriz lienzo donde se pondran los simbolos "-" y "|"
        insertarDigitosEnMatriz(digitos);

        // Realiza la impresion de los digitos
        imprimirMatriz(matrizImpresion);

    }

    /**
     *
     * Metodo que retorna la matriz de impresion en su estado Actual
     */ 
    public String[][] darMatrizImpresion(){
        return matrizImpresion;
    }

    
}
