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

import java.awt.*;
import java.time.Duration;
import java.util.List;

public class RegisterUser {
    public static void main(String[] args)
    {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(5));

        String pass="User1234";
        String mail="fsd.cbd17@gmail.com";
        String productName = "ZARA COAT 3";
        driver.get("https://rahulshettyacademy.com/client");

        //----Registration for new user----//
        driver.findElement(By.xpath("(//a[normalize-space()='Register here'])[1]")).click();
        driver.findElement(By.id("firstName")).sendKeys("FSD");
        driver.findElement(By.id("lastName")).sendKeys("CBD");
        driver.findElement(By.id("userEmail")).sendKeys(mail);
        driver.findElement(By.id("userMobile")).sendKeys("9007860111");
        driver.findElement(By.xpath("(//select[@class='custom-select ng-untouched ng-pristine ng-valid'])")).click();
        driver.findElement(By.xpath("//select[@formcontrolname='occupation'] //option[text()='Engineer']")).click();
        driver.findElement(By.xpath("//input[@value='Male']")).click();
        driver.findElement(By.id("userPassword")).sendKeys(pass);
        driver.findElement(By.id("confirmPassword")).sendKeys(pass);
        driver.findElement(By.xpath("(//input[@type='checkbox'])[1]")).click();
        driver.findElement(By.id("login")).click();

        WebElement toastTitleRS = w.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("#toast-container .toast-title")
                )
        );
        String actualToastTextRS = toastTitleRS.getText().trim();
        System.out.println("Toast Text: " + actualToastTextRS);
        Assert.assertEquals(actualToastTextRS, "Registered Successfully");

        //----User created----//
        w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1.headcolor")));
        System.out.println(driver.findElement(By.cssSelector("h1.headcolor")).getText());
        Assert.assertEquals(driver.findElement(By.cssSelector("h1.headcolor")).getText(),"Account Created Successfully");


        //----Wait until registration toast disappears----//
        w.until(ExpectedConditions.invisibilityOfElementLocated(
                By.cssSelector("#toast-container .toast-title")
        ));

        //----Move to Login page----//
        driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//h1[normalize-space()='Log in']")).getText(),"Log in");

        //----Insert User details to login----//
        driver.findElement(By.id("userEmail")).sendKeys(mail);
        driver.findElement(By.id("userPassword")).sendKeys(pass);
        driver.findElement(By.id("login")).click();

        //----Verify user logged in (Toast)----//
        WebElement toastTitleLS = w.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("#toast-container .toast-title")
                )
        );
        String actualToastTextLS = toastTitleLS.getText().trim();
        System.out.println("Toast Text: " + actualToastTextLS);
        Assert.assertEquals(actualToastTextLS, "Login Successfully");

        //----Wait until login toast disappears----//
        w.until(ExpectedConditions.invisibilityOfElementLocated(
                By.cssSelector("#toast-container .toast-title")));

        //----Wait till all products are visible
        w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
        //----Extract Products, extract and compare to required product and add to cart
        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
        WebElement prod = products.stream().filter(product ->
                product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);

        prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
        //----Wait for loading animation to disappear
       w.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
        //----Verify Add to cart toast appear and verify it
        WebElement toastTitleACS = w.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("toast-container")));
        String actualToastTextACS = toastTitleACS.getText().trim();
        System.out.println("Toast Text: " + actualToastTextACS);
        Assert.assertEquals(actualToastTextACS, "Product Added To Cart");
        //----Go to Cart
        driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
        //----Verify the products in cart is same you added before
        w.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".cartSection h3")));
        List<WebElement> cartProds = driver.findElements(By.cssSelector(".cartSection h3"));
        Boolean match = cartProds.stream().anyMatch(cartprod -> cartprod.getText().trim().equalsIgnoreCase(productName));
        Assert.assertTrue(match);
        driver.findElement(By.cssSelector(".totalRow button")).click();
        //----Enter Sau to country field
        Actions a = new Actions(driver);
        a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"Sau").build().perform();
        w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
        //----Verify Saudi Arabia from list and select it
        List<WebElement> countries = driver.findElements(By.cssSelector(".ta-results button"));
        for (WebElement country : countries) {
            if (country.getText().trim().equals("Saudi Arabia")) {
                country.click();
                break;
            }
        }
        w.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ta-backdrop")));
        driver.findElement(By.cssSelector(".action__submit")).click();

        w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary")));
        String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertEquals(confirmMessage, "THANKYOU FOR THE ORDER.");
        System.out.println("Confirm Message: " + confirmMessage);


        driver.quit();
    }
}
