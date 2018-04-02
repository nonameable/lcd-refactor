
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

import java.util.ArrayList;
public class TestImpresion {

	private int filasDigito;
    private int columnasDigito;
    private int totalFilas;
    private int totalColumnas;
    private int tamanioDigito;
    private int espacioEntreDigitos;

	// impresion
	private Impresion impresion;

	// numero digitos
	private int numeroDigitos;

	/**
     * Escenario simple. Se crea la impresion
     */
    @Before
    public void setup(){

        int[] digitos = {1,3,4};
		numeroDigitos = digitos.length;
		espacioEntreDigitos = 2;

		tamanioDigito = 4;
		espacioEntreDigitos = 3;
		filasDigito = (2 * tamanioDigito) + 3;
		columnasDigito = tamanioDigito + 2;
		totalFilas = filasDigito;;
		totalColumnas = (columnasDigito * numeroDigitos
                + (espacioEntreDigitos * numeroDigitos)); 

		impresion = new Impresion(filasDigito, columnasDigito, totalFilas, totalColumnas, tamanioDigito, espacioEntreDigitos);
    }

    // tests para los puntos inciales

	@Test
	public void testPf1InicialCorrecto() {
		int[] puntoEsperado = {0,0};
		int[] pf1Inicial = impresion.darPf1Actual();
		assertTrue("Coordenada inicial i de punto fijo 1 no es la esperada" , puntoEsperado[0] == pf1Inicial[0]);
		assertTrue("Coordenada inicial j de punto fijo 1 no es la esperada", puntoEsperado[1] == pf1Inicial[1]);
			
	}

	@Test
	public void testPf2InicialCorrecto() {
		int[] puntoEsperado = {(filasDigito / 2),0};
		int[] pf2Inicial = impresion.darPf2Actual();
		assertTrue("Coordenada inicial i de punto fijo 2 no es la esperada" , puntoEsperado[0] == pf2Inicial[0]);
		assertTrue("Coordenada inicial j de punto fijo 2 no es la esperada", puntoEsperado[1] == pf2Inicial[1]);
	}
	@Test
	public void testPf3InicialCorrecto() {
		int[] puntoEsperado = {(filasDigito - 1),0};
		int[] pf3Inicial = impresion.darPf3Actual();
		assertTrue("Coordenada inicial i de punto fijo 3 no es la esperada" , puntoEsperado[0] == pf3Inicial[0]);
		assertTrue("Coordenada inicial j de punto fijo 3 no es la esperada", puntoEsperado[1] == pf3Inicial[1]);
	}

	@Test
	public void testPf4InicialCorrecto() {
		int[] puntoEsperado = {(filasDigito / 2),(columnasDigito - 1)};
		int[] pf4Inicial = impresion.darPf4Actual();
		assertTrue("Coordenada inicial i de punto fijo 4 no es la esperada" , puntoEsperado[0] == pf4Inicial[0]);
		assertTrue("Coordenada inicial j de punto fijo 4 no es la esperada", puntoEsperado[1] == pf4Inicial[1]);
	}
	@Test
	public void testPf5InicialCorrecto() {
		int[] puntoEsperado = {0,(columnasDigito - 1)};
		int[] pf5Inicial = impresion.darPf5Actual();
		assertTrue("Coordenada inicial i de punto fijo 5 no es la esperada" , puntoEsperado[0] == pf5Inicial[0]);
		assertTrue("Coordenada inicial j de punto fijo 5 no es la esperada", puntoEsperado[1] == pf5Inicial[1]);

	}


	// tests para cuando pivotX se ha movido

	@Test
	public void testPf1Correcto() {
		impresion.moverPivotX();
		impresion.moverPivotX();
		int pivotX = impresion.darPivotX();
		int[] puntoEsperado = {0,0 + pivotX};
		int[] pf1 = impresion.darPf1Actual();
		assertTrue("Coordenada inicial i de punto fijo 1 no es la esperada" , puntoEsperado[0] == pf1[0]);
		assertTrue("Coordenada inicial j de punto fijo 1 no es la esperada", puntoEsperado[1] == pf1[1]);
		
			
	}

	@Test
	public void testPf2Correcto() {
		impresion.moverPivotX();
		impresion.moverPivotX();
		int pivotX = impresion.darPivotX();
		int[] puntoEsperado = {(filasDigito / 2),0 + pivotX};
		int[] pf2 = impresion.darPf2Actual();
		assertTrue("Coordenada i de punto fijo 2 no es la esperada" , puntoEsperado[0] == pf2[0]);
		assertTrue("Coordenada j de punto fijo 2 no es la esperada", puntoEsperado[1] == pf2[1]);
		
	}
	@Test
	public void testPf3Correcto() {
		impresion.moverPivotX();
		impresion.moverPivotX();
		int pivotX = impresion.darPivotX();
		int[] puntoEsperado = {(filasDigito - 1),0 + pivotX};
		int[] pf3 = impresion.darPf3Actual();
		assertTrue("Coordenada i de punto fijo 3 no es la esperada" , puntoEsperado[0] == pf3[0]);
		assertTrue("Coordenada j de punto fijo 3 no es la esperada", puntoEsperado[1] == pf3[1]);
		
	}

	@Test
	public void testPf4Correcto() {
		impresion.moverPivotX();
		impresion.moverPivotX();
		int pivotX = impresion.darPivotX();
		int[] puntoEsperado = {(filasDigito / 2),(columnasDigito - 1) + pivotX};
		int[] pf4 = impresion.darPf4Actual();
		assertTrue("Coordenada i de punto fijo 4 no es la esperada" , puntoEsperado[0] == pf4[0]);
		assertTrue("Coordenada j de punto fijo 4 no es la esperada", puntoEsperado[1] == pf4[1]);
		
	}
	@Test
	public void testPf5Correcto() {
		impresion.moverPivotX();
		impresion.moverPivotX();
		int pivotX = impresion.darPivotX();
		int[] puntoEsperado = {0,(columnasDigito - 1)+ pivotX};
		int[] pf5 = impresion.darPf5Actual();
		assertTrue("Coordenada i de punto fijo 5 no es la esperada" , puntoEsperado[0] == pf5[0]);
		assertTrue("Coordenada j de punto fijo 5 no es la esperada", puntoEsperado[1] == pf5[1]);
	}

	@Test
	public void testMoverPivotXMasDeLoPosible() {
		impresion.moverPivotX();
		impresion.moverPivotX();
		int[] pf5 = impresion.darPf5Actual();

		impresion.moverPivotX();

		int[] pf5Siguiente = impresion.darPf5Actual();
		assertTrue("Coordenada i de punto fijo 5 incremento mas de lo posible" , pf5[0] == pf5Siguiente[0]);
		assertTrue("Coordenada j de punto fijo 5 incremento mas de lo posible", pf5[1] == pf5Siguiente[1]);
	}


}


