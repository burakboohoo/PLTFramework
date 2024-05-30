package pages;

import helper.Utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import helper.Environment;

import java.util.List;
import java.util.Random;

public class HomePage extends BasePage{

    @FindBy(css = "[data-testid='account-icon']")
            private WebElement loginButton;
    @FindBys(@FindBy(css = "#frame-header-nav > li"))
            private List<WebElement> navMenu;
    @FindBy(xpath = "//button[text()='Accept All']")
            private WebElement acceptAllButton;

     WebDriverWait wait;

    public HomePage(WebDriver driver){
        super(driver);
        wait = new WebDriverWait(driver,25);
    }

    public void selectItem() {
        this.selectCategoryRandomly();
    }

    public void gotoHomePage() {
        Utilities utilities = new Utilities(driver);
        driver.get(Environment.getProperty("homepageURL"));
        utilities.waitForPageToLoad(25);
        this.setAlertAccept(); //needs to handle alert in order to find locaters
    }

    public void selectCategoryRandomly() {
        Utilities utilities = new Utilities(driver);
        PLP plp = new PLP(driver);
        List<WebElement> categories = navMenu;

        Random random = new Random();
        // delete last category EDIT from the list
        int randomIndex = random.nextInt(categories.size()-1);

        categories.get(randomIndex).click();
        utilities.waitForPageToLoad(30);
        plp.assertPLPisDisplayed(); //assertion PLP is displayed
    }

    public void setAlertAccept() {
        Utilities utilities = new Utilities(driver);
        wait.until(ExpectedConditions.visibilityOf(acceptAllButton));
        utilities.clickOnJS(acceptAllButton);
    }

    public void clickOnLoginButton(){
        loginButton.click();
    }

    public String getUrl(){
        return driver.getCurrentUrl();
    }

    public void AssertURL(){
        Assert.assertTrue(driver.getCurrentUrl().contains(Environment.getProperty("homepageURL")));
    }

}
