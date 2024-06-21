package ge.tbcacademy.data.steps;

import ge.tbcacademy.data.constants.ConstantsForSoapDemo;
import ge.tbcacademy.data.constants.ConstantsForSpring;
import ge.tbcacademy.data.util.CalendarHelper;
import ge.tbcacademy.data.util.Marshall;
import ge.tbcacademy.data.util.Unmarshall;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import org.tempuri.*;

import javax.xml.datatype.XMLGregorianCalendar;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SoapDemoSteps {
    ObjectFactory objectFactory;
    FindPerson findPerson;
    String body;
    Response response;
    String responseAsString;
    FindPersonResponse findPersonResponse;


    @Step("Creating SOAP request body")

    public SoapDemoSteps creatingBody() {
        objectFactory = new ObjectFactory();
        findPerson = objectFactory.createFindPerson();

        findPerson.withId("10");
        return this;
    }

    @Step("Sending SOAP request and getting response")
    public SoapDemoSteps findingPerson() {
        body = Marshall.marshallSoapRequest(findPerson);
        response = given()
                .header("Content-Type", "text/xml; charset=utf-8")
                .header("SOAPAction", "http://tempuri.org/SOAP.Demo.FindPerson")
                .body(body)
                .filter(new AllureRestAssured())
                .post(ConstantsForSoapDemo.URI)
                .then().extract().response();

        return this;
    }

    @Step("Validating presence of FindPersonResult element")
    public SoapDemoSteps validatingNotNull() {
        responseAsString = response.asString();

        findPersonResponse = Unmarshall.unmarshallResponse(responseAsString, FindPersonResponse.class);

        assertThat(findPersonResponse.getFindPersonResult(), notNullValue());

        return this;
    }

    @Step("Validating person's name")
    public SoapDemoSteps validatingName() {

        assertThat(extractNameAndSurname(findPersonResponse.getFindPersonResult().getName())[1], equalTo(ConstantsForSoapDemo.FIRST_NAME));
        return this;
    }


    @Step("Validating SSN value")
    private String[] extractNameAndSurname(String fullName) {
        String[] names = new String[2];
        String[] parts = fullName.split(",\\s*");

        if (parts.length == 2) {
            names[0] = parts[0];
            names[1] = parts[1];
        }
        return names;
}
    @Step("Validating SSN value")

    public SoapDemoSteps validatingSSNValue(){
        assertThat(findPersonResponse.getFindPersonResult().getSSN(), equalTo(ConstantsForSoapDemo.SSN));
    return this;
    }
    @Step("Validating number of characters in SSN")
    public SoapDemoSteps validatingNumbOfCharacters(){
        assertThat(findPersonResponse.getFindPersonResult().getSSN().length(), equalTo(11));
    return this;

    }
    @Step("Checking if SSN follows the pattern NNN-NN-NNNN")
    public SoapDemoSteps validatingDigitsWithPattern(){
        String ssnPattern = "\\d{3}-\\d{2}-\\d{4}";
        assertThat(findPersonResponse.getFindPersonResult().getSSN(), matchesPattern(ssnPattern));
    return this;
    }
    @Step("Validating date of birth (DOB)")
    public SoapDemoSteps validatingDOB(){
        XMLGregorianCalendar date = CalendarHelper.createXMLGregorianCalendar(2013,12,12);
        assertThat(findPersonResponse.getFindPersonResult().getDOB(), equalTo(date));
        return this;
    }
    @Step("Validating presence of favorite colors [Orange, Red]")
    public SoapDemoSteps validatingFavoriteColors(){
        List<String> colors = new ArrayList<>();
        colors.add(ConstantsForSoapDemo.ORANGE);
        colors.add(ConstantsForSoapDemo.RED);

        assertThat(findPersonResponse.getFindPersonResult().getFavoriteColors().getFavoriteColorsItem(), equalTo(colors));
        return this;
    }
    @Step("Validating person's last name")
    public SoapDemoSteps validatingLastName(){
        assertThat(extractNameAndSurname(findPersonResponse.getFindPersonResult().getName())[0], equalTo(ConstantsForSoapDemo.SURNAME));
        return this;
    }
    @Step("Checking if person has Red as one of favorite colors and its index is 2")
    public boolean validateFavoriteColor() {

        return findPersonResponse.getFindPersonResult().getFavoriteColors().getFavoriteColorsItem().stream()
                .skip(2)
                .findFirst()
                .filter(color -> color.equals(ConstantsForSoapDemo.RED))
                .isPresent();
    }
    @Step("Validating boolean result of favorite color check")
    public SoapDemoSteps booleanValidation(){
        assertThat(validateFavoriteColor(), is(false));
        return this;
    }
    @Step("Checking if office zip code is not the same as home zip code")
    public boolean validateZipCode(){
        return Stream.of(
                findPersonResponse.getFindPersonResult().getOffice().getZip(),
                findPersonResponse.getFindPersonResult().getHome().getZip()).distinct().count() == 2;
    }
    @Step("Validating boolean result of zip code check")
    public SoapDemoSteps zipCodeBooleanValidation(){
        assertThat(validateZipCode(), is(true));
        return this;
    }
}
