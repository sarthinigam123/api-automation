package integrationTests;

import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import users.UsersClient;
import users.create.CreateUserRequestBody;
import users.create.response.CreateUserResponse;
import users.get.GetUserResponse;

import java.util.UUID;

import static io.restassured.RestAssured.given;

public class UserTests {
    @BeforeClass
    public void beforeClass(){
        usersClient = new UsersClient();
    }

    private UsersClient usersClient;

    @Test
    public void shouldCreateAndGetUser(){
        //Arrange


        CreateUserRequestBody requestBody = new CreateUserRequestBody.Builder().build();

        //Act
        int id = usersClient.createUser(requestBody).getData().getId();


        //Assert
        usersClient.getUser(id).assertUser(requestBody);

    }

}
