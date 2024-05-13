package ge.tbcacademy;

import ge.tbcacademy.data.steps.fakerApi.CreditCardSteps;
import io.qameta.allure.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Epic("API Tests")
public class fakerApiCardTest {
    CreditCardSteps creditCardSteps;
    @BeforeClass
    public void setUp(){
        creditCardSteps = new CreditCardSteps();
    }

    @Feature("Credit Card Validation")
    @Story("Validate credit card response schema")
    @Description("Validate the credit card response schema from the Faker API. The schema includes regular expression patterns for the 'owner' field to ensure it has at least 2 characters, and for the 'status' field to ensure it's 'OK'.")
    @Severity(SeverityLevel.MINOR)

    @Test(description = "Validating Credit card response")
    public void testCard() {
        creditCardSteps.
                callCreditCardApiAndGetResponseBody()
                .validateSchema();
    }
}
