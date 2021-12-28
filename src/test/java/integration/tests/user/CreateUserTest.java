package integration.tests.user;

import com.sun.xml.bind.v2.runtime.reflect.opt.Const;
import integration.factory.UserDataFactory;
import integration.pojo.UserPojo;
import integration.utils.Constants;
import integration.utils.UserUtil;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.*;
import static org.hamcrest.Matchers.*;

@DisplayName("Register User API Test")
public class CreateUserTest {

    @Test
    @DisplayName("When register a Admin user, then user should be saved with success")
    public void whenRegisterAdminUserItShouldBeSaveWithSuccess(){

        UserPojo user = new UserDataFactory().userAdmin();

        String userID = _userUtil.registerUser(user)
                .statusCode(HttpStatus.SC_CREATED)
                .body("message", equalTo(Constants.SUCCESS_MESSAGE_REGISTER))
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
            .body("message", equalTo(Constants.SUCCESS_MESSAGE_REGISTER))
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
                .body("nome", equalTo(Constants.FAILED_MESSAGE_EMPTY_USER_NAME));

    }

    @Test
    @DisplayName("When register a user with null name, then user should not be created")
    public void whenRegisterUserWithNullNameShouldNotBeCreated(){

        UserPojo user = new UserDataFactory().userNullName();

        _userUtil.registerUser(user)
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("nome", equalTo(Constants.FAILED_MESSAGE_NULL_USER_NAME));

    }

    @Test
    @DisplayName("When register a user with a empty email, then user should not be created")
    public void whenRegisterUserWithEmptyEmailShouldNotBeCreated(){

        UserPojo user = new UserDataFactory().userWithEmptyEmail();

        _userUtil.registerUser(user)
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("email", equalTo(Constants.FAILED_MESSAGE_EMPTY_USER_EMAIL));

    }

    @Test
    @DisplayName("When register a user with null email, then user should not be created")
    public void whenRegisterUserWithNullEmailShouldNotBeCreated(){

        UserPojo user = new UserDataFactory().userNullEmail();

        _userUtil.registerUser(user)
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("email", equalTo(Constants.FAILED_MESSAGE_NULL_USER_EMAIL ));

    }

    @Test
    @DisplayName("When register a user with a existent email, then user should not be created")
    public void whenRegisterUserWithExistentEmailShouldNotBeCreated(){

        UserPojo user = new UserDataFactory().userConsumer();

        String userID = _userUtil.registerUser(user)
                .extract().path("_id");

        _userUtil.registerUser(user)
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("message", equalTo(Constants.FAILED_MESSAGE_EXISTENT_USER_EMAIL));

        _userUtil.deleteUser(userID);
    }


    @Test
    @DisplayName("When register a user with empty password, then user should not be created")
    public void whenRegisterUserWithEmptyPasswordShouldNotBeCreated(){

        UserPojo user = new UserDataFactory().userWithEmptyPassword();

        _userUtil.registerUser(user)
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("password", equalTo(Constants.FAILED_MESSAGE_EMPTY_USER_PASSWORD));

    }

    @Test
    @DisplayName("When register a user with null password, then user should not be created")
    public void whenRegisterUserWithNullPasswordShouldNotBeCreated(){

        UserPojo user = new UserDataFactory().userNullPassword();

        _userUtil.registerUser(user)
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("password", equalTo(Constants.FAILED_MESSAGE_NULL_USER_PASSWORD));

    }

    @Test
    @DisplayName("When register a user without Admin type, then user should not be created")
    public void whenRegisterUserWithoutAdminTypeShouldNotBeCreated(){

        UserPojo user = new UserDataFactory().userWithNullAdminType();

        _userUtil.registerUser(user)
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("administrador", equalTo(Constants.FAILED_MESSAGE_USER_ADMIN_TYPE));

    }

    @Test
    @DisplayName("When register a user with a Invalid Admin type, then user should not be created")
    public void whenRegisterUserWithInvalidAdminTypeShouldNotBeCreated(){

        UserPojo user = new UserDataFactory().userWithInvalidAdminType();

        _userUtil.registerUser(user)
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("administrador", equalTo(Constants.FAILED_MESSAGE_USER_ADMIN_TYPE));

    }

    UserUtil _userUtil = new UserUtil();

}
