package stepDefinitions;

import TestComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import umerlearning.pageobjects.LandingPage;
import umerlearning.pageobjects.ProductCatalogue;

import java.io.IOException;
import java.util.List;

public class StepDefinitionImp extends BaseTest {
    public LandingPage landingPage;
    public ProductCatalogue productCatalogue;

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


}
