import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class LoginPageTest {
    private WebDriver driver;
    private LogInPage logInPage;
    private MainPage mainPage;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get(Constants.urls.TEST_PAGE);
        mainPage = new MainPage(driver);
        mainPage.logInFromMainPage(Constants.credentials.USERNAME, Constants.credentials.PASSWORD);
        mainPage.clickSignInButton();
        WebElement element01 = driver.findElement(By.xpath("//span[contains(text(),'tomcruise@i.ua')]"));
        Assert.assertEquals(element01.getText(), Constants.text.EXPECTED_TEXT_2);
        logInPage = new LogInPage(driver);
    }

    @Test(description = "Page Title Test")
    public void pageTitleTest() {
        String title = driver.getTitle();
        Assert.assertEquals(title, Constants.text.EXPECTED_TEXT_4);
    }

    @Test(description = "Inbox mails Page Test", priority = 1)
    public void inboxTest() {
        logInPage.clickInbox();
        String title = driver.getTitle();
        Assert.assertEquals(title, Constants.text.EXPECTED_TEXT_4);
    }

    @Test(description = "Send mails Page Test", priority = 1)
    public void sendTest() {
        logInPage.clickSent();
        String title = driver.getTitle();
        Assert.assertEquals(title, Constants.text.EXPECTED_TEXT_5);
    }

    @Test(description = "User LogOut Test", priority = 2)
    public void logOutTest() {
        logInPage.LogOut();
        String title = driver.getTitle();
        Assert.assertEquals(title, Constants.text.EXPECTED_TEXT_1);
    }

    @Test(description = "Create New Email Test", priority = 2)
    public void createNewEmailTest() {
        logInPage.clickCreateNewMail();
        String title = driver.getTitle();
        Assert.assertEquals(title, Constants.text.EXPECTED_TEXT_6);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }
}
