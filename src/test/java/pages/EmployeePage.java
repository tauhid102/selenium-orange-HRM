package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class EmployeePage {
    @FindBy(className = "oxd-button")
    public List<WebElement> btnAddEmployee;
    @FindBy(css = "[type=submit]")
    public WebElement btnSubmit;
    @FindBy(name = "firstName")
    public WebElement txtFirstname;
    @FindBy(name = "lastName")
    public WebElement txtLastName;
    @FindBy(className = "oxd-switch-input")
    public WebElement toggleButton;
    @FindBy(className = "oxd-input")
    public List<WebElement> userCreds;

    @FindBy(className = "oxd-select-text-input")
    public List<WebElement> dropdownBoxs;
    @FindBy(className = "oxd-input-field-error-message")
    public WebElement userError;

    public EmployeePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public String checkIfUserExists(String userName) {
        userCreds.get(5).sendKeys(userName);
        return userError.getText();
    }

    public void createEmployee(String firstName, String lastName, String userName, String password, String cmdPassword) {

        txtFirstname.sendKeys(firstName);
        txtLastName.sendKeys(lastName);
        userCreds.get(5).sendKeys(userName);
        userCreds.get(6).sendKeys(password);
        userCreds.get(7).sendKeys(cmdPassword);
        btnSubmit.click();
    }
}
