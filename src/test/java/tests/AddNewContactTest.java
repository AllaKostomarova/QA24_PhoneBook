package tests;

import models.Contact;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AddNewContactTest extends TestBase{
    @BeforeClass
    public void preConditione(){
        User newUser = new User().withEmail("ww@ww.ru").withPassword("Test123$");
        if(!app.getHelperUser().isLogged())
            app.getHelperUser().login(newUser);
    }

    @Test
    public void addNewContactSuccessAllFields(){
        int randomNum = (int)(System.currentTimeMillis()/1000)%3600;
        Contact newContact = Contact.builder()
                .name("xxx"+randomNum)
                .lastName("xxx"+randomNum)
                .phone("123456789"+randomNum)
                .email("xxx"+randomNum+"@xxx.xxx")
                .address("City"+randomNum)
                .description("favorite")
                .build();
        System.out.println(newContact);
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(newContact);
        app.getHelperContact().saveContactForm();
        Assert.assertTrue(app.getHelperContact().isContactAddedByName(newContact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(newContact.getPhone()));
    }

    @Test
    public void addNewContactSuccess(){
        int randomNum = (int)(System.currentTimeMillis()/1000)%3600;
        Contact newContact = Contact.builder()
                .name("xxx"+randomNum)
                .lastName("xxx"+randomNum)
                .phone("123456789"+randomNum)
                .email("xxx"+randomNum+"@xxx.xxx")
                .address("City"+randomNum)
                .build();
        System.out.println(newContact);
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(newContact);
        app.getHelperContact().saveContactForm();
        Assert.assertTrue(app.getHelperContact().isContactAddedByName(newContact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(newContact.getPhone()));
    }
}
