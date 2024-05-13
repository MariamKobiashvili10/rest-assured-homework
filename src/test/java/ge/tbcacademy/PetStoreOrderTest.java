package ge.tbcacademy;

import ge.tbcacademy.data.constants.ConstantsForPetStore;
import ge.tbcacademy.data.steps.petstore.PlaceNewOrderSteps;
import io.qameta.allure.*;
import org.testng.annotations.Test;


@Epic("Pet Store v3 Tests")
public class PetStoreOrderTest {
     PlaceNewOrderSteps placeNewOrder = new PlaceNewOrderSteps();
   @Feature("Order Creation")
   @Story("Create a new order on the Pet Store API")
   @Description("Test the creation of a new order on the Pet Store API by placing an order and asserting its details.")
   @Severity(SeverityLevel.NORMAL)

   @Test(description = "Creating New order on pet store")
        public void testOrderCreation() {
           placeNewOrder
                   .placeOrder(ConstantsForPetStore.ID, ConstantsForPetStore.PET_ID, ConstantsForPetStore.QUANTITY, true);
           placeNewOrder.asserting();

        }
}
