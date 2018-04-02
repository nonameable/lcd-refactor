/**
* Clase que define a una impresion particular
* Cada vez que se llame el metodo Imprimir de Nuevo, se crea una nueva instancia de la clase impresion
* y se utiliza para imprimir
*/

public class Impresion {

	// Variables de estado que son parametros de la impresion
	private int filasDigito;
    private int columnasDigito;
    private int totalFilas;
    private int totalColumnas;
    private int tamanioDigito;
    private int espacioEntreDigitos;

    // puntos fijos iniciales de la impresion actual.  
    private int[] pf1Inicial;
    private int[] pf2Inicial;
    private int[] pf3Inicial;
    private int[] pf4Inicial;
    private int[] pf5Inicial;

    // punto pivot para moverse a través de la dimension X (j de la matriz lienzo) 
    private int pivotX;


    public Impresion(int filasDigito, int columnasDigito, int totalFilas, int totalColumnas, int tamanioDigito, int espacioEntreDigitos){
    	this.filasDigito = filasDigito;
    	this.columnasDigito =columnasDigito;
    	this.totalFilas = totalFilas;
    	this.totalColumnas = totalColumnas;
    	this.tamanioDigito = tamanioDigito;
    	this.espacioEntreDigitos = espacioEntreDigitos;

    	// inicializa pivotX en cero. Se movera a lo largo de la direccion X (coordenada j de la matriz lienzo de la impresora)
        pivotX = 0;

	   	// Inicializa puntos fijos y los calcula
    	inicializarPuntosFijos();
    }

    public void inicializarPuntosFijos(){
    	pf1Inicial = new int[2];
        pf2Inicial = new int[2];
        pf3Inicial = new int[2];
        pf4Inicial = new int[2];
        pf5Inicial = new int[2];
        
        // calcula puntos fijos
        pf1Inicial[0] = 0;
        pf1Inicial[1] = 0;

        pf2Inicial[0] = (filasDigito / 2);
        pf2Inicial[1] = 0;

        pf3Inicial[0] = (filasDigito - 1);
        pf3Inicial[1] = 0;

        pf4Inicial[0] = (filasDigito / 2);
        pf4Inicial[1] = (columnasDigito - 1);

        pf5Inicial[0] = 0;
        pf5Inicial[1] = (columnasDigito - 1);

    }



    /**
     *
     * Metodo que retorna las filas que ocupa un digito
     */  
    public int darFilasDigito(){
    	return filasDigito;
    }

    /**
     *
     * Metodo que retorna las columnas que ocupa un digito
     */
    public int darColumnasDigito(){
    	return columnasDigito;
    }

    /**
     *
     * Metodo que retorna las filas necesarias para la impresion
     */
    public int darTotalFilas(){
    	return totalFilas;
    }

    /**
     *
     * Metodo que retorna las columnas necesarias para la impresion
     */
    public int darTotalColumnas(){
    	return totalColumnas;
    }


    /**
     *
     * Metodo que retorna el tamanio de un digito. Este es un parametro ingresado por el usuario
     */
    public int darTamanioDigito(){
    	return tamanioDigito;
    }
   
    /**
     *
     * Metodo que retorna el espacio que deberia haber entre los digitos en la impresion
     * Este es un parametro ingresado por el usuario
     */
    public int darEspacioEntreDigitos(){
    	return espacioEntreDigitos;
    }


    /**
     *
     * Metodo que mueve pivotX a lo largo de la dirección X incrementando su valor.
     * Esto lo hace en función de las columnas que ocupa un digito y el espacio que debe existir
     * entre cada par de digitos
     */
    public void moverPivotX(){
        int nuevoPivotX = pivotX + columnasDigito + espacioEntreDigitos;
        if(nuevoPivotX < totalColumnas){
            pivotX = nuevoPivotX;
        }
    }

    public int darPivotX(){
        return pivotX;
    }


    // los siguientes metodos retornan los puntos fijos actuales para la impresion
    // estos puntos fijos se van moviendo a lo largo de la direccion X con el fin
    // de insertar cada uno de los digitos en la matriz. Para esto se definen usando la variable pivotX.
    

    public int[] darPf1Actual(){
    	int [] pf1Actual = new int[2];
    	pf1Actual[0] = pf1Inicial[0];
    	pf1Actual[1] = pf1Inicial[1] + pivotX;
    	return pf1Actual;
    }

    public int[] darPf2Actual(){
    	int [] pf2Actual = new int[2];
    	pf2Actual[0] = pf2Inicial[0];
    	pf2Actual[1] = pf2Inicial[1] + pivotX;
    	return pf2Actual;
    }

    public int[] darPf3Actual(){
    	int [] pf3Actual = new int[2];
    	pf3Actual[0] = pf3Inicial[0];
    	pf3Actual[1] = pf3Inicial[1] + pivotX;
    	return pf3Actual;
    }

    public int[] darPf4Actual(){
    	int [] pf4Actual = new int[2];
    	pf4Actual[0] = pf4Inicial[0];
    	pf4Actual[1] = pf4Inicial[1] + pivotX;
    	return pf4Actual;
    }

    public int[] darPf5Actual(){
    	int [] pf5Actual = new int[2];
    	pf5Actual[0] = pf5Inicial[0];
    	pf5Actual[1] = pf5Inicial[1] + pivotX;
    	return pf5Actual;
    }



	
}