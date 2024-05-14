package ge.tbcacademy;

import ge.tbcacademy.data.steps.PetStoreV3Steps;
import io.qameta.allure.*;
import lombok.Getter;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

@Epic("Pet Store Tests")
public class PetStoreTests {
    PetStoreV3Steps petStoreV3Steps;

    @BeforeSuite
    public void creatingApi(){
        petStoreV3Steps = new PetStoreV3Steps();
        petStoreV3Steps.gettingApi();
    }


    @Description("This test verifies the functionality of placing an order.")
    @Story("Placing new order on petstore using openapi")
    @Severity(SeverityLevel.NORMAL)
    @Test(description =  "placing new order",priority = 1)
    public void placingOrder() {
        petStoreV3Steps.creatingOrderBody()
                .placeOrder()
                .executeAsPojo()
                .validations();
    }

    @Description("This test verifies the functionality of adding a new pet.")
    @Story("Adding new pet On petstore ")
    @Severity(SeverityLevel.NORMAL)
    @Test(description =  "adding new pet operation",priority = 2)
    public void addingNewPet(){
         petStoreV3Steps.createPetBODY()
                .postNewPet()
                .doingValidations();
    }

}
