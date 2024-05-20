package pages;

import helper.Utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.util.Random;

public class PDP extends BasePage{
@FindBys(@FindBy(css = ".inline button.cursor-pointer"))
private List<WebElement> sizeList;

    @FindBy(css = ".inline button.cursor-pointer.selected")
    private WebElement selectedSizeElement;

    @FindBy(css = "h1#pdp-name")
    private WebElement productNameElement;

    @FindBy(id = "add-to-bag-button")
    private WebElement addToBagButton;

    @FindBy(css = "[data-testid='BASKET_COUNT']")
    private WebElement bagValue;

    @FindBy(className = "basket-icon")
    private WebElement bagIcon;
    WebDriverWait wait;

    public PDP(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, 25);
    }

    public void addToCardPage() {
        Utilities utilities = new Utilities(driver);
        this.clickAddtoBag();
        utilities.waitForPageToLoad(30);
        this.assertBagValueIsNotZero();
    }

    public void assertPDPisDisplayed() {
        Assert.assertTrue(productNameElement.isDisplayed());
        this.selectRandomSize();
    }

    public void selectRandomSize() {
        List<WebElement> sizes = sizeList;

        Random random = new Random();
        int randomIndex = random.nextInt(sizes.size());

        sizes.get(randomIndex).click();
    }

    public String storeProductSize() {
        List<WebElement> sizeOptions = sizeList;
        WebElement selectedSize = null;
        for (WebElement sizeOption : sizeOptions) {
            if (sizeOption.getCssValue("color").equals("rgba(255, 255, 255, 1)")) {
                selectedSize = sizeOption;
                break;
            }
        }
        assert selectedSize != null;
        return selectedSize.getText();
    }

    public String storeProductName() {
        String productName = productNameElement.getText();

        return productName;
    }

    public void clickAddtoBag() {
        addToBagButton.click();
    }

    public void assertBagValueIsNotZero() {
        Utilities utilities = new Utilities(driver);
        utilities.explicitWait(bagValue);
        Assert.assertTrue(bagValue.isDisplayed());
        this.clickBagIcon();
    }

    public void clickBagIcon() {
        Utilities utilities = new Utilities(driver);
        utilities.clickOnJS(bagIcon);
        utilities.waitForPageToLoad(30);
    }
}
