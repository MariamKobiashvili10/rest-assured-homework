package ge.tbcacademy.data.models.updateBookingRestful;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({ "firstname", "lastname", "totalprice", "depositpaid", "bookingdates", "additionalneeds", "passportNo" })
public class UpdateBooking {
	@JsonProperty("firstname")
	private String firstNameChanged;

	@JsonProperty("lastname")
	private String lastNameChanged;

	@JsonProperty("totalprice")
	private int totalPriceChanged;

	@JsonProperty("depositpaid")
	private boolean depositPaidChanged;


	@JsonProperty("bookingdates")
	private Bookingdates bookingDatesChanged;

	@JsonProperty("additionalneeds")
	private String additionalNeedsChanged;


	@JsonIgnore
	private double salePrice;

	@JsonProperty("passportNo")
	private String passportNumber;

}