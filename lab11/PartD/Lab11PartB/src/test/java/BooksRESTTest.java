import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import books.domain.Book;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

public class BooksRESTTest {

    @BeforeClass
    public static void setup() {
        RestAssured.port = Integer.valueOf(8080);
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "";
    }

    @Test
    public void testGetOneBook() {
        // add the book to be fetched
        Book book = new Book("878","Book 123", 18.95, "Joe Smith");
        given()
                .contentType("application/json")
                .body(book)
                .when().post("/books").then()
                .statusCode(200);
        // test getting the book
        given()
                .when()
                .get("books/878")
                .then()
                .contentType(ContentType.JSON)
                .and()
                .body("isbn",equalTo("878"))
                .body("title",equalTo("Book 123"))
                .body("price",equalTo(18.95f))
                .body("author",equalTo("Joe Smith"));
        //cleanup
        given()
                .when()
                .delete("books/878");
    }

    @Test
    public void testAddAndGetBook() {
        Book book = new Book("878", "Book 123", 18.95, "Joe Smith");

        // Add book
        given()
                .contentType(ContentType.JSON)
                .body(book)
                .when()
                .post("/books")
                .then()
                .statusCode(200);

        // Get book
        given()
                .when()
                .get("/books/878")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("isbn", equalTo("878"))
                .body("title", equalTo("Book 123"))
                .body("price", equalTo(18.95f))
                .body("author", equalTo("Joe Smith"));

        // Cleanup
        delete("/books/878");
    }

    @Test
    public void testUpdateBook() {
        Book book = new Book("999", "Old Title", 10.0, "Alice");
        post("/books").then().statusCode(200);

        // Update the book
        Book updatedBook = new Book("999", "New Title", 15.0, "Alice");
        given()
                .contentType(ContentType.JSON)
                .body(updatedBook)
                .when()
                .put("/books/999")
                .then()
                .statusCode(200)
                .body("title", equalTo("New Title"))
                .body("price", equalTo(15.0f));

        delete("/books/999");
    }

    @Test
    public void testDeleteBook() {
        Book book = new Book("777", "To Delete", 5.0, "Delete Author");
        post("/books").then().statusCode(200);

        // Delete
        when()
                .delete("/books/777")
                .then()
                .statusCode(204);

        // Ensure it's gone
        when()
                .get("/books/777")
                .then()
                .statusCode(404);
    }

    @Test
    public void testGetAllBooksAndByAuthor() {
        Book b1 = new Book("101", "Java Basics", 25.0, "Dev Guru");
        Book b2 = new Book("102", "Advanced Java", 35.0, "Dev Guru");
        given()
                .contentType(ContentType.JSON)
                .body(b1)
                .when()
                .post("/books")
                .then()
                .statusCode(200);

        given()
                .contentType(ContentType.JSON)
                .body(b2)
                .when()
                .post("/books")
                .then()
                .statusCode(200);


        // Get all
        when()
                .get("/books")
                .then()
                .statusCode(200)
                .body("books.size()", equalTo(2));

        // Get by author
        given()
                .queryParam("author", "\"Dev Guru\"")
                .when()
                .get("/books")
                .then()
                .statusCode(200)
                .body("books.author", everyItem(equalTo("Dev Guru")));

        delete("/books/101");
        delete("/books/102");
    }
}
