package ge.tbcacademy.data.steps;

import ge.tbcacademy.data.constants.ConstantsForPetStore;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import lombok.Getter;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import pet.store.v3.api.PetApi;
import pet.store.v3.api.StoreApi;
import pet.store.v3.invoker.ApiClient;
import pet.store.v3.invoker.JacksonObjectMapper;
import pet.store.v3.model.Category;
import pet.store.v3.model.Order;
import pet.store.v3.model.Pet;
import pet.store.v3.model.Tag;

import java.time.OffsetDateTime;
import java.util.Collections;

import static io.restassured.config.ObjectMapperConfig.objectMapperConfig;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static pet.store.v3.invoker.ResponseSpecBuilders.shouldBeCode;
import static pet.store.v3.invoker.ResponseSpecBuilders.validatedWith;

public class PetStoreV3Steps {
        ApiClient apiClient;
        Order orderBody;
        Order order;
        Pet petBody;
        Response response;
        private StoreApi.PlaceOrderOper placeOrderOper;

        @Step("getting an api of petstore")
        public PetStoreV3Steps gettingApi(){
            apiClient = ApiClient.api(ApiClient.Config.apiConfig()
                    .reqSpecSupplier(() -> new RequestSpecBuilder()
                            .log(LogDetail.ALL)
                            .setConfig(RestAssuredConfig.config()
                                    .objectMapperConfig(objectMapperConfig()
                                            .defaultObjectMapper(JacksonObjectMapper.jackson())))
                            .addFilter(new ErrorLoggingFilter())
                            .addFilter(new AllureRestAssured())
                            .setBaseUri(ConstantsForPetStore.BASE_URI)));
            return this;
        }
        @Step("Creating Order body ")
        public PetStoreV3Steps creatingOrderBody() {
            orderBody = new Order().
                    id(ConstantsForPetStore.ID)
                    .petId(ConstantsForPetStore.PET_ID)
                    .quantity(ConstantsForPetStore.QUANTITY)
                    .shipDate(OffsetDateTime.parse("2024-05-13T11:20:17.962Z"))
                    .status(Order.StatusEnum.APPROVED)
                    .complete(true);
            return this;
        }

        @Step("Placing New order ")
        public PetStoreV3Steps placeOrder() {
            placeOrderOper = apiClient.store().placeOrder().body(orderBody);
            return this;

        }

        @Step("executing in pojo")
        public PetStoreV3Steps executeAsPojo(){
            order = placeOrderOper.executeAs(validatedWith(shouldBeCode(SC_OK))
                    .andThen(response -> response));
            return this;
        }

        @Step("Doing validations")
        public PetStoreV3Steps validations(){
            assertThat(order.id(), equalTo(orderBody.id()));
            assertThat(order.petId(), equalTo(orderBody.petId()));
            assertThat(order.status(), equalTo(orderBody.status()));
            return this;
        }


        @Step("Creating body for adding new pet")
        public PetStoreV3Steps createPetBODY(){
            petBody = new Pet()
                    .id(ConstantsForPetStore.ID)
                    .name(ConstantsForPetStore.NAME)
                    .category(new Category()
                            .id(1L)
                            .name(ConstantsForPetStore.CATEGORIES_NAME))
                    .photoUrls(Collections.singletonList("string"))
                    .tags(Collections.singletonList(new Tag()
                            .id(4L)
                            .name("string")))
                    .status(Pet.StatusEnum.AVAILABLE);
            return this;
        }

        @Step("Posting New pet")
        public PetStoreV3Steps postNewPet(){
            response = apiClient.pet().addPet().body(petBody).execute((resp) -> resp);
            return this;
        }

        @Step("Doing validations about added pet")
        public PetStoreV3Steps doingValidations(){

            assertThat(response.getStatusCode(), equalTo(200));
            assertThat(response.jsonPath().getLong("id"), equalTo(petBody.id()));
            assertThat(response.jsonPath().getString("name"), equalTo(petBody.name()));
            return this;
        }


}
