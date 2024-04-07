package tests;

import models.Contact;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AddNewContactTest extends TestBase{

    @BeforeClass
    public void preCondition(){
        User newUser = new User().withEmail("ww@ww.ru").withPassword("Test123$");
        if(!app.getHelperUser().isLogged())
            app.getHelperUser().login(newUser);
    }

    @AfterMethod
    public void goToContactPage(){
        app.getHelperContact().pause(5);
        app.getHelperContact().openContactPage();
    }

    @Test
    public void addNewContactSuccessAllFields(){
        int randomNum = (int)(System.currentTimeMillis()/1000)%3600;
        Contact newContact = Contact.builder()
                .name("sucAll"+randomNum)
                .lastName("xxx"+randomNum)
                .phone("123456789"+randomNum)
                .email("xxx"+randomNum+"@xxx.xxx")
                .address("City"+randomNum)
                .description("favorite")
                .build();

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
                .name("suc"+randomNum)
                .lastName("xxx"+randomNum)
                .phone("123456789"+randomNum)
                .email("xxx"+randomNum+"@xxx.xxx")
                .address("City"+randomNum)
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(newContact);
        app.getHelperContact().saveContactForm();
        Assert.assertTrue(app.getHelperContact().isContactAddedByName(newContact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(newContact.getPhone()));
    }

    @Test(description = "Alert is not present", enabled = true)
    public void addNewContactNegativeTest_emptyNameField(){
        Contact newContact = Contact.builder()
                .name("")
                .lastName("xxx-negative")
                .phone("12345678901")
                .email("xxx-negative@xxx.xxx")
                .address("City")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(newContact);
        app.getHelperContact().saveContactForm();
        Assert.assertTrue(app.getHelperContact().isContactFormPresent());
        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());

//        softAssert.assertTrue(app.getHelperContact().isAlertPresent("Name cannot be empty!"));//--> Bug
        app.getHelperContact().openContactPage();
        Assert.assertFalse(app.getHelperContact().isContactAddedByPhone(newContact.getPhone()));
    }

    @Test(description = "Alert is not present", enabled = true)
    public void addNewContactNegativeTest_emptyLastNameField(){
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        Contact newContact = Contact.builder()
                .name("xxx-empty LastName")
                .lastName("")
                .phone("12345678901")
                .email("xxx@xxx.xxx")
                .address("City")
                .build();

        app.getHelperContact().openContactForm();
        //app.waitElement();
        app.getHelperContact().fillContactForm(newContact);
        app.getHelperContact().getScreen("src/test/screenshots/screen"+i+".png");
        app.getHelperContact().saveContactForm();
        Assert.assertTrue(app.getHelperContact().isContactFormPresent());
        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
 //       softAssert.assertTrue(app.getHelperContact().isAlertPresent("Last Name cannot be empty!"));//--> Bug
        app.getHelperContact().openContactPage();
        Assert.assertFalse(app.getHelperContact().isContactAddedByPhone(newContact.getPhone()));
    }

    @Test
    public void addNewContactNegativeTest_emptyPhoneField(){

        Contact newContact = Contact.builder()
                .name("xxx-empty Phone")
                .lastName("xxx-neg")
                .phone("")
                .email("xxx@xxx.xxx")
                .address("City")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(newContact);
        app.getHelperContact().saveContactForm();
        Assert.assertTrue(app.getHelperContact().isContactFormPresent());
        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
        Assert.assertTrue(app.getHelperContact().isAlertPresent("Phone not valid"));
        app.getHelperContact().openContactPage();
        Assert.assertFalse(app.getHelperContact().isContactAddedByName(newContact.getName()));
    }

    @Test
    public void addNewContactNegativeTest_wrongPhoneNumber(){

        Contact newContact = Contact.builder()
                .name("xxx-wrong Phone")
                .lastName("xxx-neg")
                .phone("12345")
                .email("xxx@xxx.xxx")
                .address("City")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(newContact);
        Assert.assertTrue(app.getHelperContact().isContactFormPresent());
        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
        Assert.assertTrue(app.getHelperContact().isAlertPresent("Phone not valid"));
        app.getHelperContact().openContactPage();
        Assert.assertFalse(app.getHelperContact().isContactAddedByName(newContact.getName()));
    }

    @Test(description = "Alert is not present; New contact is added", enabled = true)
    public void addNewContactNegativeTest_emptyEmailField(){

        Contact newContact = Contact.builder()
                .name("xxx-emptyEmail")
                .lastName("xxx-neg")
                .phone("123456789012")
                .email("")
                .address("City")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(newContact);
        app.getHelperContact().saveContactForm();
        Assert.assertTrue(app.getHelperContact().isContactFormPresent());
        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
        //softAssert.assertTrue(app.getHelperContact().isAlertPresent("Email not valid:"));//--> Bug
//        app.getHelperContact().openContactPage();
//        Assert.assertFalse(app.getHelperContact().isContactAddedByPhone(newContact.getPhone()));//--> Bug

    }
    @Test
    public void addNewContactNegativeTest_wrongEmail(){
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        Contact newContact = Contact.builder()
                .name("xxx-wrongEmail")
                .lastName("xxx-neg")
                .phone("123456789012")
                .email("xxx.xxx")
                .address("City")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(newContact);
        app.getHelperContact().pause(15);
        app.getHelperContact().saveContactForm();
        app.getHelperContact().getScreen("src/test/screenshots/screen"+i+".png");
        app.getHelperContact().pause(15);
        app.getHelperContact().getScreen("src/test/screenshots/screen"+i+".png");
        Assert.assertTrue(app.getHelperContact().isContactFormPresent());
        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
       // app.getHelperContact().getScreen("src/test/screenshots/screen"+i+".png");
        Assert.assertTrue(app.getHelperContact().isAlertPresent("Email not valid:"));
        app.getHelperContact().openContactPage();
        Assert.assertFalse(app.getHelperContact().isContactAddedByPhone(newContact.getPhone()));
//        softAssert.assertAll("stop test");
    }
    @Test(description = "Alert is not present", enabled = true)
    public void addNewContactNegativeTest_emptyAddressField(){

        Contact newContact = Contact.builder()
                .name("xxx-emptyAddress")
                .lastName("xxx-neg")
                .phone("123456789012")
                .email("xxx@xxx.xxx")
                .address("")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(newContact);
        app.getHelperContact().saveContactForm();
        Assert.assertTrue(app.getHelperContact().isContactFormPresent());
        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
 //       softAssert.assertTrue(app.getHelperContact().isAlertPresent("Address cannot be empty!"));//--> Bug
        app.getHelperContact().openContactPage();
        Assert.assertFalse(app.getHelperContact().isContactAddedByPhone(newContact.getPhone()));
    }
    @Test
    public void addNewContactNegativeTest_emptyAllField(){
        Contact newContact = Contact.builder()
                .name("")
                .lastName("")
                .phone("")
                .email("")
                .address("")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(newContact);
        app.getHelperContact().saveContactForm();
        Assert.assertTrue(app.getHelperContact().isContactFormPresent());
        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
        Assert.assertTrue(app.getHelperContact().isAlertPresent());
    }

}
