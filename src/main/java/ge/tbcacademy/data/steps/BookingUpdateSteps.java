package ge.tbcacademy.data.steps;

import ge.tbcacademy.data.constants.ConstantsForBookingUpdate;
import ge.tbcacademy.data.RequestSpecs;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class BookingUpdateSteps {
    RequestSpecification requestSpecification;
    Response response;

    public BookingUpdateSteps() {
        requestSpecification = RequestSpecs.getBaseRequestSpecForBookingUpdate();
    }

    public JSONObject createBookingUpdateRequestBody() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("firstname", ConstantsForBookingUpdate.FIRST_NAME);
        requestBody.put("lastname", ConstantsForBookingUpdate.LAST_NAME);
        requestBody.put("totalprice", ConstantsForBookingUpdate.TOTAL_PRICE);
        requestBody.put("depositpaid", true);
        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin", ConstantsForBookingUpdate.CHECKIN);
        bookingDates.put("checkout", ConstantsForBookingUpdate.CHECKOUT);
        requestBody.put("bookingdates", bookingDates);
        requestBody.put("additionalneeds", ConstantsForBookingUpdate.ADDITIONAL_NEED);

        return requestBody;
    }


    public BookingUpdateSteps updateBooking(int bookingId, JSONObject requestBody) {
        response = given(requestSpecification)
                .body(requestBody.toString())
                .when()
                .put(ConstantsForBookingUpdate.BOOKING_ENDPOINT + bookingId);
        return this;
    }

    public int statusCodeExtracted() {
        return response.then().extract().statusCode();
    }

    public BookingUpdateSteps logIfStatusCodeIsProvided(int statusCode) {
        if (response.getStatusCode() == statusCode){
            response.then().log().body();
        }
        return this;
}
}