package utils;

import com.github.javafaker.Faker;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class Utils {
    public static void scrollDown(WebDriver driver){
        JavascriptExecutor js=(JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }
    public static int randomNumber(int max,int min){
        double randomValue = Math.random();
        int range = max - min + 1;
        int randomNumber = (int) (randomValue * range) + min;

        return randomNumber;
    }
    private String firstname;
    private String lastname;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void generateRandomData(){
        Faker faker =new Faker();
        setFirstname(faker.name().firstName());
        setLastname(faker.name().lastName());
    }
    public void saveJsonList(String Username,String password) throws IOException, ParseException {
        JSONParser parser=new JSONParser();
        String fileName="./src/test/resources/user.json";
        Object obj= parser.parse(new FileReader(fileName));
        JSONArray jsonArray= (JSONArray) obj;

        JSONObject jsonUser=new JSONObject();
        jsonUser.put("username",Username);
        jsonUser.put("password",password);
        jsonArray.add(jsonUser);
        FileWriter fileWriter=new FileWriter(fileName);
        fileWriter.write(jsonArray.toJSONString());
        fileWriter.flush();
        fileWriter.close();
        System.out.println("Save Data");
    }
    public static List readJsonArray(String Filename) throws IOException, ParseException {
        JSONParser parser =new JSONParser();
        Object obj= parser.parse(new FileReader(Filename));
        JSONArray jsonArray=(JSONArray) obj;
        return jsonArray;
    }
    public static void waitForElement(WebDriver driver, WebElement element,int time_seconds){
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(time_seconds));
        wait.until(ExpectedConditions.visibilityOf(element));
    }



}
