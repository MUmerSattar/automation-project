package umerlearning.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import umerlearning.AbstractComponents.AbstractComponent;

import java.util.List;

public class OrderPage extends AbstractComponent {
    WebDriver driver;
    //PageFactory
    //@FindBy(css= "tr td:nth-child(3)")
    @FindBy(xpath= "//tbody/tr/td[2]")
    List<WebElement> orderProductNames;

    public OrderPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public Boolean VerifyOrdersDisplay(String productName) throws InterruptedException {
        //Thread.sleep(5000);
        waitForElementToAppear(By.xpath("//tbody/tr/td"));
        Boolean match = orderProductNames.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
        return match;
    }

}
