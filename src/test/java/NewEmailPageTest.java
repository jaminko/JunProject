import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class NewEmailPageTest {
    private WebDriver driver;
    private NewEmailPage newEmailPage;
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
        logInPage.clickCreateNewMail();
        newEmailPage = new NewEmailPage(driver);
    }

    @Test(description = "Page Title Test")
    public void PageTitleTest() {
        String title = driver.getTitle();
        Assert.assertEquals(title, "Новий лист - I.UA ");
    }

    @Test(description = "Click on the Cancel Button Test")
    public void CancelButtonTest() {
        newEmailPage.clickOnCancelButton();
        WebElement element01;
        element01 = driver.findElement(By.xpath("//span[contains(text(),'tomcruise@i.ua')]"));
        Assert.assertEquals(element01.getText(), "tomcruise@i.ua");
    }

    @Test(description = "Successful Send the Test Email to jaminko@gmail.com", priority = 1)
    public void setNewEmailTest() {
        newEmailPage.fillRecipientField(Constants.credentials.TEST_RECIPIENT_EMAIL);
        newEmailPage.fillThemeField(Constants.credentials.TEST_THEME);
        newEmailPage.fillBodyField(Constants.credentials.TEST_TEXT);
        newEmailPage.clickOnSendButton();
    }

    @Test(description = "Send the Test Email with empty Recipient Field", priority = 2)
    public void setNewEmailFailTest1() {
        newEmailPage.fillThemeField(Constants.credentials.TEST_THEME);
        newEmailPage.fillBodyField(Constants.credentials.TEST_TEXT);
        newEmailPage.clickOnSendButton();
        driver.switchTo().alert().accept();
        WebElement err;
        err = driver.findElement(By.xpath("//*[text()='Поле \"Кому\" не вказано']"));
        Assert.assertEquals(err.getText(), "Поле \"Кому\" не вказано");
    }

    @Test(description = "Send the Test Email with invalid Recipient Email", priority = 2)
    public void setNewEmailFailTest2() {
        newEmailPage.fillRecipientField(Constants.credentials.INVALID_TEST_RECIPIENT_EMAIL);
        newEmailPage.fillThemeField(Constants.credentials.TEST_THEME);
        newEmailPage.fillBodyField(Constants.credentials.TEST_TEXT);
        newEmailPage.clickOnSendButton();
        driver.switchTo().alert().accept();
        WebElement err;
        err = driver.findElement(By.xpath("//*[text()='Поле \"Кому\" має невірний формат']"));
        Assert.assertEquals(err.getText(), "Поле \"Кому\" має невірний формат");
    }

    @Test(description = "Save the Test Email on the drafts", priority = 2)
    public void saveOnDrafts() {
        newEmailPage.fillRecipientField(Constants.credentials.TEST_RECIPIENT_EMAIL);
        newEmailPage.fillThemeField(Constants.credentials.TEST_THEME);
        newEmailPage.fillBodyField(Constants.credentials.TEST_TEXT);
        newEmailPage.clickOnSaveInDraftsButton();
        WebElement element01;
        element01 = driver.findElement(By.xpath("//div[contains(text(),'Лист успішно збережено')]"));
        Assert.assertEquals(element01.getText(), "Лист успішно збережено");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }
}
