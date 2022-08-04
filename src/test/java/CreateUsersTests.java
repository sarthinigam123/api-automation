import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import users.UsersClient;
import users.create.CreateUserRequestBody;
import users.create.response.CreateUserResponse;
import users.get.GetUserResponse;

import java.util.UUID;

import static io.restassured.RestAssured.given;

public class CreateUsersTests {

    @BeforeClass
    public void beforeClass(){
        usersClient = new UsersClient();
    }

    private UsersClient usersClient;

    @Test
    public void shouldCreateMaleUser(){

//        //Arrange

        CreateUserRequestBody requestBody = new CreateUserRequestBody.Builder().gender("male").build();

        //Act
        CreateUserResponse createUserResponse = usersClient.createUser(requestBody);


                //Assert
        createUserResponse.assertUser(requestBody);


    }

    @Test
    public void shouldCreateFemaleUser(){
        CreateUserRequestBody requestBody = new CreateUserRequestBody.Builder().gender("female").build();


        CreateUserResponse createUserResponse = usersClient.createUser(requestBody);

        //Assert
        createUserResponse.assertUser(requestBody);

    }

    public GetUserResponse getUser(int id){
        Response response = given()
                .pathParam("id", id)
                .when()
                .get("https://gorest.co.in/public/v2/users/{id}");

        response.then().log().body();

        int statusCode = response.statusCode();

        GetUserResponse getUserResponse = response.as(GetUserResponse.class);
        getUserResponse.setStatusCode(statusCode);

        return getUserResponse;
    }

}
