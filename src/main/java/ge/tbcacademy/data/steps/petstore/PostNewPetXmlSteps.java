package ge.tbcacademy.data.steps.petstore;

import ge.tbcacademy.data.constants.ConstantsForPetStore;
import ge.tbcacademy.data.models.xmlpetstore.Category;
import ge.tbcacademy.data.models.xmlpetstore.Pet;
import ge.tbcacademy.data.models.xmlpetstore.TagsItem;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PostNewPetXmlSteps {
    Pet petsResponse;
    Pet pet;
    @Step("Creating a new pet")
    public PostNewPetXmlSteps createBody() {
        pet = new Pet();
        pet.setId(ConstantsForPetStore.ID);
        pet.setName(ConstantsForPetStore.NAME);
        pet.setStatus(ConstantsForPetStore.STATUS);

        Category category = new Category();
        category.setId(1);
        category.setName(ConstantsForPetStore.CATEGORIES_NAME);
        pet.setCategory(category);

        TagsItem tag = new TagsItem();
        tag.setId(0);
        tag.setName("string");
        List<TagsItem> tagsList = new ArrayList<>();
        tagsList.add(tag);
        pet.setTags(tagsList);

        return this;
    }

    @Step("Posting the new pet to the API")
    public PostNewPetXmlSteps postingPet(){
         petsResponse = RestAssured.given()
                 .filter(new AllureRestAssured())
                .baseUri(ConstantsForPetStore.BASEURI)
                .contentType("application/json")
                .accept("application/xml")
                .body(pet)
                .when()
                .post(ConstantsForPetStore.PET_ENDPOINT)
                .then().log().body().statusCode(200).extract().as(Pet.class);
      return this;
    }
    @Step("Verifying the posted pet")

    public PostNewPetXmlSteps asserting(){
        assertThat(petsResponse.getId(), equalTo(ConstantsForPetStore.ID));
        assertThat(petsResponse.getStatus(),equalTo(ConstantsForPetStore.STATUS));
        assertThat(petsResponse.getName(), equalTo(ConstantsForPetStore.NAME));
        assertThat(petsResponse.getCategory().getName(), equalTo(ConstantsForPetStore.CATEGORIES_NAME));
        return this;
    }
}
