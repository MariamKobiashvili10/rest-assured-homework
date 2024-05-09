package ge.tbcacademy.data.steps.bookStore;

import ge.tbcacademy.data.constants.ConstantsForBookStore;
import ge.tbcacademy.data.RequestSpecs;
import ge.tbcacademy.data.models.bookstore.Books;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
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
        Books books = booksResponse.then().extract().as(Books.class);
        assertThat(books.getBooks(), everyItem(hasProperty("pages", lessThan(1000))));
        return this;
    }

    public BookStoreSteps validatingAuthors(String author1, String author2) {
        Books books = booksResponse.then().extract().as(Books.class);

        assertThat(books.getBooks().get(0).getAuthor(), equalTo(author1));
        assertThat(books.getBooks().get(1).getAuthor(), equalTo(author2));
        return this;
    }
    public BookStoreSteps validatinglastTwoAuthors(String author1, String author2) {
        Books books = booksResponse.then().extract().as(Books.class);

        int totalBooks = books.getBooks().size();

        assertThat(books.getBooks().get(totalBooks - 1).getAuthor(), equalTo(author1));
        assertThat(books.getBooks().get(totalBooks - 2).getAuthor(), equalTo(author2));

        return this;
    }
    }

