# PLT Automation Framework

## Overview

This project is an automation framework for testing the PrettyLittleThing (PLT) website. It is built using the Page Object Model (POM) with TestNG as the testing framework and Maven for project management.

## Prerequisites

- Java Development Kit (JDK) 8 or higher
- Maven 3.6 or higher
- Google Chrome browser
- ChromeDriver (ensure the version matches your installed Chrome browser)

## Project Structure

- `Configuration.properties`: Contains configuration settings for the tests.
- `pom.xml`: Maven configuration file.
- `testng.xml`: TestNG configuration file for running the test suites.
- `src/`: Contains the source code, including page object classes and test cases.
- `target/`: Directory where the compiled code and reports will be generated.
- `report/`: Directory where test reports will be stored.

## Setup

1. **Clone the repository**
   ```sh
   git clone (https://github.com/burakboohoo/PLTFramework.git)

2. Install dependencies Ensure you have Maven installed. Run the following command to install project dependencies:
   ```sh
   mvn clean install
3. Update Configuration Edit the Configuration.properties file to set the required configuration parameters such as the base URL of the PLT website, ChromeDriver path, etc.

## Running Test
  Test results will be generated in the target/surefire-reports directory.

##  Writing Tests
  To add a new test case, follow these steps:

1. Create a new test class Add a new class in the src/test/java directory.

2. Define test methods Use TestNG annotations such as @Test to define your test methods.

3. Implement test logic Utilize the page object classes to interact with the web pages and perform the test steps.

### Example Test Case
  Here is an example of a test case based on the provided Excel file:
  ```sh
  public class PurchaseTest {

      WebDriver driver;
  
      @BeforeClass
      public void setUp() {
          System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
          driver = new ChromeDriver();
          driver.get("https://www.prettylittlething.com");
      }
  
      @Test
      public void purchaseItem() {
          // Implement the test steps here
      }
  
      @AfterClass
      public void tearDown() {
          driver.quit();
      }
  }
```


## Test Case from PLT Automation

### Objective

Open up a web browser, navigate to the PLT website, and purchase an item.

### Pre-requirements
- Automation framework is built in POM with TestNG and Maven.
- ChromeDriver is set up correctly.
### Test Steps
1. Launch ChromeDriver.
2. Navigate to https://www.prettylittlething.com.
3. Choose a random category from the navigation menu.
4. Confirm that the Product Listing Page (PLP) is displayed.
5. Choose a random item from the PLP page.
6. Continue to cart page and login the account
7. Go to checkout page and see the payment section
   
### Expected Outcomes
1. ChromeDriver launches on the device under test.
2. The Product Listing Page (PLT) website is displayed.
3. The Product Detail Page (PDP) should be displayed.
4. The Card Page should be displayed
5. The Checkout Page should be displayed
6. The Product Information Page should be displayed.
7. The PDP should be displayed.
8. Succesfully log in with valid credentials
9. Product name and size match on the order details informations
10. Grand total, subtotal should be match on bag informations
11. Payment section should be displayed
12. 
Reporting
Test reports will be generated in the report directory. Open the report file in a web browser to view the detailed test results.
   
