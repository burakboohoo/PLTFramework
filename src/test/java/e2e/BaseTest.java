package e2e;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.*;
import helper.Utilities;

public class BaseTest {

    public static WebDriver driver;

    public HomePage homePage;
    public CartPage cartPage;
    public CheckoutPage checkoutPage;
    public PDP pdp;
    public PLP plp;
    public Utilities utilities;
    public ExtentTest logger;
    public ExtentReports report;

    @BeforeClass(alwaysRun=true)
    public void setup(ITestContext context) {
        System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        context.setAttribute("WebDriver", driver);

        homePage = new HomePage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        pdp = new PDP(driver);
        plp = new PLP(driver);
        utilities = new Utilities(driver);
    }

    @AfterClass(alwaysRun=true)
    public void tearDown() {
        driver.quit();
    }
}
