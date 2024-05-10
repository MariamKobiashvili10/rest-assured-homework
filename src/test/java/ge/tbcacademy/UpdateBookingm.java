package ge.tbcacademy;

import ge.tbcacademy.data.CustomDataProvider;
import ge.tbcacademy.data.models.updateBookingRestful.UpdateBooking;
import ge.tbcacademy.data.steps.restful.UpdateBookingSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UpdateBookingm {
    UpdateBookingSteps updateBookingSteps;
    @BeforeClass
    public void setUp(){
        updateBookingSteps = new UpdateBookingSteps();
    }
    @Test(dataProvider = "bookingData", dataProviderClass = CustomDataProvider.class)
    public void testUpdateBooking(UpdateBooking booking) {
        updateBookingSteps.updateBooking(booking);
    }

}
