import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class MainClass {
    static WebDriver driver;

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(Constants.urls.TEST_PAGE);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

        MainPage mainPage = new MainPage(driver);
        mainPage.logInFromMainPage("tomcruise", "qwe123!");
        mainPage.clickSignInButton();
        driver.findElement(By.xpath("//*[text()='Створити листа']")).click();
        driver.findElement(By.xpath("//*[@src='images/closeBtn.png']")).click();
        WebElement element01 = driver.findElement(By.xpath("//*[@name='send' and contains (@tabindex, '12')]"));
        Actions builder1 = new Actions(driver);
        builder1.moveToElement(element01).doubleClick().pause(500).sendKeys(Keys.ENTER).build().perform();

    }
}

