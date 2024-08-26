package ua.com.alevel;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import ua.com.alevel.entity.Student;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentControllerAssuredTest {

    @LocalServerPort
    private Integer port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    @Order(1)
    public void shouldBeCreateUser() {
        given()
                .contentType(ContentType.JSON)
                .body(new Gson().toJson(new Student()))
                .when()
                .post("/students")
                .then()
                .statusCode(201);
    }

    @Test
    @Order(2)
    public void shouldBeFindUserByCorrectId() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/students/{id}", 1)
                .then()
                .statusCode(200).body("id", equalTo(1));
    }

    @Test
    @Order(3)
    public void shouldBeFindUserByIncorrectId() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/students/{id}", 2)
                .then()
                .statusCode(404).body("errors", notNullValue());
    }
}
