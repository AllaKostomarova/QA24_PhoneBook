package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ListenerWD extends AbstractWebDriverEventListener {
    Logger logger = LoggerFactory.getLogger(ListenerWD.class);

    public ListenerWD(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        super.beforeFindBy(by, element, driver);
        logger.info("before find element: "+by);
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        super.afterFindBy(by, element, driver);
        logger.info("after find element: "+by);
    }

    @Override
    public void beforeGetText(WebElement element, WebDriver driver) {
        super.beforeGetText(element, driver);
        logger.info("get text from element with text: "+element.getText());
    }

    @Override
    public void beforeNavigateTo(String url, WebDriver driver) {
        super.beforeNavigateTo(url, driver);
    }

    @Override
    public void afterNavigateTo(String url, WebDriver driver) {
        super.afterNavigateTo(url, driver);
    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {
        super.onException(throwable, driver);
        logger.info("Huston we have a problem");
        logger.info(throwable.getMessage());
        logger.info(throwable.fillInStackTrace().toString());
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        String link = "src/test/screenshots/screen-"+1+".png";
        HelperBase helperBase = new HelperBase(driver);
        helperBase.getScreen(link);
        logger.info("This is link to screenshot with error --->"+link);

    }

    @Override
    public void afterGetText(WebElement element, WebDriver driver, String text) {
        super.afterGetText(element, driver, text);
        logger.info("got the text successfull");
    }

}
