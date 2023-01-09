import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.HomePage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class MyntraTests {

    WebDriver driver;

    HomePage homePage;

    @BeforeMethod
    public void setup() throws IOException {
        String driverPath = System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();

       homePage=new HomePage(driver);



        //documentationPage=new DocumentationPage(driver);


        String rootFolder=System.getProperty("user.dir");
        System.out.println("rootfolder:"+rootFolder);

        Properties propObj=new Properties();
        propObj.load(new FileInputStream(rootFolder+"//src//test//resources//data.properties"));
        String appUrl=propObj.getProperty("appurl");


        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
        driver.get(appUrl);

    }


    @Test(priority = 1)
    public void verifyRedmi10Search() throws InterruptedException, IOException {
        homePage.launchHomePage();
        homePage.SearchThenVerify();
    }


    @Test(priority = 2)
    public void verifyAddToCart() throws IOException, InterruptedException {
        homePage.launchHomePage();
        homePage.verifyAddToCart();
        homePage.takeCartScreenshot();


    }



    @AfterMethod
    public void tearDown() throws IOException {

        driver.quit();
    }






}
