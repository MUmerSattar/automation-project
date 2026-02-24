package umerlearning;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import TestComponents.BaseTest;
import umerlearning.pageobjects.*;

import java.io.IOException;
import java.util.List;

public class SubmitOrderTest extends BaseTest {
    String productName = "ZARA COAT 3";

    @Test
    public void Submitorder() throws InterruptedException, IOException {

        ProductCatalogue productCatalogue = landingPage.loginUser("fsd.cbd17@gmail.com","User1234");
        List<WebElement> products = productCatalogue.getProductsList();
        productCatalogue.addProductToCart(productName);
        CartPage cartPage = productCatalogue.GoToCartPage();
        boolean match = cartPage.VerifyProductDisplay(productName);
        Assert.assertTrue(match);
        CheckOutPage checkout = cartPage.GoToCheckOut();
        checkout.SelectCountry("Sau");
        ConfirmationPage confirmationPage = checkout.submitOrder();
        String confirmMessage = confirmationPage.GetConfirmationMessage();
        Assert.assertEquals(confirmMessage, "THANKYOU FOR THE ORDER.");
        System.out.println("Confirm Message: " + confirmMessage);
    }

    @Test(dependsOnMethods = {"Submitorder"})
    public void OrderHistoryTest() throws InterruptedException {
        ProductCatalogue productCatalogue = landingPage.loginUser("fsd.cbd17@gmail.com","User1234");
        OrderPage orderPage = productCatalogue.GoToOrderPage();
        Assert.assertTrue(orderPage.VerifyOrdersDisplay(productName));

    }
}
