package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.remote.RemoteWebDriver;

import static util.StoreQAUtil.waitforElement;

public class HomePage {

    private final WebDriver driver;

    // locators
    private final By accountIconLocator = By.className("account_icon");
    private final By searchLocator = By.name("s");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public IPhonesPage searchIphoneProduct() throws InterruptedException {
        waitforElement(driver, searchLocator);
        driver.findElement(searchLocator).sendKeys("apple iphone 16gb black");
		Thread.sleep(3000);
        Keyboard kb = ((RemoteWebDriver) driver).getKeyboard();
        kb.pressKey(Keys.RETURN);
        return new IPhonesPage(driver);
    }

    public AccountPage clickAccountIcon() {
        waitforElement(driver, accountIconLocator);
        driver.findElement(accountIconLocator).click();
        return new AccountPage(driver);
    }

}
