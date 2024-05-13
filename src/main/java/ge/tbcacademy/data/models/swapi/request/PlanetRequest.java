package ge.tbcacademy.data.models.swapi.request;

import com.beust.ah.A;
import ge.tbcacademy.data.constants.ConstantsForSwapi;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PlanetRequest {

    public Response getPlanets() {
        return given()
                .filter(new AllureRestAssured())
                .baseUri(ConstantsForSwapi.BASE_URI)
                .contentType(ContentType.JSON)
                .when()
                .get(ConstantsForSwapi.PLANETS_ENDPOINT);
    }

}
