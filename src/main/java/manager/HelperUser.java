package manager;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HelperUser extends HelperBase{


    public HelperUser(WebDriver wd) {
        super(wd);
    }

    //============LOGIN====================
    public void openLoginRegistrationForm(){
        //The method opens the page /login (https://telranedu.web.app/login) by clicking the "Login" title in the header

//        WebElement loginTab = wd.findElement(By.cssSelector("a[href='/login']")); // xpath: //a[text()='LOGIN']
//        loginTab.click();
        click(By.cssSelector("a[href='/login']"));
    }

    public void fillLoginRegistrationForm(String email, String password){
        // The method fills the email field and the password field in the login page

//        WebElement emailInput = wd.findElement(By.name("email"));
//        emailInput.click();
//        emailInput.clear();
//        emailInput.sendKeys(email);
        type(By.name("email"), email);

//        WebElement passwordInput = wd.findElement(By.xpath("//input[last()]"));
//        passwordInput.click();
//        passwordInput.clear();
//        passwordInput.sendKeys(passvord);
        type(By.xpath("//input[last()]"), password);
    }

    public void fillLoginRegistrationForm(User user) {
        // The method fills the email field and the password field

        type(By.name("email"), user.getEmail());
        type(By.xpath("//input[last()]"), user.getPassword());
    }

    public void submitLogin(){
        //The method clicks on the "Login" button on the login page

        click(By.xpath("//button[text()='Login']"));
    }

    public boolean isLogged() {
        //The method returns:
        // True if the user's page is opened (The "Sign Out" title is present in the header)
        // False if the login page is opened (The "Sign Out" title is not present in the header)

        return isElementPresent(By.xpath("//button[text()='Sign Out']"));
    }

    public void logOut() {
        // The method dos logout

        click(By.xpath("//button[text()='Sign Out']"));
    }

    public void submitRegistration(){
        // The method clicks on the "Registration" button on the login page

        click(By.name("registration"));
    }

    public boolean isNoContactsHereDisplayed(){
        WebDriverWait wait = new WebDriverWait(wd, 5);
        return wait.until(ExpectedConditions
                .textToBePresentInElement(wd.findElement(By.cssSelector(".contact-page_message__2qafk>h1")), "No Contacts here!"));

    }

    public void login(User user){
        openLoginRegistrationForm();
        fillLoginRegistrationForm(user);
        submitLogin();
    }
}
