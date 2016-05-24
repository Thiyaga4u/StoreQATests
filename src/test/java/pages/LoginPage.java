package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static util.StoreQAUtil.waitforElement;

public class LoginPage {

    private final WebDriver driver;

    // locators
    private final By usernameLocator = By.id("user_login");
    private final By passwordLocator = By.id("user_pass");
    private final By loginButtonLocator = By.id("wp-submit");
    private final By usernameLocatorBack = By.id("log");
    private final By passwordLocatorBack = By.id("pwd");
    private final By loginButtonLocatorBack = By.id("login");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage typeUsername(String username) {
        driver.findElement(usernameLocatorBack).sendKeys(username);
        return new LoginPage(driver);
    }

    public LoginPage typePassword(String password) {
        driver.findElement(passwordLocatorBack).sendKeys(password);
        return new LoginPage(driver);
    }

    public ProfilePage clickLoginButton() {
        driver.findElement(loginButtonLocatorBack).click();
        return new ProfilePage(driver);
    }


    public LoginPage typeUserId(String username) {
        waitforElement(driver, usernameLocator);
        driver.findElement(usernameLocator).sendKeys(username);
        return new LoginPage(driver);
    }

    public LoginPage typePwd(String password) {
        waitforElement(driver, passwordLocator);
        driver.findElement(passwordLocator).sendKeys(password);
        return new LoginPage(driver);
    }

    public ProfilePage clickLogin() {
        waitforElement(driver, loginButtonLocator);
        driver.findElement(loginButtonLocator).click();
        return new ProfilePage(driver);
    }





}
