package integration.tests.user;

import integration.utils.UserUtil;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ListUserTest {

    @Test
    @DisplayName("When get all users, then should return a list of registered users")
    public void whenGetAllUsersItShouldReturnAListOfUsers(){
        _userUtil.listUsers()
                .statusCode(HttpStatus.SC_OK);
    }

    UserUtil _userUtil = new UserUtil();
}
