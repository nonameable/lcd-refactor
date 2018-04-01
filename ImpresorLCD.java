import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ImpresorLCD {


    // Objeto diccionario que contiene los segmentos para cada numero
    private DiccionarioSegmentos diccionarioSegmentos;

    // Puntos fijos
    private final int[] pf1;
    private final int[] pf2;
    private final int[] pf3;
    private final int[] pf4;
    private final int[] pf5;

    private static final String CARACTER_VERTICAL = "|";
    private static final String CARACTER_HORIZONTAL = "-";
    private static final String POSICION_X = "X";
    private static final String POSICION_Y = "Y";

    // matriz de impresion. Sera un lienzo sobre el cual se escribiran los caracteres horizontales y veticales
    private String[][] matrizImpresion;

    // Variables de estado que son parametros de la impresion actual
    // Cada vez que se llame el metodo Imprimir de Nuevo, se inicializan con los nuevos valores correspondientes
    private int filasDigito;
    private int columnasDigito;
    private int totalFilas;
    private int totalColumnas;
    private int tamanioDigito;
    private int espacioEntreDigitos;

    public ImpresorLCD() {

        // inicializar segmentosPorNumero
        diccionarioSegmentos = new DiccionarioSegmentos();

        // Inicializa variables
        pf1 = new int[2];
        pf2 = new int[2];
        pf3 = new int[2];
        pf4 = new int[2];
        pf5 = new int[2];
    }



    /**
     *
     * Metodo encargado de inicializar las variables de estado de la impresion con los valores
     * dados por el usuario
     */    
    private void inicializarParametrosDeImpresion(int tamanioDigito, int numeroDigitos, int espacioEntreDigitos) {
        this.tamanioDigito = tamanioDigito;
        this.espacioEntreDigitos = espacioEntreDigitos;

        // Calcula el numero de filas cada digito
        int filasDigito = (2 * tamanioDigito) + 3;
        this.filasDigito = filasDigito;

        // Calcula el numero de columna de cada digito
        int columnasDigito = tamanioDigito + 2;
        this.columnasDigito = columnasDigito;

        // Calcula el total de filas de la matriz en la que se almacenaran los digitos
        int totalFilas = filasDigito;
        this.totalFilas = totalFilas;

        // Calcula el total de columnas de la matriz en la que se almacenaran los digitos
        int totalColumnas = (columnasDigito * numeroDigitos
                + (espacioEntreDigitos * numeroDigitos)); 
        this.totalColumnas = totalColumnas;

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
     * @param tamanioDigito Tamaño Segmento
     * @param caracter Caracter Segmento
     */    
    private void adicionarLinea(int[] punto, String posFija, String caracter) {

        if (posFija.equalsIgnoreCase(POSICION_X)) 
        {
            for (int y = 1; y <= tamanioDigito; y++) 
            {
                int valor = punto[1] + y; // mal nombre , valor no es bueno
                matrizImpresion[punto[0]][valor] = caracter;
            }
        } 
        else 
        {
            for (int i = 1; i <= tamanioDigito; i++) 
            {
                int valor = punto[0] + i;
                matrizImpresion[valor][punto[1]] = caracter;
            }
        }
    }

    /**
     *
     * Metodo encargado de un segmento a la matriz de Impresion
     *
     * @param segmento Segmento a adicionar
     * @param tamanioDigito Tamanio del digito especificado por el usuario para la impresion
     * @param matrizImpresion   Matriz donde se va a adicionar el segmento
     */  
    private void adicionarSegmento(int segmento) {

        switch (segmento) {
            case 1:
                adicionarLinea(pf1, POSICION_Y, CARACTER_VERTICAL);
                break;
            case 2:
                adicionarLinea(pf2, POSICION_Y, CARACTER_VERTICAL);
                break;
            case 3:
                adicionarLinea(pf5, POSICION_Y, CARACTER_VERTICAL);
                break;
            case 4:
                adicionarLinea(pf4, POSICION_Y, CARACTER_VERTICAL);
                break;
            case 5:
                adicionarLinea(pf1, POSICION_X, CARACTER_HORIZONTAL);
                break;
            case 6:
                adicionarLinea(pf2, POSICION_X, CARACTER_HORIZONTAL);
                break;
            case 7:
                adicionarLinea(pf3, POSICION_X,CARACTER_HORIZONTAL);
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
        System.out.println("---------------");
        System.out.println(segmentos);
        System.out.println("---------------");


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
        int pivotX = 0;

        for (int digito : digitos) {
            
            //Calcula puntos fijos
            pf1[0] = 0;
            pf1[1] = 0 + pivotX;

            pf2[0] = (filasDigito / 2);
            pf2[1] = 0 + pivotX;

            pf3[0] = (filasDigito - 1);
            pf3[1] = 0 + pivotX;

            pf4[0] = (filasDigito / 2);
            pf4[1] = (columnasDigito - 1) + pivotX;

            pf5[0] = 0;
            pf5[1] = (this.columnasDigito - 1) + pivotX;

            pivotX = pivotX + columnasDigito + espacioEntreDigitos;

            adicionarDigito(digito);
        }

    }


    /**
     *
     * Metodo que retorna un int array con los digitos contenidos en un String.
     * En caso de que alguno de los caracteres del String no sea un digito,
     * lanza una IllegalArgumentException. 
     *
     * @param String que contiene la cadena de digitos
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
     * @param String[][] con la matriz de strings que se quiere imprimir
     */
    private void imprimirMatriz(String[][] matriz){
        
        if( matriz != null){
            // Imprime matriz
            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz[i].length; j++) {
                    System.out.print(matriz[i][j]);
                }
                System.out.println();
            }
        }
        else {
            throw new NullPointerException("La matriz aún no ha sido inicializada");
        }
        
    }

    /**
     * Método que construye una matriz de strings para llenar luego con los simbolos que representaran digtos
     * @param tamanioDigito
     * @param numeroDigitos
     * @param espacioEntreDigitos
     */
    private void construirMatrizVacia(){
        
        
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
        inicializarParametrosDeImpresion(tamanioDigito, digitos.length, espacioEntreDigitos);

        // construimos la matriz lienzo vacia para luego llenarla con los caracteres verticales y horizaontales
        construirMatrizVacia();
        
        // Insertamos los digitos en la matriz lienzo donde se pondran los simbolos "-" y "|"
        insertarDigitosEnMatriz(digitos);

        // Realiza la impresion de los digitos
        imprimirMatriz(matrizImpresion);


    }

    
}
