package ge.tbcacademy;

import ge.tbcacademy.data.steps.fakerApi.CreditCardSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class fakerApiCardTest {
    CreditCardSteps creditCardSteps;
    @BeforeClass
    public void setUp(){
        creditCardSteps = new CreditCardSteps();
    }
    @Test
    public void testCard(){
        creditCardSteps.validateSchema();
    }
}
