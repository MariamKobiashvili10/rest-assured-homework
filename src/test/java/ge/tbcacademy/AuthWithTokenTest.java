package ge.tbcacademy;


import com.fasterxml.jackson.core.JsonProcessingException;
import ge.tbcacademy.data.models.modelForBooking.Booking;
import ge.tbcacademy.data.steps.restful.RestfulBookerSteps;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class AuthWithTokenTest {
    RestfulBookerSteps restfulBookerSteps;

    @BeforeClass
    public void getToken() {
       restfulBookerSteps = new RestfulBookerSteps();
       restfulBookerSteps.getToken();
    }


    @Test(priority = 1)
    public void createBookingTest()throws JsonProcessingException {
        Booking createBookingBody = restfulBookerSteps.createBookingUpdateRequestBody();
        restfulBookerSteps.createBooking(createBookingBody);
    }
    @Test(priority = 2)
    public void updateBooking(){
        restfulBookerSteps.updateBooking();
    }
    @Test(priority = 3)
    public void deleteBooking(){
        restfulBookerSteps.deleteBooking();
    }
}