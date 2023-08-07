package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    @FindBy(name = "username")
    WebElement txtUserName;
    @FindBy(name = "password")
    WebElement tstPassword;
    @FindBy(css = "[type=submit]")
    WebElement btnSubmit;
    @FindBy(className = "oxd-userdropdown-img")
    public WebElement imageDisplay;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void doLogin(String userName, String password) {
        txtUserName.sendKeys(userName);
        tstPassword.sendKeys(password);
        btnSubmit.click();
    }
}
