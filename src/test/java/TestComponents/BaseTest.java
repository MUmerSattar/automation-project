package TestComponents;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;
import umerlearning.pageobjects.LandingPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {

    public WebDriver driver;
    public LandingPage landingPage;

    public WebDriver intializeDriver() throws IOException {
        Properties prop =  new Properties();
        FileInputStream input = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//umerlearning//resources//GlobalData.properties");
        prop.load(input);
        String browserName = System.getProperty("browser")!=null ? System.getProperty("browser"): prop.getProperty("browser");

        if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();

        }
        else if(browserName.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();

        }
        else if(browserName.equalsIgnoreCase("edge")){
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();

        }
        driver.manage().window().maximize();
        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
        return driver;
    }

    public List<HashMap<String, String>> getJsonDataToHashMap(String filepath) throws IOException {
        //reading json and convert json to string
        String jsonContent = FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);
        //converting string to hashmap
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){
        });
        return data;

    }

    public String getScreenshot (String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts =  (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String reportDir = System.getProperty("user.dir") + File.separator + "reports";
        new File(reportDir).mkdirs();
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());


        String destinationPath = reportDir + File.separator + testCaseName +"_"+ timestamp+ ".png";
        File destinationPathFile = new File(destinationPath);
        FileUtils.copyFile(source,destinationPathFile);
        return testCaseName+"_"+ timestamp+".png";
    }



    @BeforeMethod(alwaysRun = true)
    public void launchApplication() throws IOException {
        driver = intializeDriver();
        landingPage = new LandingPage(driver);
        landingPage.GoToLandingPage();
    }

    @AfterMethod(alwaysRun = true)
    public void TearDown() {
        driver.close();
    }
}
