package integration.utils;

import integration.pojo.UserPojo;
import integration.factory.UserDataFactory;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class UserUtil {

    public String getUserID(String ID){
        String userID;

        userID = given()
                    .contentType(ContentType.JSON)
                    .body(ID)
                .when()
                    .post(USER_BASE_URL)
                .then()
                    .statusCode(201)
                    .body("message",equalTo("Cadastro realizado com sucesso"))
                    .body("_id", notNullValue())
                    .extract()
                        .path("_id");
        return userID;

    }

    public ValidatableResponse deleteUser(String userIDForDelete){

        return given()
                    .contentType(ContentType.JSON)
                .when()
                    .delete(USER_BASE_URL.concat(userIDForDelete))
                .then();

    }

    public UserUtil createAdmUser(){
        UserPojo user = new UserDataFactory().userAdmin();
        user.setNome("Fulano da Silva");
        user.setEmail("fulano@qa.com");

        given()
            .contentType(ContentType.JSON)
            .body(user)
        .when()
            .post(USER_BASE_URL)
        .then();

        return null;
    }

    public ValidatableResponse registerUser(UserPojo userForRegister) {
        return given()
                .contentType(ContentType.JSON)
                .body(userForRegister)
                .log().all()
                .when()
                .post(USER_BASE_URL)
                .then();
    }


    //PATH
    private static final String USER_BASE_URL = "https://serverest.dev/usuarios/";
}
