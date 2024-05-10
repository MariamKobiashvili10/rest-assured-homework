package ge.tbcacademy.data;

import ge.tbcacademy.data.constants.ConstantsForBookingUpdate;
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


}