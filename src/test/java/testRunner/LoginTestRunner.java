package testRunner;

import Setup.Setup;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import utils.Utils;

import java.util.List;

public class LoginTestRunner extends Setup {
    LoginPage loginPage;
    DashboardPage dashboard;

    @Test(priority = 1)
    public void doLogin() {
        loginPage = new LoginPage(driver);
        driver.get("https://opensource-demo.orangehrmlive.com");
        loginPage.doLogin("Admin", "admin123");
        String actualString = driver.getCurrentUrl();
        String expectedUrl = "dashboard/index";
        Assert.assertTrue(actualString.contains(expectedUrl));
    }

    @Test(priority = 2)
    public void isImageDisplay() {
        boolean imageDisplayStatus = loginPage.imageDisplay.isDisplayed();
        Assert.assertTrue(imageDisplayStatus);
    }

    @Test(priority = 3)
    public void gotoPmipage() {
        List<WebElement> menuList = driver.findElements(By.className("oxd-main-menu-item"));
        menuList.get(1).sendKeys(Keys.ENTER);
        String actualUrl = driver.getCurrentUrl();
        String expected = "pim/viewEmployeeList";
        Assert.assertTrue(actualUrl.contains(expected));
    }

    @Test(priority = 4)
    public void employeeStatus() {
        List<WebElement> dropDowns = driver.findElements(By.className("oxd-select-text-input"));
        dropDowns.get(0).click();
        dropDowns.get(0).sendKeys("f");
        dropDowns.get(0).sendKeys(Keys.ARROW_DOWN);
        dropDowns.get(0).sendKeys(Keys.ARROW_DOWN);
        dropDowns.get(0).sendKeys(Keys.ARROW_DOWN);
        dropDowns.get(0).sendKeys(Keys.ENTER);
        dropDowns.get(1).click();
        driver.findElement(By.cssSelector("[type=submit]")).click();
    }

    @Test(priority = 5)
    public void getEmployeeStatus() {
//        Utils.scrollDown(driver);

        WebElement table = driver.findElement(By.className("oxd-table-body"));
        List<WebElement> allRows = table.findElements(By.cssSelector(("[role=row]")));

        for (WebElement row : allRows) {
            List<WebElement> cells = row.findElements(By.cssSelector("[role=cell]"));
            //            System.out.println(cells.get(5).getText());
        }

    }

    @Test(priority = 6)
    public void doLogout() {
        DashboardPage dashboard = new DashboardPage(driver);
        dashboard.btnProfileImage.click();
        dashboard.linkLogout.click();

    }
}
