
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

import java.util.ArrayList;
public class TestUtilidades {


	// testing Utilidades
	@Test
	public void testEsNumerico() {	
		String stringNoNumerico = "Junit is working fine";
		String stringSiNumerico = "1234567890";

		assertTrue("No acepta un String que si es numerico", Utilidades.esNumerico(stringSiNumerico));
		assertFalse("Acepta un String que no es numerico", Utilidades.esNumerico(stringNoNumerico));
	}

}


