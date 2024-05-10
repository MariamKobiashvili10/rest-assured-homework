package ge.tbcacademy.data.models.swapi.request;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PlanetRequest {

    public Response getPlanets() {
        return given()
                .baseUri("https://swapi.dev/api")
                .contentType(ContentType.JSON)
                .when()
                .get("planets/?format=json");
    }

}
