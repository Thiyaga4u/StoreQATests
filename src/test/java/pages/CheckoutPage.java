package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import static util.StoreQAUtil.waitforElement;

public class CheckoutPage {

    private final WebDriver driver;

    // locators
    private final By continueButtonLocator = By.className("step2");
    private final By countryButtonLocator = By.id("uniform-current_country");
    private final By countrySelectLocator = By.id("current_country");
    private final By calculateButtonLocator = By.name("wpsc_submit_zipcode");
    private final By emailAddressFormLocator = By.id("wpsc_checkout_form_9");
    private final By billingFirstNameLocator = By.id("wpsc_checkout_form_2");
    private final By billingLastNameLocator = By.id("wpsc_checkout_form_3");
    private final By billingAddressLocator = By.id("wpsc_checkout_form_4");
    private final By billingCityLocator = By.id("wpsc_checkout_form_5");
    private final By billingStateLocator = By.id("wpsc_checkout_form_6");
    private final By billingPostalCodeLocator = By.id("wpsc_checkout_form_8");
    private final By billingPhoneLocator = By.id("wpsc_checkout_form_18");
    private final By billingCountrySelectLocator = By.id("wpsc_checkout_form_7");
    private final By shippingSameBillingCheckboxLocator = By.id("shippingSameBilling");
    private final By purchaseButtonLocator = By.className("input-button-buy");
    private final By cartItemsLocator = By.className("count");
    private final By clickRemoveItemLocator = By.xpath("//*[@id=\"checkout_page_container\"]/div[1]/table/tbody/tr[2]/td[6]/form/input[4]");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public CheckoutPage clickContinue() {
        //waitforElement(driver, continueButtonLocator);
        driver.findElement(continueButtonLocator).click();
        return this;
    }

    public CheckoutPage clickCountryMenu() {
       // waitforElement(driver, countryButtonLocator);
        driver.findElement(countryButtonLocator).click();
        return this;
    }

    public CheckoutPage selectCountry(String text) {
        Select dropdown = new Select(driver.findElement(countrySelectLocator));
        dropdown.selectByVisibleText(text);
        return this;
    }

    public CheckoutPage clickCalculateButton() throws InterruptedException {

        waitforElement(driver, calculateButtonLocator);
        driver.findElement(calculateButtonLocator).submit();
        return this;
    }

    public CheckoutPage addEmailAddress(String emailAddress) {
        waitforElement(driver, emailAddressFormLocator);
        driver.findElement(emailAddressFormLocator).sendKeys(emailAddress);
        return this;
    }

    public CheckoutPage addBillingFirstName(String firstName) {
        waitforElement(driver, billingFirstNameLocator);
        driver.findElement(billingFirstNameLocator).sendKeys(firstName);
        return this;
    }

    public CheckoutPage addBillingLastName(String lastName) {
        waitforElement(driver, billingLastNameLocator);
        driver.findElement(billingLastNameLocator).sendKeys(lastName);
        return this;
    }

    public CheckoutPage addBillingAddress(String address) {
        driver.findElement(billingAddressLocator).sendKeys(address);
        return this;
    }

    public CheckoutPage addBillingCity(String city) {
        driver.findElement(billingCityLocator).sendKeys(city);
        return this;
    }

    public CheckoutPage addBillingState(String state) {
        driver.findElement(billingStateLocator).sendKeys(state);
        return this;
    }

    public CheckoutPage selectBillingCountry(String text) {

        //waitforElement(driver, billingCountrySelectLocator);
        Select dropdown = new Select(driver.findElement(billingCountrySelectLocator));
        dropdown.selectByVisibleText(text);
        return this;
    }

    public CheckoutPage addBillingPostalCode(String postalCode) {
        driver.findElement(billingPostalCodeLocator).sendKeys(postalCode);
        return this;
    }

    public CheckoutPage addBillingPhone(String phone) {
        driver.findElement(billingPhoneLocator).sendKeys(phone);
        return this;
    }

    public CheckoutPage clickShippingSameBillingCheckbox() {
        driver.findElement(shippingSameBillingCheckboxLocator).click();
        return this;
    }

    public ResultsPage clickPurchaseButton() {
        waitforElement(driver, purchaseButtonLocator);
        driver.findElement(purchaseButtonLocator).submit();
        return new ResultsPage(driver);
    }

    public String getItemCount() {
        waitforElement(driver, cartItemsLocator);
        return driver.findElement(cartItemsLocator).getText();
    }

    public int getItemCountAsInt() {
        return Integer.parseInt(getItemCount());
    }

    public CheckoutPage clickRemoveItem() {
        waitforElement(driver, clickRemoveItemLocator);
        driver.findElement(clickRemoveItemLocator).click();
        return this;
    }

}
