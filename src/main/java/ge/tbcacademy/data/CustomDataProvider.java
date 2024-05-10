package ge.tbcacademy.data;

import ge.tbcacademy.data.models.updateBookingRestful.Bookingdates;
import ge.tbcacademy.data.models.updateBookingRestful.UpdateBooking;
import org.testng.annotations.DataProvider;

public class CustomDataProvider {
    @DataProvider(name = "bookingData")
    public Object[][] bookingData() {
        UpdateBooking booking1 = new UpdateBooking("Mariam", "Kobiashvili", 125, true, new Bookingdates("2018-01-01", "2019-01-01"), "breakfast", 0.0, null);
        UpdateBooking booking2 =new UpdateBooking("Ana", "Maisuradze", 200, false, new Bookingdates("2018-01-02", "2019-01-02"), "lunch", 0.0, null);

        return new Object[][] {
                { booking1 },
                { booking2 }
        };
    }
}
