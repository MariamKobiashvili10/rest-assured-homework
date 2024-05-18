package ge.tbcacademy;

import ge.tbcacademy.data.steps.SoapDemoSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SoapDemoTest {
    SoapDemoSteps soapDemoSteps;
    @BeforeClass
    public void setUp(){
        soapDemoSteps = new SoapDemoSteps();
    }
    @Test
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
