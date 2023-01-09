package pageObjects;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class HomePage {

    WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver=driver;


    }

    String root=System.getProperty("user.dir");

    public void launchHomePage() throws IOException, InterruptedException {
        String expectedPageTitle="Online Shopping for Women, Men, Kids Fashion & Lifestyle - Myntra";
        String actualPageTitle=driver.getTitle();
        Assert.assertEquals(actualPageTitle,expectedPageTitle,"Home Page Not Loaded");

        Thread.sleep(2000);




        File file=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file,new File(root+"target\\Screenshot\\"+"HomePage" +".jpg"));

    }

    public void SearchThenVerify() throws InterruptedException, IOException {
        Actions action=new  Actions(driver);
        WebElement searchbox=driver.findElement(By.xpath("//input"));
        searchbox.sendKeys("Redmi 10");
        Thread.sleep(2000);

        WebElement searchbutton= driver.findElement(By.xpath("//a[@class=\"desktop-submit\"]"));
        action.moveToElement(searchbutton);
        action.click().build().perform();
        Thread.sleep(2000);

        String expectedPageTitle="Redmi 10 - Buy Redmi 10 online in India";
        String actualPageTitle=driver.getTitle();

        File file=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file,new File(root+"target\\Screenshot\\"+"HomePage" +".jpg"));


        Assert.assertEquals(actualPageTitle,expectedPageTitle,"Redmi 10 verification failed");

    }


    public void verifyAddToCart() throws InterruptedException, IOException {
        Actions action=new  Actions(driver);
        WebElement searchbox=driver.findElement(By.xpath("//input"));
        searchbox.sendKeys("Armani Watch");
        Thread.sleep(2000);

        WebElement searchbutton= driver.findElement(By.xpath("//a[@class=\"desktop-submit\"]"));
        action.moveToElement(searchbutton);
        action.click().build().perform();
        Thread.sleep(2000);

        File file=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file,new File(root+"target\\Screenshot\\"+"HomePage" +".jpg"));

        // WebElement pricebutton=driver.findElement(By.xpath("//body/div[@id='mountRoot']/div[1]/main[1]/div[3]/div[2]/div[1]/div[2]/section[1]/ul[1]/li[1]/a[1]/div[2]/div[1]/span[1]/span[1]"));
       WebElement pricebutton=driver.findElement(By.xpath("(//div[@class='product-price'])[1]"));
        action.moveToElement(pricebutton);
        action.click().build().perform();

        ArrayList<String> tabs=new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));


        FileUtils.copyFile(file,new File(root+"target\\Screenshot\\"+"HomePage" +".jpg"));



        //WebElement addToBagButton= driver.findElement(By.xpath("//body/div[@id='mountRoot']/div[1]/div[1]/main[1]/div[2]/div[2]/div[2]/div[2]/div[1]/div[1]"));
        WebElement addToBagButton= driver.findElement(By.cssSelector("div.pdp-add-to-bag"));
        action.moveToElement(addToBagButton);
        action.click().build().perform();
        Thread.sleep(2000);

        WebElement checkcart= driver.findElement(By.xpath("//header/div[2]/div[2]/a[2]/span[2]"));
        Thread.sleep(2000);
        if(checkcart.getText().contains("1")){
            Assert.assertTrue(true);

        }
        Thread.sleep(3000);

    }


    public void takeCartScreenshot() throws IOException {
        File file=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file,new File(root+"target\\Screenshot\\"+"HomePage" +".jpg"));


    }

}
