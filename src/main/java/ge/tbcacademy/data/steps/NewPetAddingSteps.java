package ge.tbcacademy.data.steps;

import ge.tbcacademy.data.constants.ConstantsForPetStore;
import ge.tbcacademy.data.RequestSpecs;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class NewPetAddingSteps {
    RequestSpecification requestSpecification;
    Response petsResponse;

    public NewPetAddingSteps() {
        requestSpecification = RequestSpecs.getBaseRequestForPetStore();
    }

    public JSONObject createParametrizedRequestBody() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("id", ConstantsForPetStore.ID);

        JSONObject category = new JSONObject();
        category.put("id", ConstantsForPetStore.ID);
        category.put("name", ConstantsForPetStore.NAME);
        requestBody.put("category", category);

        requestBody.put("name", ConstantsForPetStore.NAME);

        JSONArray photoUrls = new JSONArray();
        photoUrls.put("string");
        requestBody.put("photoUrls", photoUrls);

        JSONArray tags = new JSONArray();
        JSONObject tag = new JSONObject();
        tag.put("id", ConstantsForPetStore.ID);
        tag.put("name", ConstantsForPetStore.NAME);
        tags.put(tag);
        requestBody.put("tags", tags);

        requestBody.put("status", ConstantsForPetStore.BASE_STATUS);

        return requestBody;

    }

    public NewPetAddingSteps addingPet(JSONObject jsonBodyPet) {
        petsResponse = given(requestSpecification)
                .contentType(ContentType.JSON)
                .body(jsonBodyPet.toString())
                .when()
                .post(ConstantsForPetStore.PETS_ENDPOINT);
        return this;
    }

    public NewPetAddingSteps ensuringStatusCode() {
        petsResponse.then().assertThat().statusCode(200);
        return this;
    }

    public NewPetAddingSteps ensuringPetWasAdded() {
        petsResponse.then().assertThat()
                .body("id", Matchers.is(ConstantsForPetStore.ID))
                .body("name", equalTo(ConstantsForPetStore.NAME))
                .body("status", equalTo(ConstantsForPetStore.BASE_STATUS));
        return this;
    }

    public NewPetAddingSteps findByStatus() {
        petsResponse = given(requestSpecification)
                .queryParam("status", ConstantsForPetStore.BASE_STATUS)
                .when()
                .get(ConstantsForPetStore.FIND_BY_STATUS_ENDPOINT)
                .thenReturn();
        return this;
    }
    public NewPetAddingSteps validateResponseContainsId(int id) {
        petsResponse.then().assertThat()
                .body("id", Matchers.hasItem(id));
        return this;
    }

    public NewPetAddingSteps validatePetObject(JSONObject expectedPet) throws JSONException {
        String responseBodyString = petsResponse.getBody().asString();

        JSONArray responseBody = new JSONArray(responseBodyString);

        for (Object obj : responseBody) {
            JSONObject pet = (JSONObject) obj;
            if (pet.getInt("id") == expectedPet.getInt("id")) {
                assertThat(pet.toString(), equalTo(expectedPet.toString()));
                break;
            }
        }

        return this;
    }
    public NewPetAddingSteps updatePetWithFormData(int petId) {
        petsResponse = given(requestSpecification)
                .contentType(ContentType.URLENC)
                .formParam("name", ConstantsForPetStore.PETS_NEW_NAME)
                .formParam("status", ConstantsForPetStore.UPDATED_STATUS)
                .when()
                .post(ConstantsForPetStore.PETS_ENDPOINT + petId);

        return this;
    }
    public NewPetAddingSteps getNewPetWithId(int petId){
        petsResponse = given(requestSpecification)
                .when()
                .get(ConstantsForPetStore.PETS_ENDPOINT + petId);

        return this;

    }
    public NewPetAddingSteps validateUpdatedNameAndStatus(){
        petsResponse.then().assertThat()
                .body("name", equalTo(ConstantsForPetStore.PETS_NEW_NAME))
                .body("status", equalTo(ConstantsForPetStore.UPDATED_STATUS));

        return this;
    }
}
