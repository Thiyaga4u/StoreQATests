package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ResultsPage {

    private final WebDriver driver;

    // locators
    private final By resultsLocator = By.cssSelector("div.wpsc-transaction-results-wrap p");

    public ResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTotalShipping() {
        String s = driver.findElements(resultsLocator).get(2).getText();
        String total = s.split("\\n")[0];
        return total;
    }

    public float getTotalShippingAsFloat() {
        String total = getTotalShipping();
        String s = total.split("\\$")[1];
        return Float.parseFloat(s);
    }

    public String getTotalPrice() {
        String s = driver.findElements(resultsLocator).get(2).getText();
        String total = s.split("\\n")[1];
        return total;
    }

    public float getTotalPriceAsFloat() {
        String total = getTotalPrice();
        String s = total.split("\\$")[1];
        return Float.parseFloat(s);
    }

}
