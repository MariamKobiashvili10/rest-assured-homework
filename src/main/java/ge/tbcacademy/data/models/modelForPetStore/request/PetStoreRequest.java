package ge.tbcacademy.data.models.modelForPetStore.request;

import ge.tbcacademy.data.constants.ConstantsForPetStore3;
import ge.tbcacademy.data.models.modelForPetStore.rresponse.PostPetStore;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PetStoreRequest {

    public Response createOrder(PostPetStore order) {
        return given()
                .baseUri(ConstantsForPetStore3.BASE_URI)
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .body(order)
                .when()
                .post("/store/order");
    }
}
