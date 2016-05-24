package com.store.qa.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.*;

import static org.testng.AssertJUnit.assertEquals;


public class StoreQATest {

    private WebDriver driver;

    private static final String BASEURL = "http://store.demoqa.com";
    public static String chromeDriverPath = "ChromeDriver\\";

    @BeforeClass
    public void setUp() throws Exception {
       //driver = new FirefoxDriver();
       System.setProperty("webdriver.chrome.driver", chromeDriverPath+"chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void testIphone4OrderAndValidateTotalPrice() throws Exception {
        driver.get(BASEURL);

        // home page
        HomePage homePage = new HomePage(driver);

        // add Iphone
        IPhonesPage iPhonesPage = homePage.searchIphoneProduct();
        iPhonesPage.addIphone4Black();
        Float iphone4Price = iPhonesPage.getIphone4PriceAsFloat();

        // checkout
        CheckoutPage checkoutPage = iPhonesPage.clickCheckout();
        Thread.sleep(10000);
        checkoutPage.clickContinue();
        Thread.sleep(20000);
        checkoutPage.clickCountryMenu();
        checkoutPage.selectCountry("USA");
        checkoutPage.clickCalculateButton();

        // add account info
        checkoutPage.addEmailAddress("sample@gmail.com");
        checkoutPage.addBillingFirstName("Rajan");
        checkoutPage.addBillingLastName("Tom");
        checkoutPage.addBillingAddress("55555  Test Drive Lane");
        checkoutPage.addBillingCity("Austin");
        checkoutPage.addBillingState("TX");
        checkoutPage.addBillingPostalCode("22304");
        checkoutPage.addBillingPhone("333-222-6666");
        Thread.sleep(20000);
        checkoutPage.selectBillingCountry("USA");
        checkoutPage.clickShippingSameBillingCheckbox();

        // purchase
        ResultsPage resultsPage = checkoutPage.clickPurchaseButton();

        // verify price
        float shipping = resultsPage.getTotalShippingAsFloat();
        float expectedTotal = iphone4Price + shipping;
        float actualTotal = resultsPage.getTotalPriceAsFloat();
        assertEquals(expectedTotal, actualTotal, 0.001);
    }

    @Test
    public void testMyAccountDetailsUpdateByReLogin() throws Exception {

        driver.get(BASEURL);
        String username = "thiyaga4u";
        String password = "Raj%PM5zkSGowNFR";
        // home page
        HomePage homePage = new HomePage(driver);

        // log in
        AccountPage accountPage = homePage.clickAccountIcon();
        accountPage.addUserId(username);
        accountPage.addPwd(password);
        accountPage.clickLogin();

        // Update address
        accountPage.clickDetailsLink();
        String newAddress = "123" + " Sesame Street";
        accountPage.clearAddress();
        accountPage.addAddress(newAddress);
        accountPage.clickSaveProfile();

        // log out
        LoginPage loginPage = accountPage.clickLogoutLink();
        // log in back
        loginPage.typeUserId(username);
        loginPage.typePwd(password);
        ProfilePage profilePage = loginPage.clickLogin();

        // Account details
        homePage = profilePage.clickHomeLink();
        accountPage = homePage.clickAccountIcon();
        accountPage.clickDetailsLink();

        // verify updated address
        String address = accountPage.getAddress();
        assertEquals(newAddress, address);
    }

    @Test
    public void testEmptyCartMessageByRemoveAllItems() throws Exception {
        driver.get(BASEURL);
        HomePage homePage = new HomePage(driver);
        // Add iphones
        IPhonesPage iPhonesPage = homePage.searchIphoneProduct();
        iPhonesPage.addIphone4Black();
        // Add more iphones
        HomePage homePageRef = new HomePage(driver);
        IPhonesPage iPhonesPageRef = homePageRef.searchIphoneProduct();
        iPhonesPageRef.addIphone4Black();

       // checkout
        CheckoutPage checkoutPage = iPhonesPageRef.clickCheckout();
        checkoutPage.clickRemoveItem();

        // verify no items left in cart
        int newCount = checkoutPage.getItemCountAsInt();
        assertEquals(0, newCount);
		Assert.assertEquals(driver.findElement(By.xpath("//div[contains(@class, 'entry-content') and normalize-space(.)='Oops, there is nothing in your cart.']")).getText(), "Oops, there is nothing in your cart.", "Fail");

    }

}