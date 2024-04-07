package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{

    @BeforeMethod
    public void preCondition(){
        // if SignOut ---> logout
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logOut();
            logger.info("Before method finish logout");
        }
    }

    @Test
    public void loginSuccess(){
        logger.info("Start test with name 'loginSuccess'");
        logger.info("Test data-->email: aa@aa.ru, password: Test123$");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("aa@aa.ru", "Test123$");
        app.getHelperUser().submitLogin();

//        Assert.assertEquals();
//        Assert.assertNotEquals();
//        Assert.assertTrue();
//        Assert.assertFalse();

        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Assert check that button 'Sign Out' present");

    }

    @Test
    public void loginSuccessModel(){
        logger.info("Start");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("aa@aa.ru", "Test123$");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("End");
    }
    @Test
    public void loginWrongEmail(){
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("aaaa.ru", "Test123$");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));

    }
    @Test
    public void loginWrongPassword(){
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("aa@aa.ru", "Test123");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
    }
    @Test
    public void loginUnregistredUser(){
        User user = new User().withEmail("aa@aa.ru").withPassword("Test123");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));

    }
}
