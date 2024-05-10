package ge.tbcacademy.data.models.modelForPetStore.request;

import ge.tbcacademy.data.models.modelForPetStore.rresponse.PostPetStore;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PetStoreRequest {
    private final String BASE_URI = "https://petstore3.swagger.io/api/v3";

    public Response createOrder(PostPetStore order) {
        return given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .body(order)
                .when()
                .post("/store/order");
    }
}
