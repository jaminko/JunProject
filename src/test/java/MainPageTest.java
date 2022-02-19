import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class MainPageTest {
    private WebDriver driver;
    private MainPage mainPage;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get(Constants.urls.TEST_PAGE);
        mainPage = new MainPage(driver);
    }

    @Test(description = "Page Title Test")
    public void checkPageTitle() {
        String title = driver.getTitle();
        Assert.assertEquals(title, "І.UA - твоя пошта ");
    }

    @Test(description = "Page URL Test")
    public void checkPageUrl() {
        String url = driver.getCurrentUrl();
        Assert.assertEquals(url, "https://www.i.ua/");
    }

    @Test(description = "Successful user login Test")
    public void checkValidLogInTest() {
        mainPage.logInFromMainPage("tomcruise", "qwe123!");
        mainPage.clickSignInButton();
        WebElement element01;
        element01 = driver.findElement(By.xpath("//span[contains(text(),'tomcruise@i.ua')]"));
        Assert.assertEquals(element01.getText(), "tomcruise@i.ua");
    }

    @Test(description = "Login with empty fields Test", priority = 1)
    public void loginWithEmptyFieldsTest() {
        mainPage.logInFromMainPage("", "");
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://www.i.ua/");
    }

    @Test(description = "Login with incorrect credentials Test", priority = 1)
    public void loginWithInvalidLoginTest() {
        mainPage.logInFromMainPage("bradpitt", "12345");
        mainPage.clickSignInButton();
        WebElement element02;
        element02 = driver.findElement(By.xpath("//div[contains(text(),'Невірний логін або пароль')]"));
        Assert.assertEquals(element02.getText(), "Невірний логін або пароль");
    }

    @Test(description = "Login with incorrect password Test", priority = 1)
    public void loginWithInvalidPasswordTest() {
        mainPage.logInFromMainPage("tomcruise", "12345");
        mainPage.clickSignInButton();
        WebElement element03;
        element03 = driver.findElement(By.xpath("//div[contains(text(),'Невірний логін або пароль')]"));
        Assert.assertEquals(element03.getText(), "Невірний логін або пароль");
        driver.navigate().back();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }
}
