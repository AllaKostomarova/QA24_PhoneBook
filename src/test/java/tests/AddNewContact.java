package tests;

import models.Contact;
import org.testng.annotations.Test;

public class AddNewContact extends TestBase{

    @Test
    public void addNewContactSuccess(){
        int randomNum = (int)(System.currentTimeMillis()/1000)%3600;
        Contact newContact = Contact.builder()
                .name("xxx"+randomNum)
                .lastName("xxx"+randomNum)
                .phone("123457"+randomNum)
                .email("xxx"+randomNum+"@xxx.xxx")
                .address("City"+randomNum)
                .build();
        System.out.println(newContact);
    }
}
