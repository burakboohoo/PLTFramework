package e2e;

import org.testng.annotations.Test;

public class PurchaseItem extends BaseTest {

    String prodcutName;
    String productSize;
    String subtotal;

    @Test(description = "Select a random product")
    public void selectProduct() {

        homePage.selectItem();

        prodcutName = pdp.storeProductName();
        productSize = pdp.storeProductSize();
    }
    @Test(dependsOnMethods = "selectProduct", description = "Add the product to Cart Page")
    public void addToCardPage() {
        pdp.addToCardPage();

        cartPage.assertProductNameInBag(prodcutName);
        cartPage.assertProductSizeInBag(productSize);
        subtotal = cartPage.bagTotalValue();

        cartPage.clickProceedToCheckout();
    }
    @Test(dependsOnMethods = "addToCardPage", description = "Log in to the account in order to go to checkout page")
    public void login() {
        cartPage.proceedToCheckoutPage();
        checkoutPage.login();
    }
    @Test(dependsOnMethods = "login", description = "Make payment on the payment info page")
    public void checkOutPage() {
        checkoutPage.assertPaymentInfoPageIsDisplayed();
        checkoutPage.assertProductDatasDisplayedInProductInfo(prodcutName,productSize);
        checkoutPage.assertDeliveryTotalIsDisplayed();
        checkoutPage.assertGrandTotalIsDisplayed();
        checkoutPage.cardPayment();
    }
}
