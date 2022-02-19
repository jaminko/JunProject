import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {

    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    private By loginfield = By.xpath("//*[@name='login' and contains (@tabindex, '1')]");
    private By passfield = By.xpath("//*[@name='pass' and contains (@tabindex, '2')]");
    private By signInButton = By.xpath("//*[@type='submit' and contains (@tabindex, '5')]");

    public MainPage enterUsername(String username) {
        driver.findElement(loginfield).sendKeys(username);
        return this;
    }

    public MainPage enterPassword(String password) {
        driver.findElement(passfield).sendKeys(password);
        return this;
    }

    public MainPage clickSignInButton() {
        driver.findElement(signInButton).click();
        return this;
    }

    public MainPage logInFromMainPage(String username, String password) {
        this.enterUsername(username);
        this.enterPassword(password);
        return new MainPage(driver);
    }
}
