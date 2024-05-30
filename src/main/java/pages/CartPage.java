package pages;

import helper.Environment;
import helper.Utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CartPage extends BasePage {
    @FindBy(css = ".grid a.font-brand-thin")
    private WebElement productNameElement;
    @FindBy(css = ".grid li:nth-child(1)")
    private WebElement productSize;
    @FindBy(css = ".grid .inline-block")
    private WebElement totalValue;
    @FindBy(id = "checkout-button-top")
    private WebElement proceedToCheckoutButton;

    WebDriverWait wait;

    public CartPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, 25);
    }

    public void proceedToCheckoutPage() {
        this.assertCartPageIsDisplayed();
    }

    public void assertCartPageIsDisplayed() {
        Assert.assertTrue(driver.getCurrentUrl().contains(Environment.getProperty("cartpageURLtag")));
    }

    public void assertProductSizeInBag(String itemSize) {
        String selectedSize = productSize.getText();
        // Text comes with an extra infos and using split":" get size
        Assert.assertEquals(selectedSize.split(":")[1].strip(), itemSize);
    }

    public void assertProductNameInBag(String productName) {
        Utilities utilities = new Utilities(driver);
        utilities.explicitWait(productNameElement);
        String bagProductName = productNameElement.getText();

        Assert.assertEquals(bagProductName, productName);
    }

    public String bagTotalValue() {
        return totalValue.getText();
    }

    public void clickProceedToCheckout() {
        Utilities utilities = new Utilities(driver);
        utilities.clickOnJS(proceedToCheckoutButton);
        utilities.waitForPageToLoad(30);
    }


}
