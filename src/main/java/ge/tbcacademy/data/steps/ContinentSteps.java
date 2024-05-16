package ge.tbcacademy.data.steps;

import ge.tbcacademy.data.constants.ConstantsForContinents;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ContinentSteps {

    Response response;

    XmlPath respToXml;
    List<String> sNameValues;
    String sCode;

    @Step("getting response")
    public ContinentSteps getResponse(){
        response = given()
                .baseUri(ConstantsForContinents.BASE_URI)
                .when()
                .filter(new AllureRestAssured())
                .get(ConstantsForContinents.ENDPOINT);

        return this;
    }
    @Step("response as xmlpath")
    public ContinentSteps respToXmlPath(){
        respToXml = response.xmlPath();
        return this;
    }
    @Step("Validate count of all 'sName' nodes")
    public ContinentSteps validationCountOfSname(){
        sNameValues = response.xmlPath().getList("ArrayOftContinent.tContinent.sName");
        assertThat(sNameValues.size(), equalTo(6));
        return this;
    }

    @Step("Validate list of all 'sName' node's values")
    public ContinentSteps validatingSNameValues(){
        assertThat(sNameValues, containsInAnyOrder(ConstantsForContinents.ASIA,
                ConstantsForContinents.AFRICA, ConstantsForContinents.ANTARCTICA, ConstantsForContinents.EUROPE, ConstantsForContinents.OCEANIA, ConstantsForContinents.AMERICA));
        return this;
    }
    @Step("Validate 'sName' node result with value of 'sCode' equals to 'AN'")
    public ContinentSteps validatingSNameWithScode(){
        sCode = respToXml.get("ArrayOftContinent.tContinent.find { it.sCode == 'AN' }.sName");
        assertThat(sCode, equalTo(ConstantsForContinents.ANTARCTICA));

        return this;
    }
    @Step("Validate the last 'tContinent' node's 'sName' value")
    public ContinentSteps validatingLastTcontinentSnameValue(){
        assertThat(respToXml.get("ArrayOftContinent.tContinent[-1].sName"), equalTo(ConstantsForContinents.AMERICA));
        return this;
    }
    @Step("Verify that each 'sName' is unique")
    public ContinentSteps verifyingEverySNameIsUnique(){
        Set<String> sNamesSet = new HashSet<>(sNameValues);
        assertThat(sNamesSet.size(), equalTo(sNameValues.size()));
        return this;
    }
    @Step("Validate the presence and correctness of both 'sCode' and 'sName' for each 'tContinent'")
    public ContinentSteps validatePresenceOfSCODEsNAME(){
        for (int i =0; i < 6; i++){
            assertThat("ArrayOftContinent.tContinent[" + i + "].sName", notNullValue());
            assertThat("ArrayOftContinent.tContinent[" + i + "].sCode", notNullValue());
        }
        return this;

    }
    @Step("Checking that 'sCode' values follow a specific pattern (two uppercase letters)")
    public ContinentSteps validateSCodeValuesWithPattern() {
        List<String> scodeValues = respToXml.getList("ArrayOftContinent.tContinent.sCode");

        for (String sCodeValue : scodeValues) {
            assertThat(sCodeValue, matchesRegex("^[A-Z]{2}$"));
        }
        return this;
    }
    @Step("Ensure that the order of 'tContinent' nodes corresponds to a specific sequence (alphabetical order by 'sName')")
    public ContinentSteps validatingAlphabeticalOrder(){
        List<String> sNamesSorted = sNameValues.stream().sorted().collect(Collectors.toList());
        assertThat(sNameValues, equalTo(sNamesSorted));
        return this;
    }
    @Step("No Numeric Characters in 'sName'")
    public ContinentSteps validatingSNameNotContainDigits(){
        assertThat(sNameValues, not(contains("\\d")));
        return this;
    }
    @Step("Find all 'sName' that starts with 'A' and ends with 'ca'")
    public ContinentSteps validatingStartsWithAendsWithCa(){
        List<String> namesThatStartWithAEndsWithCa = respToXml.getList("ArrayOftContinent.tContinent.findAll { it.sName.text() ==~ /^A.*ca$/ }.sName");
        assertThat(namesThatStartWithAEndsWithCa, contains(ConstantsForContinents.AFRICA, ConstantsForContinents.ANTARCTICA));
        return this;
    }
    @Step("Find 'sCode' that starting with 'O' and ensure that is 'Ocenania'")
    public ContinentSteps validatingOceania(){
        assertThat(respToXml.get("ArrayOftContinent.tContinent.find { it.sCode.text().startsWith('O') }.sName"), equalTo(ConstantsForContinents.OCEANIA));
        return this;
    }
}

