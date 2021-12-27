package integration.factory;

import com.github.javafaker.Faker;
import integration.pojo.UserPojo;

public class UserDataFactory {
    Faker faker = new Faker();

    public UserPojo userAdmin(){
        UserPojo user = new UserPojo();

        user.setAdministrador("true");
        user.setNome(faker.name().firstName());
        user.setEmail(faker.internet().emailAddress());
        user.setPassword("teste");

        return user;
    }

    public UserPojo userConsumer(){
        UserPojo user = new UserPojo();

        user.setAdministrador("false");
        user.setNome(faker.name().fullName());
        user.setEmail(faker.internet().emailAddress());
        user.setPassword("test");

        return user;
    }

    public UserPojo userNullName(){
        UserPojo user = new UserDataFactory().userAdmin();
        user.setNome(null);

        return user;
    }

    public UserPojo userWithEmptyName(){
        UserPojo user = new UserDataFactory().userAdmin();
        user.setNome("");

        return user;
    }
    public UserPojo userNullEmail(){
        UserPojo user = new UserDataFactory().userAdmin();
        user.setEmail(null);

        return user;
    }

    public UserPojo userWithEmptyEmail(){
        UserPojo user = new UserDataFactory().userAdmin();
        user.setEmail("");

        return user;
    }

    public UserPojo userNullPassword(){
        UserPojo user = new UserDataFactory().userAdmin();
        user.setPassword(null);

        return user;
    }

    public UserPojo userWithEmptyPassword(){
        UserPojo user = new UserDataFactory().userAdmin();
        user.setPassword("");

        return user;
    }

    public UserPojo userWithNullAdminType(){
        UserPojo user = new UserDataFactory().userAdmin();
        user.setAdministrador("");

        return user;
    }

    public UserPojo userWithInvalidAdminType(){
        UserPojo user = new UserDataFactory().userAdmin();
        user.setAdministrador("teste");

        return user;
    }


}
