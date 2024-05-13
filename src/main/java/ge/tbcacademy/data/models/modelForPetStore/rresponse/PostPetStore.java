package ge.tbcacademy.data.models.modelForPetStore.rresponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true, fluent = true)
@JsonPropertyOrder({"id", "petId", "quantity", "shipDate", "complete", "status"})
public class PostPetStore {
	@NotNull
	@JsonProperty("id")
	private int id;

	@NotNull
	@JsonProperty("petId")
	private int petId;

	@NotNull
	@JsonProperty("quantity")
	private int quantity;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("shipDate")
	private String shipDate;

	@NotNull
	@JsonProperty("complete")
	private boolean complete;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("status")
	private String status;
}