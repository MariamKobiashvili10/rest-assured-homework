package ge.tbcacademy;

import ge.tbcacademy.data.CustomDataProvider;
import ge.tbcacademy.data.models.updateBookingRestful.UpdateBooking;
import ge.tbcacademy.data.steps.restful.UpdateBookingSteps;
import io.qameta.allure.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
@Epic("Booking Tests")
public class UpdateBookingm {
    UpdateBookingSteps updateBookingSteps;
    @BeforeClass
    public void setUp(){
        updateBookingSteps = new UpdateBookingSteps();
    }
    @Feature("Update Booking")
    @Story("Validate updating booking details")
    @Description("Test updating booking details on the Restful Booker API")
    @Severity(SeverityLevel.NORMAL)

    @Test(dataProvider = "bookingData", dataProviderClass = CustomDataProvider.class,description = "Updating booking and validating things")
    public void testUpdateBooking(UpdateBooking booking) {
        updateBookingSteps.updateBooking(booking);
    }

}
