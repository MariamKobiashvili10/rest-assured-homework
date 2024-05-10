package ge.tbcacademy.data.steps.petstore;

import ge.tbcacademy.data.constants.ConstantsForPetStore;
import ge.tbcacademy.data.models.modelForPetStore.request.PetStoreRequest;
import ge.tbcacademy.data.models.modelForPetStore.rresponse.PostPetStore;
import io.restassured.response.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class PlaceNewOrderSteps {
    private final PetStoreRequest petStoreRequest = new PetStoreRequest();

    public PostPetStore placeOrder(int id, int petId, int quantity, boolean complete) {
        PostPetStore order = new PostPetStore()
                .id(id)
                .petId(petId)
                .quantity(quantity)
                .complete(complete);

        Response orderResponse = petStoreRequest.createOrder(order);
        orderResponse.then().log().all();

        return orderResponse.as(PostPetStore.class);

    }
    public PlaceNewOrderSteps asserting(){
        PostPetStore placeOrder = placeOrder(ConstantsForPetStore.ID, ConstantsForPetStore.PET_ID, ConstantsForPetStore.QUANTITY, true);
        assertThat(placeOrder.id(), equalTo(ConstantsForPetStore.ID));
        assertThat(placeOrder.complete(), is(true));
        assertThat(placeOrder.petId(), is(ConstantsForPetStore.PET_ID));
        assertThat(placeOrder.quantity(), is(ConstantsForPetStore.QUANTITY));
        return this;
    }
}
