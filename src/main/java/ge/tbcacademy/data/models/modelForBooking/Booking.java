package ge.tbcacademy.data.models.modelForBooking;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Booking{

	@JsonProperty("booking")
	private Booking booking;

	@JsonProperty("bookingid")
	private int bookingid;

	@JsonProperty("firstname")
	private String firstname;

	@JsonProperty("additionalneeds")
	private String additionalneeds;

	@JsonProperty("bookingdates")
	private Bookingdates bookingdates;

	@JsonProperty("totalprice")
	private int totalprice;

	@JsonProperty("depositpaid")
	private boolean depositpaid;

	@JsonProperty("lastname")
	private String lastname;

	public void setBooking(Booking booking){
		this.booking = booking;
	}

	public Booking getBooking(){
		return booking;
	}

	public void setBookingid(int bookingid){
		this.bookingid = bookingid;
	}

	public int getBookingid(){
		return bookingid;
	}

	public void setFirstname(String firstname){
		this.firstname = firstname;
	}

	public String getFirstname(){
		return firstname;
	}

	public void setAdditionalneeds(String additionalneeds){
		this.additionalneeds = additionalneeds;
	}

	public String getAdditionalneeds(){
		return additionalneeds;
	}

	public void setBookingdates(Bookingdates bookingdates){
		this.bookingdates = bookingdates;
	}

	public Bookingdates getBookingdates(){
		return bookingdates;
	}

	public void setTotalprice(int totalprice){
		this.totalprice = totalprice;
	}

	public int getTotalprice(){
		return totalprice;
	}

	public void setDepositpaid(boolean depositpaid){
		this.depositpaid = depositpaid;
	}

	public boolean isDepositpaid(){
		return depositpaid;
	}

	public void setLastname(String lastname){
		this.lastname = lastname;
	}

	public String getLastname(){
		return lastname;
	}

	@Override
 	public String toString(){
		return 
			"Booking{" + 
			"booking = '" + booking + '\'' + 
			",bookingid = '" + bookingid + '\'' + 
			",firstname = '" + firstname + '\'' + 
			",additionalneeds = '" + additionalneeds + '\'' + 
			",bookingdates = '" + bookingdates + '\'' + 
			",totalprice = '" + totalprice + '\'' + 
			",depositpaid = '" + depositpaid + '\'' + 
			",lastname = '" + lastname + '\'' + 
			"}";
		}
}