package ge.tbcacademy;

import ge.tbcacademy.data.steps.petstore.PlaceNewOrderSteps;
import ge.tbcacademy.data.steps.petstore.PostNewPetXmlSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PostNewPet {
    PostNewPetXmlSteps postNewPetXmlSteps;

    @BeforeClass
    public void setUp(){
        postNewPetXmlSteps = new PostNewPetXmlSteps();
    }
    @Test
    public void postingNewPet(){
        postNewPetXmlSteps.postingPet()
                .asserting();
    }
}
