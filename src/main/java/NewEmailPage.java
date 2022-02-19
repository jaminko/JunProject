import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class NewEmailPage {
    private WebDriver driver;

    public NewEmailPage(WebDriver driver) {
        this.driver = driver;
    }

    private By recipientField = By.xpath("//*[@name='to']");
    private By themeField = By.xpath("//*[@name='subject']");
    private By bodyField = By.xpath("//*[@name='body' and contains (@id, 'text')]");
    private By sendButton = By.xpath("//*[@name='send']");
    private By saveInDraftsButton = By.xpath("//*[@name='save_in_drafts' and contains (@value, 'Зберегти чернетку')]");
    private By CancelButton = By.xpath("//*[@value='Скасувати']");

    public NewEmailPage fillRecipientField (String email) {
        driver.findElement(recipientField).sendKeys(email);
        return this;
    }

    public NewEmailPage fillThemeField (String theme) {
        driver.findElement(themeField).sendKeys(theme);
        return this;
    }

    public NewEmailPage fillBodyField (String body) {
        driver.findElement(bodyField).sendKeys(body);
        return this;
    }

    public NewEmailPage clickOnSendButton () {
        WebElement element01 = driver.findElement(sendButton);
        Actions builder1 = new Actions(driver);
        builder1.doubleClick(element01).sendKeys(Keys.ENTER).sendKeys(Keys.ENTER).build().perform();
        return this;
    }

    public NewEmailPage clickOnSaveInDraftsButton () {
        driver.findElement(saveInDraftsButton).click();
        return this;
    }

    public NewEmailPage clickOnCancelButton () {
        driver.findElement(CancelButton).click();
        return this;
    }
}
