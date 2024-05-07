package ge.tbcacademy;

import ge.tbcacademy.data.ConstantsForBookingUpdate;
import ge.tbcacademy.data.steps.BookingUpdateSteps;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BookingUpdateTest {
    public static BookingUpdateSteps bookingUpdateSteps;

    @BeforeClass
    public static void beforeClass() {
        bookingUpdateSteps = new BookingUpdateSteps();
    }

    @Test
    public void testBookingUpdate() {
        JSONObject requestBody = bookingUpdateSteps.createBookingUpdateRequestBody();

        bookingUpdateSteps.
                updateBooking(ConstantsForBookingUpdate.BOOKING_ID, requestBody)
                .logIfStatusCodeIsProvided(bookingUpdateSteps.statusCodeExtracted());
    }
}