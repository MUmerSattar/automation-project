package umerlearning;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import TestComponents.BaseTest;
import umerlearning.pageobjects.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {
    String ProductName = "ZARA COAT 3";

    @Test(dataProvider="Data",groups = {"Purchase"})
    public void Submitorder(HashMap<String,String> input) throws InterruptedException, IOException {

        ProductCatalogue productCatalogue = landingPage.loginUser(input.get("email"),input.get("password"));
        List<WebElement> products = productCatalogue.getProductsList();
        productCatalogue.addProductToCart(input.get("product"));
        CartPage cartPage = productCatalogue.GoToCartPage();
        boolean match = cartPage.VerifyProductDisplay(input.get("product"));
        Assert.assertTrue(match);
        CheckOutPage checkout = cartPage.GoToCheckOut();
        checkout.SelectCountry("Sau");
        ConfirmationPage confirmationPage = checkout.submitOrder();
        String confirmMessage = confirmationPage.GetConfirmationMessage();
        Assert.assertEquals(confirmMessage, "THANKYOU FOR THE ORDER.");
        System.out.println("Confirm Message: " + confirmMessage);
    }

    @Test(dependsOnMethods = {"Submitorder"})
    public void OrderHistoryTest() throws InterruptedException {
        ProductCatalogue productCatalogue = landingPage.loginUser("fsd.cbd17@gmail.com","User1234");
        OrderPage orderPage = productCatalogue.GoToOrderPage();
        Assert.assertTrue(orderPage.VerifyOrdersDisplay(ProductName));
    }

    @DataProvider
    public Object[][] Data() throws IOException {
        List<HashMap<String,String>> data= getJsonDataToHashMap(System.getProperty("user.dir")+ "//src//test//java//data//PurchaseOrder.json");
        return new Object[][]{{data.get(0)},{data.get(1)}};
    }


//    @DataProvider
//    public Object[][] Data() {
//
//        return new Object[][]{{"fsd.cbd12@gmail.com","User1234","ZARA COAT 3"},{"fsd.cbd13@gmail.com","User1234","ADIDAS ORIGINAL"}};
//    }
//@DataProvider
//public Object[][] Data() throws IOException {
//        HashMap<String,String> map = new HashMap<String,String>();
//        map.put("email","fsd.cbd12@gmail.com");
//        map.put("password","User1234");
//        map.put("product","ZARA COAT 3");
//
//        HashMap<String,String> map1 = new HashMap<String,String>();
//        map1.put("email","fsd.cbd13@gmail.com");
//        map1.put("password","User1234");
//        map1.put("product","ADIDAS ORIGINAL");
//
//    List<HashMap<String,String>> data= getJsonDataToHashMap(System.getProperty("user.dir")+ "//src//test//java//data//PurchaseOrder.json");
//    return new Object[][]{{data.get(0)},{data.get(1)}};
//}
}
