package integration.utils;

import integration.factory.LoginDataFactory;
import io.restassured.http.ContentType;
import integration.pojo.LoginPojo;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class LoginUtil {

    public String getUserToken(){
        String tokenAdm;

        LoginPojo login = new LoginDataFactory().loginSuccess();

        tokenAdm = given()
                        .contentType(ContentType.JSON)
                        .body(login)
                   .when()
                        .post(LOGIN_BASE_URL)
                   .then()
                        .statusCode(200)
                        .body("message",equalTo("Login realizado com sucesso"))
                        .body("authorization", notNullValue())
                        .extract()
                        .path("authorization");
        return tokenAdm;

    }

    public ValidatableResponse loginUser(LoginPojo user){

        return given()
                    .contentType(ContentType.JSON)
                    .log().all()
                    .body(user)
                .when()
                    .post(LOGIN_BASE_URL)
                .then();
    }

    //PATH
    private static final String LOGIN_BASE_URL = "https://serverest.dev/login";
}
