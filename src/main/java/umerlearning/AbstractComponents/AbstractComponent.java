package umerlearning.AbstractComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import umerlearning.pageobjects.CartPage;

import java.time.Duration;

public class AbstractComponent {

    WebDriver driver;
    @FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
    WebElement addToCartButton;

    public AbstractComponent(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public CartPage GoToCartPage()
    {
        addToCartButton.click();
        CartPage cartPage = new CartPage(driver);
        return cartPage;

    }

    public void waitForElementToAppear(By locator)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForElementToDisappear(WebElement ele)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOf(ele));
    }

}
