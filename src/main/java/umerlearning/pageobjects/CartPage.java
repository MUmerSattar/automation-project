package umerlearning.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import umerlearning.AbstractComponents.AbstractComponent;

import java.util.List;

public class CartPage extends AbstractComponent {
    WebDriver driver;
    //PageFactory
    @FindBy(css= ".cartSection h3")
    List<WebElement> productTitles;

    @FindBy(css= ".totalRow button")
    WebElement checkOutButton;


    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean VerifyProductDisplay(String productName) throws InterruptedException {
        Thread.sleep(5000);
        Boolean match = productTitles.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
        return match;
    }

    public CheckOutPage GoToCheckOut()
    {
        checkOutButton.click();
        CheckOutPage checkout = new CheckOutPage(driver);
        return checkout;

    }
}
