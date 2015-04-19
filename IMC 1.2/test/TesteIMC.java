import imc.Ex2;
import junit.framework.Assert;
import org.junit.Test;


/**
 *
 * @author Mariana Rocha - 41320761
 */


public class TesteIMC {
    
    @Test
    public void test() {
        Ex2 ex = new Ex2();
        double peso = 50.0;
        double altura = 155.0 / 100;
        double massa = peso / (altura * altura);
        Assert.assertEquals(20.811654526534856, massa);

    }
           
}
