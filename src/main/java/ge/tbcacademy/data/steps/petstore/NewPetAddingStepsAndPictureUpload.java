package ge.tbcacademy.data.steps.petstore;

import ge.tbcacademy.data.constants.ConstantsForPetStore;
import ge.tbcacademy.data.RequestSpecs;
import ge.tbcacademy.data.models.modelForPetStore.Category;
import ge.tbcacademy.data.models.modelForPetStore.Pet;
import ge.tbcacademy.data.models.modelForPetStore.TagsItem;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;

import java.io.File;
import java.util.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class NewPetAddingStepsAndPictureUpload {
    RequestSpecification requestSpecification;
    Response petsResponse;

    public NewPetAddingStepsAndPictureUpload() {
        requestSpecification = RequestSpecs.getBaseRequestForPetStore();
    }

    public Pet createParametrizedRequestBody() {
        Pet requestBody = new Pet();

        requestBody.setId(ConstantsForPetStore.ID);
        Category category = new Category();
        category.setId(ConstantsForPetStore.ID);
        category.setName(ConstantsForPetStore.NAME);
        requestBody.setCategory(category);

        requestBody.setName(ConstantsForPetStore.NAME);

        List<String> photoUrls = new ArrayList<>();
        photoUrls.add("string");
        requestBody.setPhotoUrls(photoUrls);

        List<TagsItem> tags = new ArrayList<>();
        TagsItem tag = new TagsItem();
        tag.setId(ConstantsForPetStore.ID);
        tag.setName(ConstantsForPetStore.NAME);
        tags.add(tag);
        requestBody.setTags(tags);

        requestBody.setStatus(ConstantsForPetStore.BASE_STATUS);

        return requestBody;
    }

    public NewPetAddingStepsAndPictureUpload addingPet() {
        petsResponse = given(requestSpecification)
                .contentType(ContentType.JSON)
                .body(createParametrizedRequestBody())
                .when()
                .post(ConstantsForPetStore.PETS_ENDPOINT);
        return this;
    }

    public NewPetAddingStepsAndPictureUpload ensuringStatusCode() {
        petsResponse.then().assertThat().statusCode(200);
        return this;
    }

    public NewPetAddingStepsAndPictureUpload ensurePetWasAdded() {
        Pet responseBodyPet = petsResponse.then().extract().as(Pet.class);

        assertThat(responseBodyPet.getId(), equalTo(ConstantsForPetStore.ID));
        assertThat(responseBodyPet.getName(), equalTo(ConstantsForPetStore.NAME));
        assertThat(responseBodyPet.getStatus(), equalTo(ConstantsForPetStore.BASE_STATUS));

        return this;
    }


    public NewPetAddingStepsAndPictureUpload findByStatus() {
        petsResponse = given(requestSpecification)
                .queryParam("status", createParametrizedRequestBody().getStatus())
                .when()
                .get(ConstantsForPetStore.FIND_BY_STATUS_ENDPOINT)
                .thenReturn();
        return this;
    }

    public NewPetAddingStepsAndPictureUpload validateResponseContainsId(int id) {
        String responseBody = petsResponse.getBody().asString();

        assertThat( responseBody, Matchers.containsString(String.valueOf(id)));

        return this;
    }

    public NewPetAddingStepsAndPictureUpload updatePetWithFormData(int petId) {
        Map<String, Object> formParams = new HashMap<>();
        formParams.put("name", ConstantsForPetStore.PETS_NEW_NAME);
        formParams.put("status", ConstantsForPetStore.UPDATED_STATUS);

        petsResponse = given(requestSpecification)
                .contentType(ContentType.URLENC)
                .formParams(formParams)
                .when()
                .post(ConstantsForPetStore.PETS_ENDPOINT + petId);

        return this;
    }

    public NewPetAddingStepsAndPictureUpload getNewPetWithId(int petId) {
        petsResponse = given(requestSpecification)
                .when()
                .get(ConstantsForPetStore.PETS_ENDPOINT + petId);

        return this;
    }

    public NewPetAddingStepsAndPictureUpload validateUpdatedNameAndStatus() {
        Pet updatedPet = petsResponse.then().extract().as(Pet.class);

        assertThat(updatedPet.getName(), equalTo(ConstantsForPetStore.PETS_NEW_NAME));
        assertThat(updatedPet.getStatus(), equalTo(ConstantsForPetStore.UPDATED_STATUS));

        return this;
    }

    public NewPetAddingStepsAndPictureUpload uploadingPicture() {
        File file = new File(ConstantsForPetStore.FILEPATH);
        petsResponse = given(requestSpecification)
                .log().all()
                .accept(ContentType.JSON)
                .contentType(ContentType.MULTIPART)
                .multiPart("file", file, ConstantsForPetStore.FILE_TYPE)
                .formParam("additionalMetadata", ConstantsForPetStore.METADATA)
                .when()
                .post(ConstantsForPetStore.PETS_ENDPOINT + ConstantsForPetStore.ID + ConstantsForPetStore.UPLOAD_PICTURE_ENDPOINT);
        return this;
    }

    public NewPetAddingStepsAndPictureUpload validatingMetadataInMessage() {
        String logMessage = petsResponse.getBody().asString();

        assertThat(logMessage, containsString(ConstantsForPetStore.METADATA));
        return this;
    }


    public NewPetAddingStepsAndPictureUpload validatingImageInMessage(){
        String logMessage = petsResponse.getBody().asString();

        assertThat(logMessage, containsString(ConstantsForPetStore.FILE_NAME));
        return this;
    }
    public NewPetAddingStepsAndPictureUpload validatingFileSize(){
        String logMessage = petsResponse.getBody().asString();

        assertThat(logMessage, containsString(ConstantsForPetStore.FILESIZE));
        return this;
    }
}
