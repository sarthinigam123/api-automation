package users.create;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class CreateUserRequestBody {
    private String name;
    private String gender;
    private String email;
    private String status;

    public CreateUserRequestBody(Builder builder) {
        this.name= builder.name;
        this.email= builder.email;
        this.gender= builder.gender;
        this.status= builder.status;
    }

    public static class Builder{
        private String name;
        private String gender;
        private String email;
        private String status;

        public Builder(){
            this.name="Tenali R";
            this.email= String.format("%s@yahoo.com", UUID.randomUUID());
            this.gender="male";
            this.status="active";
        }

        public Builder gender(String gender){
            this.gender=gender;
            return this;
        }

        public Builder email(String email){
            this.email=email;
            return this;
        }

        public Builder status(String status){
            this.status=status;
            return this;
        }

        public CreateUserRequestBody build(){
            CreateUserRequestBody user=new CreateUserRequestBody(this);
            return user;
        }
    }

    }
