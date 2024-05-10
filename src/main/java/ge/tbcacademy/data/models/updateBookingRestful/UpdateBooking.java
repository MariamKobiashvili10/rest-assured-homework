package ge.tbcacademy.data.models.updateBookingRestful;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "firstname", "lastname", "totalprice", "depositpaid", "bookingdates", "additionalneeds", "passportNo" })
public class UpdateBooking {
	@JsonProperty("firstname")
	private String firstName;

	@JsonProperty("lastname")
	private String lastName;

	@JsonProperty("totalprice")
	private int totalPrice;

	@JsonProperty("depositpaid")
	private boolean depositPaid;


	@JsonProperty("bookingdates")
	private Bookingdates bookingDates;

	@JsonProperty("additionalneeds")
	private String additionalNeeds;


	@JsonIgnore
	private double salePrice;

	@JsonProperty("passportNo")
	private String passportNumber;

}