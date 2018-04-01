

/**
 *
 * Clase de utilidades para cualquier clase de nuestro programa.
 * Es extensible, es decir, cada vez que se necesite una nueva funcionalidad,
 * solo se necesita agregar un nuevo metodo estatico.
 *
 * @param cadena Cadena
 */  
public class Utilidades {

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