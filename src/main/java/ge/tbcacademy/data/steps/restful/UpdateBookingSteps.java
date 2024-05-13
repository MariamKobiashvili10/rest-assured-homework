package ge.tbcacademy.data.steps.restful;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ge.tbcacademy.data.utils.RequestSpecs;
import ge.tbcacademy.data.constants.ConstantsForBookingUpdate;
import ge.tbcacademy.data.models.updateBookingRestful.UpdateBooking;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;

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

    @Step("Updating booking with ID")
    public UpdateBooking updateBooking(UpdateBooking booking) {
        String responseBody = given(requestSpecification)
                .filter(new AllureRestAssured())
                .body(booking)
                .when()
                .put(ConstantsForBookingUpdate.BOOKING_ENDPOINT + ConstantsForBookingUpdate.BOOKING_ID)
                .then()
                .statusCode(200)
                .log().all().extract().asString();

        UpdateBooking response = null;
        try {
            response = objectMapper.readValue(responseBody, UpdateBooking.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        assertThat(response.getFirstNameChanged(), anyOf(equalTo(ConstantsForBookingUpdate.FIRST_NAME),
                equalTo(ConstantsForBookingUpdate.SECOND_FIRST_NAME)));


        return response;

    }
}
