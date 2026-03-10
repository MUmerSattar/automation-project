package umerlearning.AbstractComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import umerlearning.pageobjects.CartPage;
import umerlearning.pageobjects.OrderPage;

import java.time.Duration;

public class AbstractComponent {

    WebDriver driver;
    @FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
    WebElement GoToCartButton;

    @FindBy(xpath = "//button[@routerlink='/dashboard/myorders']")
    WebElement GoToOrderButton;

    @FindBy(css = ".ngx-spinner-overlay")
    WebElement spinner;

    public AbstractComponent(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public CartPage GoToCartPage()
    {
        waitForElementToDisappear(spinner);
        GoToCartButton.click();
        CartPage cartPage = new CartPage(driver);
        return cartPage;
    }

    public OrderPage GoToOrderPage()
    {
        waitForElementToDisappear(spinner);
        GoToOrderButton.click();
        OrderPage orderPage = new OrderPage(driver);
        return orderPage;
    }


    public void waitForElementToAppear(By locator)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForWebElementToAppear(WebElement locator)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(locator));
    }

    public void waitForElementToDisappear(WebElement ele)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOf(ele));
    }

    public void waitForWebElementToClickable(WebElement locator)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

}
