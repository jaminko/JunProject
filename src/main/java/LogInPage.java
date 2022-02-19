import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogInPage {
    private WebDriver driver;

    public LogInPage(WebDriver driver) {
        this.driver = driver;
    }

    private By createNewMail = By.xpath("//*[text()='Створити листа']");
    private By inbox = By.xpath("//*[text()=' Вхідні']");
    private By sent = By.xpath("//a[contains(text(),'Відправлені')]");
    private By settings = By.xpath("//*[@title='Налаштування' and contains (@class, 'icon-ho ho_settings ho_i_settings')]");
    private By logOut = By.xpath("//*[text()='Вийти']");

    public LogInPage clickInbox() {
        driver.findElement(inbox).click();
        return this;
    }

    public LogInPage clickSent() {
        driver.findElement(sent).click();
        return this;
    }

    public LogInPage LogOut() {
        driver.findElement(settings).click();
        driver.findElement(logOut).click();
        return this;
    }

    public LogInPage clickCreateNewMail() {
        driver.findElement(createNewMail).click();
        return this;
    }
}
