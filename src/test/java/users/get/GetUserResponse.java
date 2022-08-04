package users.get;

import lombok.Getter;
import lombok.Setter;
import org.testng.Assert;
import users.create.CreateUserRequestBody;

@Getter
public class GetUserResponse {

    private Data data;
    private String meta;

    @Setter
    private int statusCode;
    @Getter
    public static class Data{
        public int id;
        public String name;
        public String email;
        public String gender;
        public String status;
    }

    public void assertUser(CreateUserRequestBody requestBody) {
        Assert.assertEquals(data.email, requestBody.getEmail());
        Assert.assertEquals(data.name, requestBody.getName());
        Assert.assertEquals(data.gender, requestBody.getGender());
        Assert.assertEquals(data.status, requestBody.getStatus());
    }
}
