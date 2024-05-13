package ge.tbcacademy;

import ge.tbcacademy.data.steps.petstore.PlaceNewOrderSteps;
import ge.tbcacademy.data.steps.petstore.PostNewPetXmlSteps;
import io.qameta.allure.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Epic("Pet Store adding new pet")
public class PostNewPet {
    PostNewPetXmlSteps postNewPetXmlSteps;

    @BeforeClass
    public void setUp(){
        postNewPetXmlSteps = new PostNewPetXmlSteps();
    }


    @Feature("Pet Creation")
    @Story("Post a new pet to the Pet Store API")
    @Description("Test the creation of a new pet on the Pet Store API by posting a pet and asserting its details.")
    @Severity(SeverityLevel.NORMAL)

    @Test(description = "Adding new pet to petstore api")
    public void postingNewPet(){
        postNewPetXmlSteps
                .createBody()
                .postingPet()
                .asserting();
    }
}
