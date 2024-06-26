package manager;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class HelperBase {
    WebDriver wd;
    Logger logger = LoggerFactory.getLogger(HelperContact.class);

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    public void type(By locator, String text){
        WebElement element = wd.findElement(locator);
        element.click();
        element.clear();
        if(text !=null) {
            element.sendKeys(text);
        }
    }

    public void click(By locator){
        WebElement element = wd.findElement(locator);
        element.click();
    }

    public boolean isElementPresent(By locator){
        List<WebElement> list = wd.findElements(locator);
        return list.size()>0;
    }

    public boolean isAlertPresent(String message) {
        //click ok -->alert.accept();
        //click cancel -->alert.dismiss();
        //type into alert --> alert.senKeys("hello);
        Alert alert = new WebDriverWait(wd, 10)
                .until(ExpectedConditions.alertIsPresent());
        if(alert != null && alert.getText().contains(message)){
            //System.out.println(alert.getText());
            //pause(2000);
            alert.accept();
            return true;
        }
        return false;
    }

    public boolean isAlertPresent() {
        Alert alert = new WebDriverWait(wd, 10)
                .until(ExpectedConditions.alertIsPresent());
        if(alert != null){
            alert.accept();
            return true;
        }
        return false;
    }

    public void pause(int sec){
        try {
            Thread.sleep(1000L*sec);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        }

    public void getScreen(String link) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) wd;
        File file = takesScreenshot.getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(file, new File(link));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
