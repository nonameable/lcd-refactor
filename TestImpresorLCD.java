
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

import java.util.ArrayList;
public class TestImpresorLCD {


	// impresoraLCD
	private ImpresorLCD impresoraLCD;


	/**
     * Escenario simple. Se crea la impresora LCD
     */
    @Before
    public void setup(){
        impresoraLCD = new ImpresorLCD();
    }
    
	@Test
	public void testImprimeCorrectamenteNumeroSolo() {
		String resultadoEsperado =
		" -- \n" +
        "|  |\n" +
        "|  |\n" +
        " -- \n" +
        "|  |\n" +
        "|  |\n" +
        " -- \n";


		int espacioEntreDigitos = 0;
		String comandoImpresion = "2,8";

		try {
			impresoraLCD.imprimir(comandoImpresion, espacioEntreDigitos);
			String[][] matrizImpresa = impresoraLCD.darMatrizImpresion();
			String resultadoImpresion = Utilidades.matrizComoString(matrizImpresa);
			assertEquals("El resultado de la impresion y el resultado esperado no son los mismos para cuando se envia sólo un digito", resultadoImpresion, resultadoEsperado);

		} catch(Exception ex) {
			fail("No debería haber lanzado Exception");
		}
		

	}

	

	@Test
	public void testImprimeCorrectamenteCadenaDigitos() {
		String resultadoEsperado =
		"         ----    ----            ----    ----    ----    ----    ----   \n" +
		"     |       |       |  |    |  |       |            |  |    |  |    |  \n" +
		"     |       |       |  |    |  |       |            |  |    |  |    |  \n" +
		"     |       |       |  |    |  |       |            |  |    |  |    |  \n" +
		"     |       |       |  |    |  |       |            |  |    |  |    |  \n" +
		"         ----    ----    ----    ----    ----            ----    ----   \n" +
		"     |  |            |       |       |  |    |       |  |    |       |  \n" +
		"     |  |            |       |       |  |    |       |  |    |       |  \n" +
		"     |  |            |       |       |  |    |       |  |    |       |  \n" +
		"     |  |            |       |       |  |    |       |  |    |       |  \n" +
		"         ----    ----            ----    ----            ----    ----   \n";


		int espacioEntreDigitos = 2;
		String comandoImpresion = "4,123456789";

		try {
			impresoraLCD.imprimir(comandoImpresion, espacioEntreDigitos);
			String[][] matrizImpresa = impresoraLCD.darMatrizImpresion();
			String resultadoImpresion = Utilidades.matrizComoString(matrizImpresa);
			assertEquals("El resultado de la impresion y el resultado esperado no son los mismos para cuando se envía una cadena de digitos", resultadoImpresion, resultadoEsperado);
		} catch(Exception ex) {
			fail("No debería haber lanzado Exception");
		}

		
		

	}

	@Test
	public void testImprimeCorrectamenteCadenaTamanioMaximo() {
		String resultadoEsperado =
		" ----------       ----------      \n" +
        "           |     |                \n" +
        "           |     |                \n" +
        "           |     |                \n" +
        "           |     |                \n" +
        "           |     |                \n" +
        "           |     |                \n" +
        "           |     |                \n" +
        "           |     |                \n" +
        "           |     |                \n" +
        "           |     |                \n" +
        "                  ----------      \n" +
        "           |     |          |     \n" +
        "           |     |          |     \n" +
        "           |     |          |     \n" +
        "           |     |          |     \n" +
        "           |     |          |     \n" +
        "           |     |          |     \n" +
        "           |     |          |     \n" +
        "           |     |          |     \n" +
        "           |     |          |     \n" +
        "           |     |          |     \n" +
        "                  ----------      \n";


		int espacioEntreDigitos = 5;
		String comandoImpresion = "10,76";

		try {
			impresoraLCD.imprimir(comandoImpresion, espacioEntreDigitos);
			String[][] matrizImpresa = impresoraLCD.darMatrizImpresion();
			String resultadoImpresion = Utilidades.matrizComoString(matrizImpresa);
			assertEquals("El resultado de la impresion y el resultado esperado no son los mismos para cuando se quiere imprimir con tamanio 10(max)", resultadoImpresion, resultadoEsperado);

		} catch(Exception ex) {
			fail("No debería haber lanzado Exception");
		}
		
		
	}

}


