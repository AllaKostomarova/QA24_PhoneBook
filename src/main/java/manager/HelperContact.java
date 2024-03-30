package manager;

import models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HelperContact extends HelperBase{
    public HelperContact(WebDriver wd) {
        super(wd);
    }

    By titleAdd = By.xpath("//a[@href='/add']");
    By titleContact = By.xpath("//a[@href='/contacts']");
    By nameField = By.xpath("//input[@placeholder='Name']");
    By lastNameField = By.xpath("//input[@placeholder='Last Name']");
    By phoneField = By.xpath("//input[@placeholder='Phone']");
    By emailField = By.xpath("//input[@placeholder='email']");
    By addressField = By.xpath("//input[@placeholder='Address']");
    By descriptionField = By.xpath("//input[@placeholder='description']");
    By buttonSave = By.xpath("//button/b");
    By nameItemCard = By.cssSelector(".contact-item_card__2SOIM>h2");
    By phoneItemCard = By.cssSelector(".contact-item_card__2SOIM>h3");


    public void openContactForm() {
        click(titleAdd);
    }

    public void openContactPage(){
        click(titleContact);
    }

    public void saveContactForm() {
        click(buttonSave);
    }

    public void fillContactForm(Contact newContact) {
        type(nameField, newContact.getName());
        type(lastNameField, newContact.getLastName());
        type(phoneField, newContact.getPhone());
        type(emailField, newContact.getEmail());
        type(addressField, newContact.getAddress());
        type(descriptionField, newContact.getDescription());
    }

    public boolean isContactAddedByName(String name) {
        List<WebElement> contactList = wd.findElements(nameItemCard);
        for (WebElement el: contactList){
            if(el.getText().equals(name))
                return true;
        }
        return false;
    }

    public boolean isContactAddedByPhone(String phone) {
        List<WebElement> contactList = wd.findElements(phoneItemCard);
        for (WebElement el: contactList){
            if(el.getText().equals(phone))
                return true;
        }
        return false;
    }

    public boolean isContactFormPresent(){
        return isElementPresent(buttonSave);
    }
}
