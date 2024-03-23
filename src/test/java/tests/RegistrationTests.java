package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

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
        int randomNum = (int)(System.currentTimeMillis()/1000)%3600;
        User newUser = new User().withEmail(randomNum+"w@w.w").withPassword("Test123$");
        System.out.println(newUser);

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(newUser);
        app.getHelperUser().submitRegistration();
        Assert.assertTrue(app.getHelperUser().isLogged());
    }

}
