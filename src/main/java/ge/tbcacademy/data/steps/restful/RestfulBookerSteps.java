package ge.tbcacademy.data.steps.restful;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ge.tbcacademy.data.constants.ConstantsForBookingUpdate;
import ge.tbcacademy.data.models.modelForBooking.AuthWithToken;
import ge.tbcacademy.data.models.modelForBooking.Booking;
import ge.tbcacademy.data.models.modelForBooking.Bookingdates;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class RestfulBookerSteps {
    public String token;
    ValidatableResponse response;
    Booking booking;
    Integer bookingId;

    public RestfulBookerSteps getToken(){
        RestAssured.baseURI = ConstantsForBookingUpdate.URL;
        AuthWithToken authRequest = new AuthWithToken();

        authRequest.setUsername("admin");
        authRequest.setPassword("password123");

        response = given()
                .contentType(ContentType.JSON)
                .body(authRequest)
                .when()
                .post("/auth")
                .then()
                .log().all();

        token = response.extract().body().jsonPath().getString("token");
        return this;
    }
    public Booking createBookingUpdateRequestBody() {
        booking = new Booking();
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
    public RestfulBookerSteps createBooking(Booking requestBody) throws JsonProcessingException {
        response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(ConstantsForBookingUpdate.BOOKING_ENDPOINT)
                .then()
                .log().all()
                .statusCode(200);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(response.extract().asString());


        bookingId = jsonNode.get("bookingid").asInt();
        return this;
    }

    public RestfulBookerSteps updateBooking(){
        booking.setFirstname(ConstantsForBookingUpdate.UPDATED_FIRST_NAME);
        booking.setLastname(ConstantsForBookingUpdate.UPDATED_LAST_NAME);
        booking.setBookingid(bookingId);

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Cookie", "token=" + token)
                .body(booking)
                .when()
                .log().all()
                .patch(ConstantsForBookingUpdate.BOOKING_ENDPOINT + bookingId)
                .then()
                .log().all()
                .statusCode(200);

        return this;
    }

    public RestfulBookerSteps deleteBooking(){
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Cookie", "token=" + token)
                .log().all()
                .when()
                .delete(ConstantsForBookingUpdate.BOOKING_ENDPOINT+bookingId).then().log().all().statusCode(201);
        return this;

    }
    }

