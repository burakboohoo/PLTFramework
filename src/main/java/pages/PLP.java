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

public class PLP extends BasePage{

//      By productsList = By.cssSelector("#products-list1 > a");
@FindBys(@FindBy(css = "#products-list1 > a"))
        private List<WebElement> productsList;
     WebDriverWait wait;
    public PLP(WebDriver driver){
       super(driver);
       wait = new WebDriverWait(driver,5);
    }

    public void selectProductRandomly() {
        Utilities utilities = new Utilities(driver);
        PDP pdp = new PDP(driver);
        HomePage homePage = new HomePage(driver);
        List<WebElement> products = productsList;

        Random random = new Random();
        int randomIndex = random.nextInt(products.size());

        products.get(randomIndex).click();
        utilities.waitForPageToLoad(30);
        pdp.assertPDPisDisplayed();
    }

    public void assertPLPisDisplayed(){
        Assert.assertTrue(productsList.get(0).isDisplayed());
        this.selectProductRandomly();
    }
}
