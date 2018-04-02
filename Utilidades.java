import java.util.ArrayList;
/**
 *
 * Clase de utilidades para cualquier clase de nuestro programa.
 * Es extensible, es decir, cada vez que se necesite una nueva funcionalidad,
 * solo se necesita agregar un nuevo metodo estatico.
 */  
public class Utilidades {

	/**
     *
     * Metodo encargado de validar si una cadena es numerica
     *
     * @param cadena Cadena
     */  
    public static boolean esNumerico(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    /**
     * Método que retorna la matriz actual como un String.
     * @param matriz String[][] con la matriz de strings que se quiere imprimir
     */
    public static String matrizComoString(String[][] matriz){
        String matrizComoString = "";
        
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matrizComoString += matriz[i][j];
            }
            matrizComoString += "\n";
        }

        return matrizComoString;   
    }	
}