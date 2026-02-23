package umerlearning.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import umerlearning.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
    WebDriver driver;
    public LandingPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    //PageFactory
    @FindBy(id = "userEmail")
    WebElement mail;
    @FindBy(id = "userPassword")
    WebElement pass;
    @FindBy(id="login")
    WebElement loginClick;

    public ProductCatalogue loginUser(String email, String password)
    {
        mail.sendKeys(email);
        pass.sendKeys(password);
        loginClick.click();
        ProductCatalogue productCatalogue =  new ProductCatalogue(driver);
        return productCatalogue;
    }

    public void GoToLandingPage()
    {
        driver.get("https://rahulshettyacademy.com/client");
    }
}
