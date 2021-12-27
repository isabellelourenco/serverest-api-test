package integration.factory;

import integration.pojo.LoginPojo;

public class LoginDataFactory {

    public static LoginPojo loginSuccess(){
        LoginPojo login = new LoginPojo();
        login.setEmail("beltrano@qa.com.br");
        login.setPassword("teste");

        return login;
    }

    public static LoginPojo loginInvalidUser(){
        LoginPojo login = new LoginPojo();
        login.setEmail("fulano_teste@gmail.com");
        login.setPassword("teste***");

        return login;
    }

    public static LoginPojo loginWithoutEmail(){
        LoginPojo login = new LoginPojo();
        login.setPassword("teste");

        return login;
    }

    public static LoginPojo loginWithoutPassword(){
        LoginPojo login = new LoginPojo();
        login.setEmail("fulano_teste@gmail.com");

        return login;
    }
}
