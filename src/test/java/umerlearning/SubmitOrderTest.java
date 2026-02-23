package umerlearning;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import umerlearning.AbstractComponents.AbstractComponent;
import umerlearning.TestComponents.BaseTest;
import umerlearning.pageobjects.*;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class SubmitOrderTest extends BaseTest {

    @Test
    public void Submitorder() throws InterruptedException, IOException {


        String productName = "ZARA COAT 3";

        LandingPage landingpage = launchApplication();
        ProductCatalogue productCatalogue = landingpage.loginUser("fsd.cbd17@gmail.com","User1234");

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


        driver.quit();
    }
}
