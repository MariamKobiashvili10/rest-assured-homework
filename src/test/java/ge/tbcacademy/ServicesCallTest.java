package ge.tbcacademy;
import ge.tbcacademy.data.constants.Constants;
import ge.tbcacademy.data.someDataProviderClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ServicesCallTest {
    @Test(dataProvider = "indexAndISBNData", dataProviderClass = someDataProviderClass.class, priority = 1)
    public void serviceTest(int index, String isbnExpression) {
        Response responseBody = given()
                .baseUri(Constants.BOOK_STORE)
                .accept("application/json")
                .when()
                .get("books");

        String isbnFirstBook = responseBody.jsonPath().getString("books[0].isbn");
        String firstBookAuthor = responseBody.jsonPath().getString("books[0].author");
        String isbnSecondBook = responseBody.jsonPath().getString("books[1].isbn");
        String secondBookAuthor = responseBody.jsonPath().getString("books[1].author");
        System.out.println("First book's ISBN is: " + isbnFirstBook + " and author is: " + firstBookAuthor);
        System.out.println("Second book's ISBN is: " + isbnSecondBook + " and author is: " + secondBookAuthor);

        String isbn = responseBody.jsonPath().getString(isbnExpression);
        Response responseBook = given()
                .baseUri(Constants.BOOK_STORE)
                .accept("application/json")
                .when()
                .get("Book?ISBN=" + isbn);

        String expectedAuthor = responseBody.jsonPath().getString("books[" + index + "].author");

        responseBook.then().statusCode(200)
                .body("author", equalToIgnoringCase(expectedAuthor))
                .body("title", notNullValue())
                .body("publish_date", notNullValue())
                .body("pages", greaterThan(0));

        given().baseUri(Constants.BOOK_STORE)
                .accept("application/json")
                .when()
                .delete("Book")
                .then()
                .statusCode(401)
                .body("message", equalTo(Constants.NOT_AUTHORIZED_MESSAGE));
    }
    @Test(priority = 2)
    public void petStoreTest(){

        Response response = given()
                .baseUri(Constants.PET_STORE)
                .contentType(ContentType.JSON)
                .accept("application/json")
                .body(Constants.JSON)
                .when()
                .post("/store/order");

        response.then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("petId", equalTo(12334))
                .body("quantity", notNullValue())
                .body("shipDate", notNullValue())
                .body("status", equalTo("placed"))
                .body("complete", equalTo(true));
    }
    @Test(priority = 3)
    public void testPostPet() {
        int petId = 1;
        String name = Constants.DOG_NAME;
        String status = Constants.STATUS;

        Response response = given()
                .baseUri(Constants.PET_STORE)
                .accept( "application/json")
                .contentType(ContentType.URLENC)
                .formParam("petId", petId)
                .formParam("name", name)
                .formParam("status", status)
                .when()
                .post("/pet/{petId}", petId);

        response.then()
                .statusCode(200)
                .body("code", notNullValue())
                .body("type", notNullValue())
                .body("message", notNullValue());
    }
    @Test(priority = 4)
    public void test404Error() {
        Response response = given()
                .baseUri(Constants.PET_STORE)
                .accept("application/json")
                .when()
                .get("nonexistentendpoint");

        response.then().statusCode(404);
        response.then().body("code", equalTo(404));
    }

    @Test(priority = 5)
    public void testUserLogin() {
        String username = Constants.USERNAME;
        String password = Constants.PASSWORD;

        Response response = given()
                .baseUri(Constants.PET_STORE)
                .accept("application/json")
                .queryParam("username", username)
                .queryParam("password", password)
                .get("/user/login");

        response.then().statusCode(200)
                .body("message", notNullValue())
                .body("message", matchesRegex(".*\\d{10}.*"))
                .extract()
                .response()
                .jsonPath()
                .getString("message");
        String extractedNumber = response.jsonPath().getString("message").replaceAll("\\D+", "");
        System.out.println(extractedNumber);
    }

    @Test(priority = 6)
    public void openlibraryTest() {
        Response response = given()
                .baseUri(Constants.OPEN_LIBRARY)
                .accept("application/json")
                .param("q", "Harry Potter")
                .when()
                .get("/search.json");


        response.then().statusCode(200)
                .body("docs", not(empty()))
                .body("docs[0].title", equalTo(Constants.PHILOSOPHER_STONE))
                .body("docs[0].author_name", hasItem(Constants.ROWLING))
                .body("docs[0].place", hasItems(Constants.ENGLAND, Constants.HOGWARTS, Constants.PLATFORM));
    }
}

