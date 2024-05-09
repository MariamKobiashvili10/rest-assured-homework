package ge.tbcacademy;

import ge.tbcacademy.data.constants.ConstantsForBookingUpdate;
import ge.tbcacademy.data.steps.restful.BookingUpdateSteps;
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
        bookingUpdateSteps.
                updateBooking(ConstantsForBookingUpdate.BOOKING_ID)
                .logIfStatusCodeIsProvided(bookingUpdateSteps.statusCodeExtracted());
    }
}