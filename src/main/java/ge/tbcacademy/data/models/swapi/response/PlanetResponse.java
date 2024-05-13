package ge.tbcacademy.data.models.swapi.response;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


public record PlanetResponse(

	@JsonProperty("next")
	 String next,

	@JsonProperty("previous")
	 Object previous,

	@JsonProperty("count")
	 int count,

	@JsonProperty("results")
	 List<ResultsItem> results

	){

}