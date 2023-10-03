import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Test;

import java.time.Duration;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class ELenta2 {
    public static WebDriver driver;

    @BeforeMethod
    public void beforeMethod() {
        driver.get("https://elenta.lt/patalpinti/ivesti-informacija?categoryId=BuitisLaisvalaikis_Kolekcionavimas&actionId=Iesko&returnurl=%2F");
        driver.findElement(By.id("title")).clear();
        driver.findElement(By.id("text")).clear();
        driver.findElement(By.id("price")).clear();
        driver.findElement(By.id("location-search-box")).clear();
        driver.findElement(By.id("phone")).clear();
        driver.findElement(By.id("email")).clear();
    }

    @AfterMethod
    public void afterMethod() {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // waits until the page loads
        acceptCookies();
    }

    @AfterClass
    public void afterClass() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.quit();
    }

    public void acceptCookies() {
        driver.get("https://elenta.lt/");
        driver.findElement(By.xpath("/html/body/div[5]/div[2]/div[1]/div[2]/div[2]/button[1]/p")).click();
    }
    @Test
    public void addNewAdTest() { // Nr 1 positive Test
        int rnd = (int) Math.round(Math.random() * 10000);

        driver.findElement(By.id("title")).sendKeys("Monetos" + rnd);
        driver.findElement(By.id("text")).sendKeys(rnd + "Description");
        driver.findElement(By.id("price")).sendKeys(rnd + "0");
        driver.findElement(By.id("location-search-box")).sendKeys("Kaunas");
        driver.findElement(By.id("phone")).sendKeys("+37061111111");
        driver.findElement(By.id("email")).sendKeys(rnd + "@gmail.com");
        driver.findElement(By.id("submit-button")).click();

        String filePath = System.getProperty("user.dir") + "/TestPictures/coins.png";
        WebElement upload_file = driver.findElement(By.id("inputfile")); // uploads file from project
        upload_file.sendKeys(filePath);

        driver.findElement(By.id("forward-button")).click();
        driver.findElement(By.id("forward-button")).click();
        driver.findElement(By.className("action")).click();

        WebElement successMsgElement = driver.findElement(By.xpath("//*[@id=\"main-container\"]/h4"));
        String successMsg = successMsgElement.getText();
        Assert.assertEquals(successMsg, "SKELBIMAS AKTYVUS");
    }

    @Test
    public void noTitleTest() { // Nr 2 negative Test. No title entered into the title field
        int rnd = (int) Math.round(Math.random() * 10000);
        fillForm("", rnd + "Description", rnd + "0", "Kaunas", "+37061111111", rnd + "@gmail.com");
        String errorMsg = driver.findElement(By.id("te")).getText();
        Assert.assertEquals(errorMsg, "Įveskite skelbimo pavadinimą");
    }

    @Test
    public void noDescrTest() { // Nr 3 negative Test. No description entered into the description field
        int rnd = (int) Math.round(Math.random() * 10000);
        fillForm("Monetos" + rnd, " ", rnd + "0", "Kaunas", "+37061111111", rnd + "@gmail.com");
        String errorMsg = driver.findElement(By.id("txte")).getText();
        Assert.assertEquals(errorMsg, "Įveskite skelbimo aprašymą");
    }

    @Test
    public void noPhoneTest() { // Nr 4 negative Test. No phone nr entered into the phone field
        int rnd = (int) Math.round(Math.random() * 10000);
        fillForm("Monetos" + rnd,  rnd + "Description", rnd + "0", "Kaunas","", rnd + "@gmail.com");
        String errorMsg = driver.findElement(By.id("ce")).getText();
        Assert.assertEquals(errorMsg, "Įveskite telefono numerį");
    }

    public void fillForm(String title, String text, String price, String town, String phone, String email) { // not a test
        driver.findElement(By.id("title")).sendKeys(title);
        driver.findElement(By.id("text")).sendKeys(text);
        driver.findElement(By.id("price")).sendKeys(price);
        driver.findElement(By.id("location-search-box")).sendKeys(town);
        driver.findElement(By.id("phone")).sendKeys(phone);
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("submit-button")).click();
    }
}
