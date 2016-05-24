package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static util.StoreQAUtil.waitforElement;


public class ProfilePage {

    private final WebDriver driver;

    // locators
    private final By homeLinkLocator = By.id("wp-admin-bar-site-name");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage clickHomeLink() {
        waitforElement(driver, homeLinkLocator);
        driver.findElement(homeLinkLocator).click();
        return new HomePage(driver);
    }

}
