package ge.tbcacademy.data.steps;

import ge.tbcacademy.data.constants.ConstantsForBookStore;
import ge.tbcacademy.data.RequestSpecs;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class BookStoreSteps {
    RequestSpecification requestSpecification;
    Response booksResponse;

    public BookStoreSteps() {
        requestSpecification = RequestSpecs.getBaseRequestForBookStore();
    }

    public BookStoreSteps getSpecificResponse(){
        booksResponse = given(requestSpecification)
                .when()
                .get(ConstantsForBookStore.BOOKING_ENDPOINT);
        return this;

    }
    public BookStoreSteps validatingPages() {
        booksResponse
                .then()
                .assertThat()
                .body("books.pages", everyItem(lessThan(1000)));
        return this;
    }
    public BookStoreSteps validatingAuthors(String author1, String author2){
        booksResponse
                .then()
                .assertThat()
                .body("books.author[0]", equalTo(author1))
                .body("books.author[1]", equalTo(author2));
        return this;
    }
}
