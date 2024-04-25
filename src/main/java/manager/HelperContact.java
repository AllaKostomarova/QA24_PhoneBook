package manager;

import models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;

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

    By titleAddActive = By.cssSelector("a.active[href='/add']");

    //------------------------------------
    By contactCard = By.cssSelector("div.contact-item_card__2SOIM");
    By btnRemove = By.xpath("//button[text()='Remove']");
    By messageNoContacts = By.cssSelector(".contact-page_message__2qafk>h1");


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

    public boolean isAddPageStillDisplayed() {
        return isElementPresent(titleAddActive);
    }


    public int removeOneContact() {
        int before = countOfContacts();
        logger.info("Number of Contacts before remove is ---> "+before);
        removeContact();
        int after = countOfContacts();
        logger.info("Number of Contacts after remove is ---> "+after);
        return before-after;
    }

    private int countOfContacts() {
        return wd.findElements(contactCard).size();
    }

    private void removeContact(){
        click(contactCard);
        click(btnRemove);
        pause(1);
    }

    public void removeAllContacts(){
        while (countOfContacts() !=0)
            removeContact();
    }

    public String getMessage_NoContacts() {
        return wd.findElement(messageNoContacts).getText();
    }

    public void provideContacts() {
        if(countOfContacts()<3){
            for (int i = 0; i < 3; i++) {
                addOneContact();
                pause(2);
            }
        }
    }

    private void addOneContact() {
       // int i = new Random().nextInt(1000)+1000;
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        Contact contact = Contact.builder()
                .name("Harry")
                .lastName("Poter")
                .email("har"+i+"@har.har")
                .phone("12345976"+i)
                .address("USA")
                .description("get")
                .build();
        openContactForm();
        fillContactForm(contact);
        saveContactForm();
        //pause(5);
    }
}
