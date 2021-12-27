package integration.suite;

import integration.tests.login.LoginTest;
import integration.tests.user.CreateUserTest;
import integration.tests.user.DeleteUserTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;


@Suite
@SuiteDisplayName("A test suite for ServeRest API")
@SelectClasses( { LoginTest.class, CreateUserTest.class, DeleteUserTest.class } )
public class RunAllApiTests {

}
