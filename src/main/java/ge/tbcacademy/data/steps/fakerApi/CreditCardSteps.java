package ge.tbcacademy.data.steps.fakerApi;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.json.JSONObject;

import javax.xml.validation.Schema;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreditCardSteps {

    String responseBody;
    public String callCreditCardApiAndGetResponseBody() {
        RestAssured.baseURI = "https://fakerapi.it/api/v1";

        Response response = RestAssured.given()// Set the query parameter
                .get("/credit_cards?_quantity=2");

        responseBody = response.getBody().asString();

        return responseBody;
    }
    public CreditCardSteps validateSchema() {
        assertThat(callCreditCardApiAndGetResponseBody(), matchesJsonSchemaInClasspath("credit_cards_schema.json"));
        return this;

    }
}
