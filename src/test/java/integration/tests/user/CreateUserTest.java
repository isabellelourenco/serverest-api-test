package integration.tests.user;

import integration.factory.UserDataFactory;
import integration.pojo.UserPojo;
import integration.utils.UserUtil;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.*;
import static org.hamcrest.Matchers.*;

@DisplayName("Create User API Test")
public class CreateUserTest {

    @Test
    @DisplayName("When register a Admin user, then user should be saved with success")
    public void whenRegisterAdminUserItShouldBeSaveWithSuccess(){

        UserPojo user = new UserDataFactory().userAdmin();

        String userID = _userUtil.registerUser(user)
                .statusCode(HttpStatus.SC_CREATED)
                .log().all()
                .body("message", equalTo("Cadastro realizado com sucesso"))
                .body("_id", notNullValue())
                .extract().path("_id");

        _userUtil.deleteUser(userID);
    }

    @Test
    @DisplayName("When register a user, then user should be saved with success")
    public void whenRegisterUserItShouldBeSaveWithSuccess(){

        UserPojo user = new UserDataFactory().userConsumer();

        String userID = _userUtil.registerUser(user)
            .statusCode(HttpStatus.SC_CREATED)
                .log().all()
                .body("message", equalTo("Cadastro realizado com sucesso"))
            .body("_id", notNullValue())
                    .extract().path("_id");

        _userUtil.deleteUser(userID);
    }

    @Test
    @DisplayName("When register a user with a empty name, then user should not be created")
    public void whenRegisterUserWithEmptyNameShouldNotBeCreated(){

        UserPojo user = new UserDataFactory().userWithEmptyName();

        _userUtil.registerUser(user)
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("nome", equalTo("nome não pode ficar em branco"));

    }

    @Test
    @DisplayName("When register a user with null name, then user should not be created")
    public void whenRegisterUserWithNullNameShouldNotBeCreated(){

        UserPojo user = new UserDataFactory().userNullName();

        _userUtil.registerUser(user)
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("nome", equalTo("nome deve ser uma string"));

    }

    @Test
    @DisplayName("When register a user with a empty email, then user should not be created")
    public void whenRegisterUserWithoutEmailShouldNotBeCreated(){

        UserPojo user = new UserDataFactory().userWithEmptyEmail();

        _userUtil.registerUser(user)
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("email", equalTo("email não pode ficar em branco"));

    }

    @Test
    @DisplayName("When register a user with null email, then user should not be created")
    public void whenRegisterUserWithNullEmailShouldNotBeCreated(){

        UserPojo user = new UserDataFactory().userNullEmail();

        _userUtil.registerUser(user)
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("email", equalTo("email deve ser uma string"));

    }

    @Test
    @DisplayName("When register a user with a existent email, then user should not be created")
    public void whenRegisterUserWithExistentEmailShouldNotBeCreated(){

        UserPojo user = new UserDataFactory().userConsumer();

        String userID = _userUtil.registerUser(user)
                .extract().path("_id");

        _userUtil.registerUser(user)
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("message", equalTo("Este email já está sendo usado"));

        _userUtil.deleteUser(userID);
    }


    @Test
    @DisplayName("When register a user without password, then user should not be created")
    public void whenRegisterUserWithoutPasswordShouldNotBeCreated(){

        UserPojo user = new UserDataFactory().userWithEmptyPassword();

        _userUtil.registerUser(user)
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("password", equalTo("password não pode ficar em branco"));

    }

    @Test
    @DisplayName("When register a user with null password, then user should not be created")
    public void whenRegisterUserWithNullPasswordShouldNotBeCreated(){

        UserPojo user = new UserDataFactory().userNullPassword();

        _userUtil.registerUser(user)
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("password", equalTo("password deve ser uma string"));

    }

    @Test
    @DisplayName("When register a user without Admin type, then user should not be created")
    public void whenRegisterUserWithoutAdminTypeShouldNotBeCreated(){

        UserPojo user = new UserDataFactory().userWithNullAdminType();

        _userUtil.registerUser(user)
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("administrador", equalTo("administrador deve ser 'true' ou 'false'"));

    }

    @Test
    @DisplayName("When register a user with a Invalid Admin type, then user should not be created")
    public void whenRegisterUserWithInvalidAdminTypeShouldNotBeCreated(){

        UserPojo user = new UserDataFactory().userWithInvalidAdminType();

        _userUtil.registerUser(user)
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("administrador", equalTo("administrador deve ser 'true' ou 'false'"));

    }

    UserUtil _userUtil = new UserUtil();

}
