package pages;

import com.github.javafaker.Faker;
import helper.Utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import helper.Environment;

import java.util.Properties;

public class CheckoutPage extends BasePage{
    @FindBy(xpath = "//*[@id='customer-email']")
    private WebElement emailBox;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement continueButton;

    @FindBy(xpath = "//*[@id='customer-password']")
    private WebElement passwordBox;

    @FindBy(xpath = "//*[@id='firstname']")
    private WebElement firstNameBox;

    @FindBy(xpath = "//*[@id='lastname']")
    private WebElement lastNameBox;

    @FindBy(xpath = "//*[@id='telend']")
    private WebElement phoneNumberBox;

    @FindBy(xpath = "//*[@name='day']")
    private WebElement dayDropDown;

    @FindBy(xpath = "//*[@name='month']")
    private WebElement monthDropDown;

    @FindBy(xpath = "//*[@name='year']")
    private WebElement yearDropDown;

    @FindBy(xpath = "//*[@id='postcode']")
    private WebElement postCodeBox;

    @FindBy(xpath = "//*[@id='address1']")
    private WebElement addressBox;

    @FindBy(xpath = "//*[@id='city']")
    private WebElement cityBox;

    @FindBy(xpath = "//*[@id='state']")
    private WebElement stateBox;

    @FindBy(xpath = "//*[@id='shipping-info-container']")
    private WebElement accountInfo;

    @FindBy(xpath = "//*[contains(@class, 'bag-item__details-list')]")
    private WebElement productInfo;

    @FindBy(xpath = "//*[contains(@class, 'bag__totals-item')][2]")
    private WebElement deliveryTotal;

    @FindBy(className = "grand-total")
    private WebElement grandTotal;

    @FindBy(xpath = "//h2[text()='Payment Information']")
    private WebElement paymentSection;

    @FindBy(xpath = "//*[@name='worldpay']")
    private WebElement cardPaymentMethod;

    @FindBy(xpath = "//div[2]/*[contains(@class, 'accordion-content-container')]")
    private WebElement cardPaymentBox;

    WebDriverWait wait;
    public CheckoutPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver,25);
    }

    public void newUser() {

        Faker faker = new Faker();
        Properties properties = new Properties();

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String phoneNumber = properties.getProperty("phoneNumber");
        String day = properties.getProperty("day");
        String month = properties.getProperty("month");
        String year = properties.getProperty("year");
        String postCode = properties.getProperty("postCode");
        String address = properties.getProperty("address");
        String city = properties.getProperty("city");
        String state = properties.getProperty("state");

        firstNameBox.sendKeys(firstName);
        lastNameBox.sendKeys(lastName);
        phoneNumberBox.sendKeys(phoneNumber);
        new Select(dayDropDown).selectByValue(day);
        new Select(monthDropDown).selectByVisibleText(month);
        new Select(yearDropDown).selectByVisibleText(year);
        postCodeBox.sendKeys(postCode);
        addressBox.sendKeys(address);
        cityBox.sendKeys(city);
        stateBox.sendKeys(state);

    }
    public void assertCheckoutPageIsDisplayed() {
        Assert.assertTrue(driver.getCurrentUrl().contains(Environment.getProperty("checkoutpageURLtag")));
    }

    public void login() {
        Utilities utilities = new Utilities(driver);
        this.assertCheckoutPageIsDisplayed();
        utilities.waitForPageToLoad(30);
        utilities.explicitWait(emailBox);
        utilities.typeInbox(emailBox, Environment.getProperty("email"));
        continueButton.click();
        utilities.explicitWait(firstNameBox, passwordBox);
            if (utilities.isElementPresent(firstNameBox)) {
                this.newUser();
            } else {
                utilities.typeInbox(passwordBox, Environment.getProperty("password"));
            }
        utilities.explicitWait(continueButton);
        continueButton.click();
        utilities.explicitWait(accountInfo);
    }

    public void accountInfoIsDisplayed() {
        Assert.assertTrue(accountInfo.isDisplayed());
    }

    public void assertProductDatasDisplayedInProductInfo(String productName, String productSize) {
        Assert.assertTrue(productInfo.getText().contains(productName));
        Assert.assertTrue(productInfo.getText().contains(productSize));
    }

    public void assertDeliveryTotalIsDisplayed() {
        Assert.assertTrue(deliveryTotal.isDisplayed());
    }

    public void assertGrandTotalIsDisplayed() {
        Utilities utilities = new Utilities(driver);
        utilities.explicitWait(grandTotal);
        Assert.assertTrue(grandTotal.isDisplayed());
    }

    public void cardPayment() {
        this.scrollToPaymentSection();
    }
    public void scrollToPaymentSection() {
        Utilities utilities = new Utilities(driver);
        utilities.scrollDownToElement(paymentSection);
        this.selectCardPaymentMethod();
    }
    public void selectCardPaymentMethod() {
        Utilities utilities = new Utilities(driver);
        utilities.clickOnJS(cardPaymentMethod);
        this.assertCardPaymentBoxIsDisplayed();
    }
    public void assertCardPaymentBoxIsDisplayed() {
        Utilities utilities = new Utilities(driver);
        utilities.explicitWait(cardPaymentBox);
        Assert.assertTrue(cardPaymentBox.isDisplayed());
    }

    public void assertPaymentInfoPageIsDisplayed() {
        Assert.assertTrue(driver.getCurrentUrl().contains(Environment.getProperty("paymentinformationpageURLtag")));
        this.accountInfoIsDisplayed();
    }
}
