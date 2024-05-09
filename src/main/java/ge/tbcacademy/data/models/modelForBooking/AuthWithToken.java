package ge.tbcacademy.data.models.modelForBooking;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthWithToken{

	@JsonProperty("password")
	private String password;

	@JsonProperty("username")
	private String username;

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	@Override
 	public String toString(){
		return 
			"AuthWithToken{" + 
			"password = '" + password + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}
}