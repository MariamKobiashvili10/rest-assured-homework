package ge.tbcacademy;

import ge.tbcacademy.data.constants.ConstantsForPetStore;
import ge.tbcacademy.data.steps.petstore.PlaceNewOrderSteps;
import org.testng.annotations.Test;


public class PetStoreOrderTest {
     PlaceNewOrderSteps placeNewOrder = new PlaceNewOrderSteps();

        @Test
        public void testOrderCreation() {
           placeNewOrder
                   .placeOrder(ConstantsForPetStore.ID, ConstantsForPetStore.PET_ID, ConstantsForPetStore.QUANTITY, true);
           placeNewOrder.asserting();

        }
}
