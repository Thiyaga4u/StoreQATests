package util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by TARAMU on 5/23/2016.
 */
public class StoreQAUtil {

    public static WebElement waitforElement(WebDriver driver, By locator){
        WebDriverWait wait = new WebDriverWait(driver,20,500);
        WebElement Elementfound = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Elementfound.isEnabled();
        return Elementfound;
    }

}
