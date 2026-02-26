package umerlearning;

import TestComponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import umerlearning.pageobjects.CartPage;
import umerlearning.pageobjects.ProductCatalogue;

import java.io.IOException;
import java.util.List;

public class ErrorValidationsTest extends BaseTest {

    @Test(groups = {"ErrorHandling"})
    public void LoginErrorValidation() throws InterruptedException, IOException {
        landingPage.loginUser("fsd.cbd20@gmail.com","User123456");
        Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email and password.");
        System.out.println(landingPage.getErrorMessage());
    }

    @Test
    public void ProductErrorValidation() throws InterruptedException, IOException {
        String productName = "ZARA COAT 3";
        ProductCatalogue productCatalogue = landingPage.loginUser("fsd.cbd16@gmail.com","User1234");
        productCatalogue.addProductToCart(productName);
        CartPage cartPage = productCatalogue.GoToCartPage();
        boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
        Assert.assertFalse(match);

    }
}
