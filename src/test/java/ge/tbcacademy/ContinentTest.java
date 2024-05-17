package ge.tbcacademy;

import ge.tbcacademy.steps.continentSteps.ContinentSteps;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


@Epic("Continent Validation")
@Feature("Validate Continents Data")
public class ContinentTest {
    ContinentSteps continentSteps;
    @BeforeClass

    public void SetUp(){
        continentSteps = new ContinentSteps();

    }
    @Test(description = "Validating continents data")
    @Story("Validate continent data retrieved from the service")
    @Description("This test validates the data retrieved for continents from a SOAP service " +
            "It checks the count of sName nodes, the uniqueness of sName values" +
            "the presence of sCode and sName for each continent, ensures that the order of continents are alphabetical.")
    public void validatingContinents() {
        continentSteps.getResponse().
                 respToXmlPath()
                .validationCountOfSname()
                .validatingSNameValues()
                .validatingSNameWithScode()
                .validatingLastTcontinentSnameValue()
                .verifyingEverySNameIsUnique()
                .validatePresenceOfSCODEsNAME()
                .validateSCodeValuesWithPattern()
                .validatingAlphabeticalOrder()
                .validatingOceania()
                .validatingStartsWithAendsWithCa()
                .validatingSNameNotContainDigits();
    }
}