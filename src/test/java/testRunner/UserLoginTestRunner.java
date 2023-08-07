package testRunner;

import Setup.Setup;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.EmployeePage;
import pages.LoginPage;
import utils.Utils;

import java.io.IOException;
import java.security.Key;
import java.util.List;

public class UserLoginTestRunner extends Setup {
    @BeforeTest
    public void doLogin() throws IOException, ParseException {
        driver.get("https://opensource-demo.orangehrmlive.com");
        List data = Utils.readJsonArray("./src/test/resources/user.json");
        JSONObject obj = (JSONObject) data.get(data.size() - 1);
        String userName = (String) obj.get("username");
        String password = (String) obj.get("password");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(userName, password);
        List<WebElement> userMenu = driver.findElements(By.className("oxd-main-menu-item"));
        userMenu.get(2).click();
        String acutalUrl = driver.getCurrentUrl();
        String employeUrl = "viewPersonalDetails";
        Assert.assertTrue(acutalUrl.contains(employeUrl));
    }

    @Test
    public void updateUserInfo() throws InterruptedException {
        List<WebElement> mainTitle = driver.findElements(By.className("orangehrm-main-title"));
        Utils.waitForElement(driver, mainTitle.get(0), 50);
        if (mainTitle.get(0).isDisplayed()) {
            EmployeePage employeePage = new EmployeePage(driver);
            employeePage.dropdownBoxs.get(0).click();
            employeePage.dropdownBoxs.get(0).sendKeys("b");
            employeePage.dropdownBoxs.get(0).sendKeys(Keys.ARROW_DOWN);
            employeePage.dropdownBoxs.get(0).sendKeys(Keys.ARROW_DOWN);
            employeePage.dropdownBoxs.get(0).sendKeys(Keys.ENTER);
            Utils.scrollDown(driver);
            List<WebElement> btnSubmit = driver.findElements(By.cssSelector("[type=submit]"));
            btnSubmit.get(0).click();
            Thread.sleep(3000);
            driver.navigate().refresh();

            List<WebElement> list = driver.findElements(By.className("oxd-select-text-input"));
            String country = list.get(0).getText();
            System.out.println(country);
            Assert.assertTrue(country.contains("Bangladeshi"));
        }
    }
//    @AfterTest
//    public void doLogout(){
//        DashboardPage dashboardPage=new DashboardPage(driver);
//        dashboardPage.btnProfileImage.click();
//        dashboardPage.linkLogout.click();
//    }
}
