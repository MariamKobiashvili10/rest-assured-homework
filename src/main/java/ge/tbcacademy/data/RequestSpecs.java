package ge.tbcacademy.data;

import ge.tbcacademy.data.constants.ConstantsForBookStore;
import ge.tbcacademy.data.constants.ConstantsForBookingUpdate;
import ge.tbcacademy.data.constants.ConstantsForPetStore;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.*;
import io.restassured.specification.*;

import static io.restassured.RestAssured.given;

public class RequestSpecs {
    public static RequestSpecification getBaseRequestSpecForBookingUpdate() {
        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .setBaseUri(ConstantsForBookingUpdate.BASE_URI)
                .setContentType(ContentType.JSON)
                .build();
        return given(requestSpecification).header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=");
    }

    public static RequestSpecification getBaseRequestForBookStore() {
        return new RequestSpecBuilder()
                .setBaseUri(ConstantsForBookStore.BOOKSTORE_BASE_URI)
                .setContentType(ContentType.JSON)
                .build();
    }

    public static RequestSpecification getBaseRequestForPetStore() {
        return new RequestSpecBuilder()
                .setBaseUri(ConstantsForPetStore.PETSTORE_BASE_URI)
                .setContentType(ContentType.JSON)
                .build();
    }

    public static RequestSpecification getBaseRequestForAuthorizingBooking() {
        return new RequestSpecBuilder()
                .setBaseUri(ConstantsForBookingUpdate.BASE_URI)
                .setContentType(ContentType.JSON)
                .build();
    }
}