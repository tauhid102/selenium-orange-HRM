package Setup;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import utils.Utils;

import java.io.IOException;
import java.time.Duration;

public class Setup {
    public WebDriver driver;
    @BeforeTest
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
    @AfterMethod
    public void screenShort(ITestResult result){
        if(ITestResult.FAILURE==result.getStatus()){
            try{
                Utils util=new Utils();
                util.takeScreenSHot(driver);
            } catch (IOException e) {
                System.out.println(e.toString());
            }

        }
    }
    @AfterTest
    public void quitBrowser(){
        try{
            driver.close();
        }
        catch (Exception e){
            driver.quit();
        }
    }
}
