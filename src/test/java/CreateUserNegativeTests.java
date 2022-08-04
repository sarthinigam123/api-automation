import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import users.UsersClient;
import users.create.CreateUserRequestBody;
import users.create.response.CreateUserErrorResponse;

public class CreateUserNegativeTests {

    private UsersClient usersClient;

    @BeforeClass
    public void beforeClass(){
        usersClient = new UsersClient();
    }

    @Test
    public void shouldNotAllowToCreateUserWithInvalidMail(){

        CreateUserRequestBody requestBody = CreateUserRequestBody.builder().email("aditi.rawat2yahoo.com").build();

        CreateUserErrorResponse errorResponse = usersClient.createUserExpectingError(requestBody);

        Assert.assertEquals(errorResponse.getStatusCode(),422);
        errorResponse.assertHasError("email","is invalid");

    }

    @Test
    public void shouldNotAllowToCreateUserWithEmptyGenderAndStatus(){
        CreateUserRequestBody requestBody = CreateUserRequestBody.builder()
                .gender("").status("").build();

        CreateUserErrorResponse errorResponse = usersClient.createUserExpectingError(requestBody);

        Assert.assertEquals(errorResponse.getStatusCode(),422);
        errorResponse.assertHasError("gender","can't be blank, can be male or female");
        errorResponse.assertHasError("status","can't be blank");
    }
}
