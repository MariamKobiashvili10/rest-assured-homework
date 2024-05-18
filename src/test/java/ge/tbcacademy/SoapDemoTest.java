package ge.tbcacademy;

import ge.tbcacademy.data.steps.SoapDemoSteps;
import io.qameta.allure.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Epic("SOAP Service Testing")
@Feature("Finding Person Feature")
public class SoapDemoTest {
    SoapDemoSteps soapDemoSteps;
    @BeforeClass
    public void setUp(){
        soapDemoSteps = new SoapDemoSteps();
    }
    @Description("Test to find a person and validate their attributes")
    @Severity(SeverityLevel.NORMAL)
    @Story("Validate attributes of a person")
    @Test(description = "validating serveral things about person")
    public void findingPerson(){
        soapDemoSteps.creatingBody()
                .findingPerson()
                .validatingNotNull()
                .validatingName()
                .validatingSSNValue()
                .validatingNumbOfCharacters()
                .validatingDigitsWithPattern()
                .validatingDOB()
                .validatingFavoriteColors()
                .validatingLastName()
                .booleanValidation()
                .zipCodeBooleanValidation();
    }
}
