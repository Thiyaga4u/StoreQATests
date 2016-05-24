package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IPhonesPage {

    private final WebDriver driver;

    // locators
    private final By iphone4BlackFormLocator = By.name("product_96");
    private final By checkoutButtonLocator = By.className("go_to_checkout");

    public IPhonesPage(WebDriver driver) {
        this.driver = driver;
    }

    public IPhonesPage addIphone4Black() throws InterruptedException {
        driver.findElement(iphone4BlackFormLocator).submit();
        return this;
    }

    public String getIphone4Price() {
        return driver.findElement(new By.ByXPath("//*[@id=\"grid_view_products_page_container\"]/div/div/div[2]/div/p[2]/span")).getText();
    }
    public float getIphone4PriceAsFloat() {
        String price = getIphone4Price();
        String s = price.split("\\$")[1];
        return Float.parseFloat(s);
    }

    public CheckoutPage clickCheckout() {
        WebElement e = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(checkoutButtonLocator));
        e.click();
        return new CheckoutPage(driver);
    }

}
