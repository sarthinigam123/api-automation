package users;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import users.create.CreateUserRequestBody;
import users.create.response.CreateUserErrorResponse;
import users.create.response.CreateUserResponse;
import users.get.GetUserResponse;

import static io.restassured.RestAssured.given;

public class UsersClient {
    public CreateUserResponse createUser(CreateUserRequestBody body) {
        Response response = create(body);
        CreateUserResponse createUserResponse = response.as(CreateUserResponse.class);
        createUserResponse.setStatusCode(response.statusCode());
        return createUserResponse;
    }

    public Response create(CreateUserRequestBody body) {
        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer b7f2230a0" +
                        "7ad3b1da4eaef1eec45205a02168ee6f3404797702242b01fe62538")
                .body(body)
                .when()
                .post("https://gorest.co.in/public/v2/users");

        response
                .then()
                .log().body();

        return response;
    }

    public CreateUserErrorResponse createUserExpectingError(CreateUserRequestBody body){
        Response response = create(body);
        CreateUserErrorResponse errorResponse = response.as(CreateUserErrorResponse.class);
        errorResponse.setStatusCode(response.statusCode());
        return errorResponse;
    }

    public Response getAlLUsers() {
        return given()
                .when()
                .get("https://gorest.co.in/public/v2/users");
    }

    public GetUserResponse getUser(int id){
        Response response =
                given()
                        .pathParam("id", id)
                        .when()
                        .get("https://gorest.co.in/public/v1/users/{id}");

        response.
                then()
                .log().body();

        int statusCode = response.statusCode();

        GetUserResponse getUserResponse = response.as(GetUserResponse.class);
        getUserResponse.setStatusCode(statusCode);
        return getUserResponse;
    }
}
