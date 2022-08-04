import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CreateUsersTests {
    @Test
    public void shouldCreateMaleUser(){
        //Arrange
        String body = "{\n" +
                "    \"name\": \"Tenali Ramakrishna\",\n" +
                "    \"gender\": \"male\",\n" +
                "    \"email\": \"tenali.ramakrishna1@yahoo.com\",\n" +
                "    \"status\": \"active\"\n" +
                "}";

        //Act
        createUser(body)
                .then()
                .log().body()

                //Assert
                .statusCode(201)
                .body("data.id", Matchers.notNullValue())
                .body("data.email", Matchers.equalTo("tenali.ramakrishna1@yahoo.com"))
                .body("data.name", Matchers.equalTo("Tenali Ramakrishna"));

        
    }

    @Test
    public void shouldCreateFemaleUser(){
        String body = "{\n" +
                "    \"name\": \"Aditi Rawat\",\n" +
                "    \"gender\": \"female\",\n" +
                "    \"email\": \"aditi.rawat1@yahoo.com\",\n" +
                "    \"status\": \"active\"\n" +
                "}";
        createUser(body)
                .then()
                .log().body()
                .statusCode(201)
                .body("data.id", Matchers.notNullValue())
                .body("data.email", Matchers.equalTo("aditi.rawat1@yahoo.com"))
                .body("data.name", Matchers.equalTo("Aditi Rawat"));

    }

    private Response createUser(String body) {
        return given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer b7f2230a07ad3b1da4eaef1eec45205a02168ee6f3404797702242b01fe62538")
                .body(body)
                .when()
                .post("https://gorest.co.in/public/v2/users");
    }

}
