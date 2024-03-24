package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RegistrationTests extends TestBase {
    @BeforeMethod
    public void preCondition(){
        // if SignOut ---> logout
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logOut();
        }
    }

    @Test
    public void registrationSuccess(){
        // way 1
        int i = new Random().nextInt((1000));
        // way 2
        int randomNum = (int)(System.currentTimeMillis()/1000)%3600;
        User newUser = new User().withEmail(randomNum+"w@w.w").withPassword("Test123$");
        System.out.println(newUser);

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(newUser);
        app.getHelperUser().submitRegistration();
        Assert.assertTrue(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isNoContactsHereDisplayed());
    }

   // @Test(description = "Bug report #12354", enabled = false)
   @Test(description = "Bug report #12354 Fixed")
    public void registrationWrongEmail(){
        User newUser = new User().withEmail("not-valid.email").withPassword("Test123$");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(newUser);
        app.getHelperUser().submitRegistration();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));

    }

    @Test
    public void registrationWrongPassword() {
        User newUser = new User().withEmail("valid@email.ru").withPassword("test");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(newUser);
        app.getHelperUser().submitRegistration();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
    }

    @Test
    public void registrationExistsUser() {
        User newUser = new User().withEmail("aa@aa.ru").withPassword("Test123$");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(newUser);
        app.getHelperUser().submitRegistration();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("User already exist"));
    }
}
