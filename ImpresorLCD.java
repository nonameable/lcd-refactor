import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ImpresorLCD {

    // Puntos fijos
    private final int[] pf1;
    private final int[] pf2;
    private final int[] pf3;
    private final int[] pf4;
    private final int[] pf5;
    private String[][] matrizImpresion;

    static final String CARACTER_VERTICAL = "|";
    static final String CARACTER_HORIZONTAL = "-";
    static final String POSICION_X = "X";
    static final String POSICION_Y = "Y";

    // TODO code application logic here
    //String entrada = JOptionPane.showInputDialog("Digite el numero");
    private int tamanioDigito;

    // Calcula el numero de filasDigito
    private int filasDigito;
    private int columnasDigito;
    private int totalFilas;
    private int totalColumnas;

    public ImpresorLCD() {
        // Inicializa variables
        this.pf1 = new int[2];
        this.pf2 = new int[2];
        this.pf3 = new int[2];
        this.pf4 = new int[2];
        this.pf5 = new int[2];
    }

    /**
     *
     * Metodo encargado de añadir una linea a la matriz de Impresion
     *
     * @param matriz Matriz Impresion
     * @param punto Punto Pivote
     * @param posFija Posicion Fija
     * @param tamanioDigito Tamaño Segmento
     * @param caracter Caracter Segmento
     */    
    private void adicionarLinea(String[][] matriz, int[] punto, String posFija,
            int tamanioDigito, String caracter) {

        if (posFija.equalsIgnoreCase(POSICION_X)) 
        {
            for (int y = 1; y <= tamanioDigito; y++) 
            {
                int valor = punto[1] + y; // mal nombre , valor no es bueno
                matriz[punto[0]][valor] = caracter;
            }
        } 
        else 
        {
            for (int i = 1; i <= tamanioDigito; i++) 
            {
                int valor = punto[0] + i;
                matriz[valor][punto[1]] = caracter;
            }
        }
    }

    /**
     *
     * Metodo encargado de un segmento a la matriz de Impresion
     *
     * @param segmento Segmento a adicionar
     */  
    private void adicionarSegmento(int segmento) {

        switch (segmento) {
            case 1:
                adicionarLinea(this.matrizImpresion
            , this.pf1, POSICION_Y,
                        this.tamanioDigito, CARACTER_VERTICAL);
                break;
            case 2:
                adicionarLinea(this.matrizImpresion
            , this.pf2, POSICION_Y,
                        this.tamanioDigito, CARACTER_VERTICAL);
                break;
            case 3:
                adicionarLinea(this.matrizImpresion
            , this.pf5, POSICION_Y,
                        this.tamanioDigito, CARACTER_VERTICAL);
                break;
            case 4:
                adicionarLinea(this.matrizImpresion
            , this.pf4, POSICION_Y,
                        this.tamanioDigito, CARACTER_VERTICAL);
                break;
            case 5:
                adicionarLinea(this.matrizImpresion
            , this.pf1, POSICION_X,
                        this.tamanioDigito, CARACTER_HORIZONTAL);
                break;
            case 6:
                adicionarLinea(this.matrizImpresion
            , this.pf2, POSICION_X,
                        this.tamanioDigito, CARACTER_HORIZONTAL);
                break;
            case 7:
                adicionarLinea(this.matrizImpresion
            , this.pf3, POSICION_X,
                        this.tamanioDigito, CARACTER_HORIZONTAL);
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
        List<Integer> segmentosNumero = new ArrayList<>();

        switch (numero) {
            case 1:
                segmentosNumero.add(3);
                segmentosNumero.add(4);
                break;
            case 2:
                segmentosNumero.add(5);
                segmentosNumero.add(3);
                segmentosNumero.add(6);
                segmentosNumero.add(2);
                segmentosNumero.add(7);
                break;
            case 3:
                segmentosNumero.add(5);
                segmentosNumero.add(3);
                segmentosNumero.add(6);
                segmentosNumero.add(4);
                segmentosNumero.add(7);
                break;
            case 4:
                segmentosNumero.add(1);
                segmentosNumero.add(6);
                segmentosNumero.add(3);
                segmentosNumero.add(4);
                break;
            case 5:
                segmentosNumero.add(5);
                segmentosNumero.add(1);
                segmentosNumero.add(6);
                segmentosNumero.add(4);
                segmentosNumero.add(7);
                break;
            case 6:
                segmentosNumero.add(5);
                segmentosNumero.add(1);
                segmentosNumero.add(6);
                segmentosNumero.add(2);
                segmentosNumero.add(7);
                segmentosNumero.add(4);
                break;
            case 7:
                segmentosNumero.add(5);
                segmentosNumero.add(3);
                segmentosNumero.add(4);
                break;
            case 8:
                segmentosNumero.add(1);
                segmentosNumero.add(2);
                segmentosNumero.add(3);
                segmentosNumero.add(4);
                segmentosNumero.add(5);
                segmentosNumero.add(6);
                segmentosNumero.add(7);
                break;
            case 9:
                segmentosNumero.add(1);
                segmentosNumero.add(3);
                segmentosNumero.add(4);
                segmentosNumero.add(5);
                segmentosNumero.add(6);
                segmentosNumero.add(7);
                break;
            case 0:
                segmentosNumero.add(1);
                segmentosNumero.add(2);
                segmentosNumero.add(3);
                segmentosNumero.add(4);
                segmentosNumero.add(5);
                segmentosNumero.add(7);
                break;
            default:
                break;
        }

        Iterator<Integer> iterator = segmentosNumero.iterator();

        while (iterator.hasNext()) {
            adicionarSegmento(iterator.next());
        }
    }

    /**
     *
     * Metodo encargado de imprimir un numero
     *
     * @param tamanioDigito Tamaño Segmento Digitos
     * @param numeroImp Numero a Imprimir
     * @param espacio Espacio Entre digitos
     */    
    private void imprimirNumero(int tamanioDigito, String numeroImp, int espacio) 
    {
        // Variable que permite moverse en la dimension X de la matriz de impresion
        int pivotX = 0;
        
        char[] digitos;

        this.tamanioDigito = tamanioDigito;

        // Calcula el numero de filas cada digito
        this.filasDigito = (2 * this.tamanioDigito) + 3;

        // Calcula el numero de columna de cada digito
        this.columnasDigito = this.tamanioDigito + 2;

        // Calcula el total de filas de la matriz en la que se almacenaran los digitos
        this.totalFilas = this.filasDigito;

        // Calcula el total de columnas de la matriz en la que se almacenaran los digitos
        this.totalColumnas
 = (this.columnasDigito * numeroImp.length())
                + (espacio * numeroImp.length()); // deberia ser menos 1, a menos que queramos espacio despues del ultimo digito

        // crea matriz para almacenar los numero a imprimir
        this.matrizImpresion
 = new String[this.totalFilas][this.totalColumnas];

        // crea el arreglo de digitos
        digitos = numeroImp.toCharArray();

        // Inicializa matriz
        for (int i = 0; i < this.totalFilas; i++) {
            for (int j = 0; j < this.totalColumnas
        ; j++) {
                this.matrizImpresion
        [i][j] = " ";
            }
        }

        for (char digito : digitos) {
            
            //Valida que el caracter sea un digito
            if( ! Character.isDigit(digito))
            {
                throw new IllegalArgumentException("Caracter " + digito
                    + " no es un digito");
            }

            int numero = Integer.parseInt(String.valueOf(digito));

            //Calcula puntos fijos
            this.pf1[0] = 0;
            this.pf1[1] = 0 + pivotX;

            this.pf2[0] = (this.filasDigito / 2);
            this.pf2[1] = 0 + pivotX;

            this.pf3[0] = (this.filasDigito - 1);
            this.pf3[1] = 0 + pivotX;

            this.pf4[0] = (this.columnasDigito - 1);          // aqui estan invertidas las dimensiones
            this.pf4[1] = (this.filasDigito / 2) + pivotX; // aqui estan invertidas las dimensiones

            this.pf5[0] = 0;
            this.pf5[1] = (this.columnasDigito - 1) + pivotX;

            pivotX = pivotX + this.columnasDigito + espacio;

            adicionarDigito(numero);
        }

        // Imprime matriz
        for (int i = 0; i < this.totalFilas; i++) {
            for (int j = 0; j < this.totalColumnas
        ; j++) {
                System.out.print(this.matrizImpresion
            [i][j]);
            }
            System.out.println();
        }
    }

     /**
     *
     * Metodo encargado de procesar la entrada que contiene el tamanioDigito del segmento
     * de los digitos y los digitos a imprimir
     *
     * @param comando Entrada que contiene el tamanioDigito del segmento de los digito
     * y el numero a imprimir
     * @param espacioEntreDigitos Espacio Entre digitos
     */  
    public void procesar(String comando, int espacioEntreDigitos) {
        
        String[] parametros;
        
        int tamanioDigito;

        if (!comando.contains(",")) {
            throw new IllegalArgumentException("Cadena " + comando
                    + " no contiene caracter ,");
        }
        
        //Se hace el split de la cadena
        parametros = comando.split(",");
        
        //Valida la cantidad de parametros
        if(parametros.length>2)
        {
           throw new IllegalArgumentException("Cadena " + comando
                    + " contiene mas caracter ,"); 
        }
        
        //Valida la cantidad de parametros
        if(parametros.length<2)
        {
           throw new IllegalArgumentException("Cadena " + comando
                    + " no contiene los parametros requeridos"); 
        }
        
        //Valida que el parametro tamanioDigito sea un numerico
        if(esNumerico(parametros[0]))
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

        // Realiza la impresion del numero
        imprimirNumero(tamanioDigito, parametros[1],espacioEntreDigitos);

    }

    /**
     *
     * Metodo encargado de validar si una cadena es numerica
     *
     * @param cadena Cadena
     */  
    static boolean esNumerico(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

}
