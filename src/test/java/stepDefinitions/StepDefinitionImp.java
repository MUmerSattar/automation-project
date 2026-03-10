package stepDefinitions;

import TestComponents.BaseTest;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import umerlearning.pageobjects.*;

import java.io.IOException;
import java.util.List;

public class StepDefinitionImp extends BaseTest {
    public LandingPage landingPage;
    public ProductCatalogue productCatalogue;
    public ConfirmationPage confirmationPage;

    @Given("I landed on Ecommerce Page")
    public void i_LandedOn_Ecommerce_Page() throws IOException {
        landingPage = launchApplication();
    }

    @Given("^Logged in with username (.+) and password (.+)$")
    public void logged_in_with_username_and_password(String username, String password) throws Throwable {
            productCatalogue = landingPage.loginUser(username, password);

    }

    @When("^I add product (.+) to Cart$")
    public void i_add_product_to_Cart(String productName) throws Throwable {
         List<WebElement> products = productCatalogue.getProductsList();
         productCatalogue.addProductToCart(productName);
    }
    @And ("^Verify (.+) and submit the order$")
    public void verify_and_submit_the_order(String productName) throws Throwable {
        CartPage cartPage = productCatalogue.GoToCartPage();
        boolean match = cartPage.VerifyProductDisplay(productName);
        Assert.assertTrue(match);
        CheckOutPage checkout = cartPage.GoToCheckOut();
        checkout.SelectCountry("Sau");
        confirmationPage = checkout.submitOrder();

    }

    @Then("{string} message is displayed on ConfirmationPage")
    public void Confirmation_Message_Displayed_On_ConfirmationPage(String string) throws Throwable {
        String confirmMessage =  confirmationPage.GetConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
        System.out.println("Confirm Message: " + confirmMessage);
        driver.quit();
    }

    @Then("^\"([^\"]*)\" message is displayed$")
    public void Message_Is_Displayed(String message) throws Throwable {
        Assert.assertEquals(message, landingPage.getErrorMessage());
        System.out.println(landingPage.getErrorMessage());
        driver.quit();
    }
}
