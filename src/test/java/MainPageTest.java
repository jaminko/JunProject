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
        Assert.assertEquals(title, Constants.text.EXPECTED_TEXT_1);
    }

    @Test(description = "Page URL Test")
    public void checkPageUrl() {
        String url = driver.getCurrentUrl();
        Assert.assertEquals(url, Constants.urls.EXPECTED_HOME_PAGE_URL);
    }

    @Test(description = "Page with Ukrainian Language Test")
    public void checkPageLanguageUkr() {
        mainPage.chooseUkrainianLanguage();
        WebElement element01 = driver.findElement(By.xpath("//*[contains(text(),'Пошта')]"));
        Assert.assertEquals(element01.getText(), Constants.text.EXPECTED_TEXT_10);
    }

    @Test(description = "Page with Russian Language Test")
    public void checkPageLanguageRus() {
        mainPage.chooseRussianLanguage();
        WebElement element01 = driver.findElement(By.xpath("//*[contains(text(),'Почта')]"));
        Assert.assertEquals(element01.getText(), Constants.text.EXPECTED_TEXT_11);
    }
    
    @Test(description = "Successful user login Test")
    public void checkValidLogInTest() {
        mainPage.logInFromMainPage(Constants.credentials.USERNAME, Constants.credentials.PASSWORD);
        mainPage.clickSignInButton();
        WebElement element01 = driver.findElement(By.xpath("//span[contains(text(),'tomcruise@i.ua')]"));
        Assert.assertEquals(element01.getText(), Constants.text.EXPECTED_TEXT_2);
    }

    @Test(description = "Login with empty fields Test", priority = 1)
    public void loginWithEmptyFieldsTest() {
        mainPage.logInFromMainPage(Constants.credentials.EMPTY_USERNAME, Constants.credentials.EMPTY_PASSWORD);
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, Constants.urls.EXPECTED_HOME_PAGE_URL);
    }

    @Test(description = "Login with incorrect credentials Test", priority = 1)
    public void loginWithInvalidLoginTest() {
        mainPage.logInFromMainPage(Constants.credentials.INCORRECT_USERNAME, Constants.credentials.INCORRECT_PASSWORD);
        mainPage.clickSignInButton();
        WebElement element01 = driver.findElement(By.xpath("//div[contains(text(),'Невірний логін або пароль')]"));
        Assert.assertEquals(element01.getText(), Constants.text.EXPECTED_TEXT_3);
    }

    @Test(description = "Login with invalid password Test", priority = 1)
    public void loginWithInvalidPasswordTest() {
        mainPage.logInFromMainPage(Constants.credentials.USERNAME, Constants.credentials.INCORRECT_PASSWORD);
        mainPage.clickSignInButton();
        WebElement element01 = driver.findElement(By.xpath("//div[contains(text(),'Невірний логін або пароль')]"));
        Assert.assertEquals(element01.getText(), Constants.text.EXPECTED_TEXT_3);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }
}
