package umerlearning.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import umerlearning.AbstractComponents.AbstractComponent;

import java.time.Duration;

public class ConfirmationPage extends AbstractComponent {
    WebDriver driver;

    //PageFactory
    @FindBy(css= ".hero-primary")
    WebElement confirmationMessage;


    public ConfirmationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String GetConfirmationMessage() throws InterruptedException {
        Thread.sleep(Duration.ofSeconds(5));
        return confirmationMessage.getText();
    }

}
