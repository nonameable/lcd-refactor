
/**
*
* Cada vez que se llame el metodo Imprimir de Nuevo, se crea una nueva instancia de la clase impresion
* y se utiliza para imprimir
*/

public class Impresion {


	// 
	// 

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
        pf1Inicial[1] = 0 + pivotX;

        pf2Inicial[0] = (filasDigito / 2);
        pf2Inicial[1] = 0 + pivotX;

        pf3Inicial[0] = (filasDigito - 1);
        pf3Inicial[1] = 0 + pivotX;

        pf4Inicial[0] = (filasDigito / 2);
        pf4Inicial[1] = (columnasDigito - 1) + pivotX;

        pf5Inicial[0] = 0;
        pf5Inicial[1] = (columnasDigito - 1) + pivotX;

    }


    public int darFilasDigito(){
    	return filasDigito;
    }

    public int darColumnasDigito(){
    	return columnasDigito;
    }

    public int darTotalFilas(){
    	return totalFilas;
    }

    public int darTotalColumnas(){
    	return totalColumnas;
    }

    public int darTamanioDigito(){
    	return tamanioDigito;
    }
   
    public int darEspacioEntreDigitos(){
    	return espacioEntreDigitos;
    }



    public void incrementarPivotX(){
    	pivotX = pivotX + columnasDigito + espacioEntreDigitos;
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