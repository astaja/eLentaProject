import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Test;
import java.time.Duration;

public class FirstTests {
    public static WebDriver driver;

    @BeforeMethod
    public void beforeMethod() {
        driver.get("https://elenta.lt/registracija");
    }
    @AfterMethod
    public void afterMethod() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("https://elenta.lt/registracija");
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

        driver.quit();
    }
    public void acceptCookies() {
        driver.get("https://elenta.lt/");
        driver.findElement(By.xpath("/html/body/div[5]/div[2]/div[1]/div[2]/div[2]/button[1]/p")).click();
    }

    @Test
    public void happyPathTest() { // 1 (positive) test
        int rnd = (int) Math.round(Math.random() * 1000000);
        int rndEmail = (int) Math.round(Math.random() * 1000000);
        String randomEmail = rndEmail + "@gmail.com";

        WebElement searchUserName = driver.findElement(By.id("UserName"));
        searchUserName.sendKeys("MyName" + rnd);
        WebElement searchEmail = driver.findElement(By.id("Email"));
        searchEmail.sendKeys(randomEmail);
        WebElement password = driver.findElement(By.id("Password"));
        password.sendKeys("MyPassword2345");
        WebElement passwordRepeat = driver.findElement(By.id("Password2"));
        passwordRepeat.sendKeys("MyPassword2345");
        WebElement registration = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[11]/td[2]/input"));
        registration.click();
        WebElement successMsgElement = driver.findElement(By.xpath("//*[@id=\"main-container\"]/div[2]/h1/b"));
        String successMsg = successMsgElement.getText();
        Assert.assertEquals(successMsg, "Jūs sėkmingai prisiregistravote!");
    }

    @Test
    public void noUsernameTest() { // 2nd test (without username)
        int rndEmail = (int) Math.round(Math.random() * 1000000);
        String randomEmail = rndEmail + "@gmail.com";

        WebElement searchUserName = driver.findElement(By.id("UserName"));
        searchUserName.sendKeys("");
        WebElement searchEmail = driver.findElement(By.id("Email"));
        searchEmail.sendKeys(randomEmail);
        WebElement password = driver.findElement(By.id("Password"));
        password.sendKeys("MyPassword2345");
        WebElement passwordRepeat = driver.findElement(By.id("Password2"));
        passwordRepeat.sendKeys("MyPassword2345");
        WebElement registration = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[11]/td[2]/input"));
        registration.click();
        WebElement errorUsrnElement = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[1]/td[2]/span"));
        String errorUsrn = errorUsrnElement.getText();
        Assert.assertEquals(errorUsrn, "Įveskite vartotojo vardą.");
    }
    @Test
    public void noEmailTest() { // 3nd test (without email)
        int rnd = (int) Math.round(Math.random() * 1000000);
        int rndEmail = (int) Math.round(Math.random() * 1000000);

        WebElement searchUserName = driver.findElement(By.id("UserName"));
        searchUserName.sendKeys("MyName" + rnd);
        WebElement searchEmail = driver.findElement(By.id("Email"));
        searchEmail.sendKeys("");
        WebElement password = driver.findElement(By.id("Password"));
        password.sendKeys("MyPassword2345");
        WebElement passwordRepeat = driver.findElement(By.id("Password2"));
        passwordRepeat.sendKeys("MyPassword2345");
        WebElement registration = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[11]/td[2]/input"));
        registration.click();
        WebElement errorEmlElement = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[4]/td[2]/span"));
        String errorEml = errorEmlElement.getText();
        Assert.assertEquals(errorEml, "Įveskite el. pašto adresą.");
    }
    @Test
    public void noPsw1Test() { // 4th test (without password1)
        int rnd = (int) Math.round(Math.random() * 1000000);
        int rndEmail = (int) Math.round(Math.random() * 1000000);
        String randomEmail = rndEmail + "@gmail.com";

        WebElement searchUserName = driver.findElement(By.id("UserName"));
        searchUserName.sendKeys("MyName" + rnd);
        WebElement searchEmail = driver.findElement(By.id("Email"));
        searchEmail.sendKeys(randomEmail);
        WebElement password = driver.findElement(By.id("Password"));
        password.sendKeys("");
        WebElement passwordRepeat = driver.findElement(By.id("Password2"));
        passwordRepeat.sendKeys("MyPassword2345");
        WebElement registration = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[11]/td[2]/input"));
        registration.click();
        WebElement errorpsw1Element = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[7]/td[2]/span"));
        String errorpsw = errorpsw1Element.getText();
        Assert.assertEquals(errorpsw, "Įveskite slaptažodį.");
    }
    @Test
    public void noPsw2Test() { // 5th test (without password2)
        int rnd = (int) Math.round(Math.random() * 1000000);
        int rndEmail = (int) Math.round(Math.random() * 1000000);
        String randomEmail = rndEmail + "@gmail.com";

        WebElement searchUserName = driver.findElement(By.id("UserName"));
        searchUserName.sendKeys("MyName" + rnd);
        WebElement searchEmail = driver.findElement(By.id("Email"));
        searchEmail.sendKeys(randomEmail);
        WebElement password = driver.findElement(By.id("Password"));
        password.sendKeys("MyPassword2345");
        WebElement passwordRepeat = driver.findElement(By.id("Password2"));
        passwordRepeat.sendKeys("");
        WebElement registration = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[11]/td[2]/input"));
        registration.click();
        WebElement errorpsw2Element = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[8]/td[2]/span"));
        String errorpsw2 = errorpsw2Element.getText();
        Assert.assertEquals(errorpsw2, "Pakartotinai neįvedėte slaptažodžio.");
    }
    @Test
    public void name1LetterTest() { // 6th test (name is 1 letter)
        int rndEmail = (int) Math.round(Math.random() * 1000000);
        String randomEmail = rndEmail + "@gmail.com";

        WebElement searchUserName = driver.findElement(By.id("UserName"));
        searchUserName.sendKeys("h");
        WebElement searchEmail = driver.findElement(By.id("Email"));
        searchEmail.sendKeys(randomEmail);
        WebElement password = driver.findElement(By.id("Password"));
        password.sendKeys("MyPassword2345");
        WebElement passwordRepeat = driver.findElement(By.id("Password2"));
        passwordRepeat.sendKeys("MyPassword2345");
        WebElement registration = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[11]/td[2]/input"));
        registration.click();
        WebElement errorMsgElement = driver.findElement(By.xpath("//*[@id=\"main-container\"]/div[2]/h1/b"));
        String errorMsg = errorMsgElement.getText();
        Assert.assertEquals(errorMsg, "Jūs sėkmingai prisiregistravote!");
    }
    @Test
    public void nameMltplLettersTest() { // 7th test (name is 259 letters)
        int rndEmail = (int) Math.round(Math.random() * 1000000);
        String randomEmail = rndEmail + "@gmail.com";

        WebElement searchUserName = driver.findElement(By.id("UserName"));
        searchUserName.sendKeys("yywxrxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxy");
        WebElement searchEmail = driver.findElement(By.id("Email"));
        searchEmail.sendKeys(randomEmail);
        WebElement password = driver.findElement(By.id("Password"));
        password.sendKeys("MyPassword2345");
        WebElement passwordRepeat = driver.findElement(By.id("Password2"));
        passwordRepeat.sendKeys("MyPassword2345");
        WebElement registration = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[11]/td[2]/input"));
        registration.click();
        WebElement notifMsgElement = driver.findElement(By.xpath("//*[@id=\"main-container\"]/div[2]/h1/b"));
        String notifMsg = notifMsgElement.getText();
        Assert.assertNotEquals(notifMsg, "Jūs sėkmingai prisiregistravote!");
    }
    @Test
    public void nameMltplDigitsTest() { // 8th test Name consists of multiple digits only
        int rndEmail = (int) Math.round(Math.random() * 1000000);
        String randomEmail = rndEmail + "@gmail.com";

        WebElement searchUserName = driver.findElement(By.id("UserName"));
        searchUserName.sendKeys("123456789234456789");
        WebElement searchEmail = driver.findElement(By.id("Email"));
        searchEmail.sendKeys(randomEmail);
        WebElement password = driver.findElement(By.id("Password"));
        password.sendKeys("MyPassword2345");
        WebElement passwordRepeat = driver.findElement(By.id("Password2"));
        passwordRepeat.sendKeys("MyPassword2345");
        WebElement registration = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[11]/td[2]/input"));
        registration.click();
        WebElement notifMsgElement = driver.findElement(By.xpath("//*[@id=\"main-container\"]/div[2]/h1/b"));
        String notifMsg = notifMsgElement.getText();
        Assert.assertNotEquals(notifMsg, "Jūs sėkmingai prisiregistravote!");
    }
    @Test
    public void nameSpcCharTest() { // 9th test Nmae consits of special signs+spaces
        int rndEmail = (int) Math.round(Math.random() * 1000000);
        String randomEmail = rndEmail + "@gmail.com";

        WebElement searchUserName = driver.findElement(By.id("UserName"));
        searchUserName.sendKeys("----...._____.__.");
        WebElement searchEmail = driver.findElement(By.id("Email"));
        searchEmail.sendKeys(randomEmail);
        WebElement password = driver.findElement(By.id("Password"));
        password.sendKeys("MyPassword2345");
        WebElement passwordRepeat = driver.findElement(By.id("Password2"));
        passwordRepeat.sendKeys("MyPassword2345");
        WebElement registration = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[11]/td[2]/input"));
        registration.click();
        WebElement notifMsgElement = driver.findElement(By.xpath("//*[@id=\"main-container\"]/div[2]/h1/b"));
        String notifMsg = notifMsgElement.getText();
        Assert.assertNotEquals(notifMsg, "Jūs sėkmingai prisiregistravote!");
    }
    @Test
    public void nameMixCharTest() { // 10th test Name consists of letters, digits, dots, spaces, dashes, underscores
        int rndEmail = (int) Math.round(Math.random() * 1000000);
        String randomEmail = rndEmail + "@gmail.com";

        WebElement searchUserName = driver.findElement(By.id("UserName"));
        searchUserName.sendKeys("---98 e928_  8172 g7d82-...._____.veqvqe.veqvevev4g 44w4gtb_ __.");
        WebElement searchEmail = driver.findElement(By.id("Email"));
        searchEmail.sendKeys(randomEmail);
        WebElement password = driver.findElement(By.id("Password"));
        password.sendKeys("MyPassword2345");
        WebElement passwordRepeat = driver.findElement(By.id("Password2"));
        passwordRepeat.sendKeys("MyPassword2345");
        WebElement registration = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[11]/td[2]/input"));
        registration.click();
        WebElement notifMsgElement = driver.findElement(By.xpath("//*[@id=\"main-container\"]/div[2]/h1/b"));
        String notifMsg = notifMsgElement.getText();
        Assert.assertNotEquals(notifMsg, "Jūs sėkmingai prisiregistravote!");
    }
    @Test
    public void noBothPswdsTest() { // 11 test (both passwords are not entered)
        int rnd = (int) Math.round(Math.random() * 1000000);
        int rndEmail = (int) Math.round(Math.random() * 1000000);
        String randomEmail = rndEmail + "@gmail.com";

        WebElement searchUserName = driver.findElement(By.id("UserName"));
        searchUserName.sendKeys("MyName" + rnd);
        WebElement searchEmail = driver.findElement(By.id("Email"));
        searchEmail.sendKeys(randomEmail);
        WebElement registration = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[11]/td[2]/input"));
        registration.click();
        WebElement errorMsgElement = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[7]/td[2]/span"));
        String errorMsg = errorMsgElement.getText();
        Assert.assertEquals(errorMsg, "Įveskite slaptažodį.");
    }
    @Test
    public void noNameNEmailTest() { // 12 test (no name and email)

        WebElement password = driver.findElement(By.id("Password"));
        password.sendKeys("MyPassword2345");
        WebElement passwordRepeat = driver.findElement(By.id("Password2"));
        passwordRepeat.sendKeys("MyPassword2345");
        WebElement registration = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[11]/td[2]/input"));
        registration.click();
        WebElement notifMsgElement = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[1]/td[2]/span"));
        String notifMsg = notifMsgElement.getText();
        Assert.assertEquals(notifMsg, "Įveskite vartotojo vardą.");
        WebElement notifMsg2Element = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[4]/td[2]/span"));
        String notifMsg2 = notifMsg2Element.getText();
        Assert.assertEquals(notifMsg2, "Įveskite el. pašto adresą.");
    }
    @Test
    public void bothPswrdsSpeclSigns() { // 13th test Both Passwords are consist dots, spaces, dashes, underscores
        int rnd = (int) Math.round(Math.random() * 1000000);
        int rndEmail = (int) Math.round(Math.random() * 1000000);
        String randomEmail = rndEmail + "@gmail.com";

        WebElement searchUserName = driver.findElement(By.id("UserName"));
        searchUserName.sendKeys("MyName" + rnd);
        WebElement searchEmail = driver.findElement(By.id("Email"));
        searchEmail.sendKeys(randomEmail);
        WebElement password = driver.findElement(By.id("Password"));
        password.sendKeys("-- -....___ __.__.");
        WebElement passwordRepeat = driver.findElement(By.id("Password2"));
        passwordRepeat.sendKeys("-- -....___ __.__.");
        WebElement registration = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[11]/td[2]/input"));
        registration.click();
        WebElement successMsgElement = driver.findElement(By.xpath("//*[@id=\"main-container\"]/div[2]/h1/b"));
        String successMsg = successMsgElement.getText();
        Assert.assertNotEquals(successMsg, "Jūs sėkmingai prisiregistravote!");
    }







}

