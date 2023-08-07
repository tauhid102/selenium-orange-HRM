package testRunner;

import Setup.Setup;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.EmployeePage;
import pages.LoginPage;
import utils.Utils;

import java.io.IOException;
import java.util.List;

public class EmployeeTestRunner extends Setup {
    LoginPage loginPage;

    @BeforeTest
    public void doLogin() {
        driver.get("https://opensource-demo.orangehrmlive.com");
        loginPage = new LoginPage(driver);
        loginPage.doLogin("Admin", "admin123");
    }

    @Test(priority = 1)
    public void gotoPmipage() {
        List<WebElement> menuList = driver.findElements(By.className("oxd-main-menu-item"));
        menuList.get(1).sendKeys(Keys.ENTER);
        String actualUrl = driver.getCurrentUrl();
        String expected = "pim/viewEmployeeList";
        Assert.assertTrue(actualUrl.contains(expected));
    }

    @Test(priority = 2, description = "Check if user exit")
    public <string> void checkIfUserExists() throws IOException, ParseException, InterruptedException {
        EmployeePage employeePage = new EmployeePage(driver);
        employeePage.btnAddEmployee.get(2).click();
        employeePage.toggleButton.click();
        List data = Utils.readJsonArray("./src/test/resources/user.json");
        JSONObject object = (JSONObject) data.get(data.size() - 1);
        String userName = (String) object.get("username");
        System.out.println(userName);
        String validationMessage = employeePage.checkIfUserExists("sarita007");
        String validationMessageExpected = "Username already exists";
        Assert.assertTrue(validationMessage.contains(validationMessageExpected));
    }

    @Test(priority = 3)
    public void createUser() throws IOException, ParseException, InterruptedException {
        EmployeePage employeePage = new EmployeePage(driver);
        Utils utils = new Utils();
        utils.generateRandomData();
        String firstName = utils.getFirstname();
        String lastName = utils.getLastname();
        int randomId = Utils.randomNumber(1000, 9999);
        String userName = utils.getFirstname() + randomId;
        String password = "123456435a";
        String cmdPassword = password;
        employeePage.userCreds.get(5).clear();
        employeePage.createEmployee(firstName, lastName, userName, password, cmdPassword);
        Thread.sleep(3000);
        List<WebElement> headerTitle = driver.findElements(By.className("orangehrm-main-title"));
        Assert.assertTrue(headerTitle.get(0).isDisplayed());
        Utils.waitForElement(driver, headerTitle.get(0), 50);
        if (headerTitle.get(0).isDisplayed()) {
            utils.saveJsonList(userName, password);
        }
    }
}
