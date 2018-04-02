
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestJunit {
   
   @Test
   public void testAdd() {
   		ImpresorLCD impresoraLCD = new ImpresorLCD();
   		String str = "Junit is working fine";
      	assertEquals("Junit is working fine",str);
   }
}