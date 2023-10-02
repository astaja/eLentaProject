import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class ELenta2 {
    public static WebDriver driver;

    @BeforeMethod
    public void beforeMethod() {
        WebElement startAdd = driver.findElement(By.id("submit-new-ad-nav-button"));
        startAdd.click();
        driver.findElement(By.xpath("//*[@id=\"select-top-category-list\"]/li[4]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"select-sub-category-list\"]/li[16]/a")).click();
    }

    @AfterMethod
    public void afterMethod() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3)); // waits until the page loads
        acceptCookies();
    }

    @AfterClass
    public void afterClass() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
        driver.quit();
    }

    public void acceptCookies() {
        driver.get("https://elenta.lt/");
        driver.findElement(By.xpath("/html/body/div[5]/div[2]/div[1]/div[2]/div[2]/button[1]/p")).click();
    }
    @Test
    public void addNewAdTest () { // positive
        int rnd = (int) Math.round(Math.random() * 1000000);
        int rndEmail = (int) Math.round(Math.random() * 1000000);
        String randomEmail = rndEmail + "@gmail.com";
        int rndDescr = (int) Math.round(Math.random() * 1000000);
        String randomDescr = Integer.toString(rndDescr);
        int rnd2 = (int) Math.round(Math.random() * 1000);
        String rndPrice = rnd2 + "";

        WebElement searchTitle = driver.findElement(By.id("title"));
        searchTitle.sendKeys("Monetos" + rnd);
        WebElement searchDescr = driver.findElement(By.id("text"));
        searchDescr.sendKeys(randomDescr);
        WebElement searchPrice = driver.findElement(By.id("price"));
        searchPrice.sendKeys(rndPrice);
        WebElement searchTown = driver.findElement(By.id("location-search-box"));
        searchTown.sendKeys("Kaunas");
        WebElement searchPhone = driver.findElement(By.id("phone"));
        searchPhone.sendKeys("+37061111111");
        WebElement searchEmail = driver.findElement(By.id("email"));
        searchEmail.sendKeys(randomEmail);
        WebElement submitBtn = driver.findElement(By.id("submit-button"));
        submitBtn.click();


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

}
