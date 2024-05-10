package ge.tbcacademy.data.steps.restful;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ge.tbcacademy.data.RequestSpecs;
import ge.tbcacademy.data.constants.ConstantsForBookingUpdate;
import ge.tbcacademy.data.models.updateBookingRestful.UpdateBooking;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UpdateBookingSteps {

    RequestSpecification requestSpecification ;

    ObjectMapper objectMapper;

    public UpdateBookingSteps(){
        requestSpecification = RequestSpecs.getBaseRequestSpecForBookingUpdate();
        objectMapper = new ObjectMapper();

    }
    public UpdateBooking updateBooking(UpdateBooking booking) {
        String responseBody = given(requestSpecification)
                .body(booking)
                .when()
                .put("/booking/" + ConstantsForBookingUpdate.BOOKING_ID)
                .then()
                .statusCode(200)
                .log().all().extract().asString();
        UpdateBooking response = null;
        try {
            response = objectMapper.readValue(responseBody, UpdateBooking.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        assertThat(response.getFirstName(), notNullValue());

        System.out.println("Deserialized Response" + response);

        return response;

    }
}
