package integration.tests.login;

import integration.factory.LoginDataFactory;
import integration.pojo.LoginPojo;
import integration.utils.LoginUtil;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.*;
import static org.hamcrest.Matchers.*;

@DisplayName("Login API Test")
public class LoginTest {

    @Test
    @DisplayName("When login with a valid admin user, then user should login with success")
    public void adminUserShouldLoginWithSuccess(){
        LoginPojo login = LoginDataFactory.loginSuccess();

        _loginUtil.loginUser(login)
                .statusCode(HttpStatus.SC_OK)
                .body("message",equalTo("Login realizado com sucesso"))
                .body("authorization", notNullValue());

    }

    @Test
    @DisplayName("When login with a valid user, then user should login with success")
    public void userShouldLoginWithSuccess(){
        LoginPojo login = LoginDataFactory.loginSuccess();

        _loginUtil.loginUser(login)
                .statusCode(HttpStatus.SC_OK)
                .body("message",equalTo("Login realizado com sucesso"))
                .body("authorization", notNullValue());
    }

    @Test
    @DisplayName("When submit login without email, then return a message error")
    public void userWithoutEmailShouldNotLoginWithSuccess(){
        LoginPojo login = LoginDataFactory.loginWithoutEmail();

        _loginUtil.loginUser(login)
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("email",equalTo("email deve ser uma string"));
    }

    @Test
    @DisplayName("When submit login without password, then return a message error")
    public void userWithoutPasswordShouldNotLogin(){
        LoginPojo login = LoginDataFactory.loginWithoutPassword();

        _loginUtil.loginUser(login)
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("password",equalTo("password deve ser uma string"));
    }

    @Test
    @DisplayName("When submit login with invalid user, then return a message error")
    public void invalidUserShouldNotLogin(){
        LoginPojo login = LoginDataFactory.loginInvalidUser();

        _loginUtil.loginUser(login)
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .body("message",equalTo("Email e/ou senha inválidos"));
    }


    LoginUtil _loginUtil = new LoginUtil();

}
