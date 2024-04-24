package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveContactTest extends TestBase{
    @BeforeMethod(alwaysRun = true)
    public void preCondition(){
        User newUser = new User().withEmail("ww@ww.ru").withPassword("Test123$");
        if(!app.getHelperUser().isLogged())
            app.getHelperUser().login(newUser);
        app.getHelperContact().provideContacts();
    }

    @Test(groups = {"smoke"})
    public void removeFirstContact(){
        //Assert size of list less by one
        Assert.assertEquals(app.getHelperContact().removeOneContact(), 1);

    }

    @Test
    public void removeAllContacts(){
        //"No contacts here"
        app.getHelperContact().removeAllContacts();
        Assert.assertEquals(app.getHelperContact().getMessage_NoContacts(), "No Contacts here!");

    }
}
