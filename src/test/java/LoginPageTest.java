import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
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
        WebElement element01;
        element01 = driver.findElement(By.xpath("//span[contains(text(),'tomcruise@i.ua')]"));
        Assert.assertEquals(element01.getText(), "tomcruise@i.ua");
        logInPage = new LogInPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, 10);
    }

    @Test(description = "Page Title Test")
    public void PageTitleTest() {
        String title = driver.getTitle();
        Assert.assertEquals(title, "Вхідні - I.UA ");
    }

    @Test(description = "Inbox mails Page Test", priority = 1)
    public void InboxTest() {
        logInPage.clickInbox();
        String title = driver.getTitle();
        Assert.assertEquals(title, "Вхідні - I.UA ");
    }

    @Test(description = "Send mails Page Test", priority = 1)
    public void SendTest() {
        logInPage.clickSent();
        System.out.println(driver.getTitle());
        String title = driver.getTitle();
        Assert.assertEquals(title, "Відправлені - I.UA ");
    }

    @Test(description = "User LogOut Test", priority = 2)
    public void LogOutTest() {
        logInPage.LogOut();
        String title = driver.getTitle();
        Assert.assertEquals(title, "І.UA - твоя пошта ");
    }

    @Test(description = "Create New Email Test", priority = 2)
    public void createNewEmailTest() {
        logInPage.clickCreateNewMail();
        String title = driver.getTitle();
        Assert.assertEquals(title, "Новий лист - I.UA ");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }
}
