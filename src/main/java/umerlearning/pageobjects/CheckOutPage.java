package umerlearning.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import umerlearning.AbstractComponents.AbstractComponent;

import java.util.List;

public class CheckOutPage extends AbstractComponent {
    WebDriver driver;

    //PageFactory
    @FindBy(css= "[placeholder='Select Country']")
    WebElement country;

    @FindBy(css = ".ta-results button")
    private List<WebElement> countryOptions;

    @FindBy(css= ".action__submit")
    WebElement submit;

    By results = By.cssSelector(".ta-results button");


    public CheckOutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectCountryBySearchInList(String countryName) {
        for (WebElement country : countryOptions) {
            if (country.getText().trim().equalsIgnoreCase(countryName)) {
                country.click();
                break;
            }
        }
    }

    public void SelectCountry(String countryName)
    {
        Actions a = new Actions(driver);
        a.sendKeys(country,countryName).build().perform();
        waitForElementToAppear(results);
        selectCountryBySearchInList("Saudi Arabia");
    }

    public ConfirmationPage submitOrder()
    {
        submit.click();
        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        return confirmationPage;

    }
}
