package users.create.response;

import lombok.Getter;
import lombok.Setter;
import org.testng.Assert;
import users.create.CreateUserRequestBody;

@Getter
public class CreateUserResponse {

    @Setter
    private int statusCode;

    private String meta;
    private Data data;

    public void assertUser(CreateUserRequestBody requestBody){
        Assert.assertEquals(this.getStatusCode(),201);
        Assert.assertNotNull(this.getData().getId());
        Assert.assertEquals(this.getData().getEmail(),requestBody.getEmail());
        Assert.assertEquals(this.getData().getEmail(),requestBody.getName());
        Assert.assertEquals(this.getData().getEmail(),requestBody.getGender());
        Assert.assertEquals(this.getData().getEmail(),requestBody.getStatus());
    }
}
