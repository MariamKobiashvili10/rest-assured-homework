package ge.tbcacademy.data.utils;

import ge.tbcacademy.data.constants.ConstantsForBookingUpdate;
import ge.tbcacademy.data.constants.FakerApiConstants;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.*;
import io.restassured.specification.*;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

public class RequestSpecs {
    public static RequestSpecification getBaseRequestSpecForBookingUpdate() {
        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .setBaseUri(ConstantsForBookingUpdate.BASE_URI)
                .setContentType(ContentType.JSON)
                .build();
        return given(requestSpecification).header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=");
    }
    public static RequestSpecification getBaseRequestForFakerApi(){
        return new RequestSpecBuilder()
                .setBaseUri(FakerApiConstants.BASEURI)
                .setContentType(ContentType.JSON)
                .build();

    }


}