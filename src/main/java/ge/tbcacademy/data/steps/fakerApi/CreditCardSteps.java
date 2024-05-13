package ge.tbcacademy.data.steps.fakerApi;

import ge.tbcacademy.data.utils.RequestSpecs;
import ge.tbcacademy.data.constants.FakerApiConstants;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreditCardSteps {
RequestSpecification requestSpecification;
    String responseBody;

    public CreditCardSteps(){
        requestSpecification = RequestSpecs.getBaseRequestForFakerApi();
    }
    @Step("Call Credit Card API and Get Response Body")
    public CreditCardSteps callCreditCardApiAndGetResponseBody() {

        Response response = RestAssured.given(requestSpecification)
                .filter(new AllureRestAssured())
                .when()
                .get(FakerApiConstants.ENDPOINT);

        responseBody = response.getBody().asString();

        return this;
    }
    @Step("Validate Schema")
    public CreditCardSteps validateSchema() {
        assertThat(responseBody, matchesJsonSchemaInClasspath("credit_cards_schema.json"));
        return this;

    }
}
