package ge.tbcacademy.data.steps.restful;

import ge.tbcacademy.data.constants.ConstantsForBookingUpdate;
import ge.tbcacademy.data.RequestSpecs;
import ge.tbcacademy.data.models.modelForBooking.Booking;
import ge.tbcacademy.data.models.modelForBooking.Bookingdates;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BookingUpdateSteps {
    RequestSpecification requestSpecification;
    Response response;

    public BookingUpdateSteps() {
        requestSpecification = RequestSpecs.getBaseRequestSpecForBookingUpdate();
    }

    public Booking createBookingUpdateRequestBody() {
        Booking booking = new Booking();
        booking.setFirstname(ConstantsForBookingUpdate.FIRST_NAME);
        booking.setLastname(ConstantsForBookingUpdate.LAST_NAME);
        booking.setTotalprice(ConstantsForBookingUpdate.TOTAL_PRICE);
        booking.setDepositpaid(true);

        Bookingdates bookingDates = new Bookingdates();
        bookingDates.setCheckin(ConstantsForBookingUpdate.CHECKIN);
        bookingDates.setCheckout(ConstantsForBookingUpdate.CHECKOUT);

        booking.setBookingdates(bookingDates);

        booking.setAdditionalneeds(ConstantsForBookingUpdate.ADDITIONAL_NEED);

        return booking;
    }


    public BookingUpdateSteps updateBooking(int bookingId) {
            response = given(requestSpecification)
                    .body(createBookingUpdateRequestBody())
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