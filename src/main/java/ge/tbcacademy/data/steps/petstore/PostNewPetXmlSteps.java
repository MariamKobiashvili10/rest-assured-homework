package ge.tbcacademy.data.steps.petstore;

import ge.tbcacademy.data.constants.ConstantsForPetStore;
import ge.tbcacademy.data.models.xmlpetstore.Category;
import ge.tbcacademy.data.models.xmlpetstore.Pet;
import ge.tbcacademy.data.models.xmlpetstore.TagsItem;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import org.hamcrest.Matchers;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.path.xml.XmlPath.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PostNewPetXmlSteps {
    Pet petsResponse;
    public Pet createBody() {
        Pet pet = new Pet();


        pet.setId(ConstantsForPetStore.ID);

        pet.setName("doggie");


        pet.setStatus(ConstantsForPetStore.STATUS);

        Category category = new Category();
        category.setId(1);
        category.setName("Dogs");
        pet.setCategory(category);

        TagsItem tag = new TagsItem();
        tag.setId(0);
        tag.setName("string");
        List<TagsItem> tagsList = new ArrayList<>();
        tagsList.add(tag);
        pet.setTags(tagsList);

        return pet;
    }

    public PostNewPetXmlSteps postingPet(){
         petsResponse = RestAssured.given()
                .baseUri("https://petstore3.swagger.io/api/v3")
                .contentType("application/json")
                .accept("application/xml")
                .body(createBody())
                .when()
                .post("/pet")
                .then().log().body().statusCode(200).extract().as(Pet.class);
      return this;
    }
    public PostNewPetXmlSteps asserting(){
        assertThat(petsResponse.getId(), equalTo(ConstantsForPetStore.ID));
        assertThat(petsResponse.getStatus(),equalTo(ConstantsForPetStore.STATUS));
        return this;
    }
}
