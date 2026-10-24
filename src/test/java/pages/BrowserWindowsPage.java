package pages;
 
import java.time.Duration;
 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
 
public class BrowserWindowsPage {
 
    // WebDriver and wait
    private WebDriver driver;
    private WebDriverWait wait;
 
    // Parent tab handle
    private String parentTab;
 
    // Browser Windows locators
    private By browserWindowsLink = By.xpath("//a[normalize-space()='Browser Windows']");
 
    private By newTabButton = By.xpath("//button[normalize-space()='New Tab']");
 
    private By newWindowButton = By.xpath("//button[normalize-space()='New Window']");
 
    // Alerts locators
    private By alertsLink = By.xpath("//a[normalize-space()='Alerts']");
 
    private By alertButton = By.xpath("//button[normalize-space()='Alert']");
 
    private By delayedAlertButton = By.xpath("//*[contains(normalize-space(.), 'alert will appear after 5 seconds')]/following::button[1]");
 
    // Frames and tutorial locators
    private By framesLink = By.xpath("//a[normalize-space()='Frames']");
 
    private By seleniumTutorialLink = By.xpath("//a[normalize-space()='Selenium Tutorial']");

    
private final By confirmButton = By.xpath(
    "//label[normalize-space()='On button click, confirm box will appear']/following-sibling::button"
);

private final By promptButton = By.xpath(
    "//button[@onclick='myPromp()']"
);

 
 
    // Constructor
    public BrowserWindowsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
                driver, Duration.ofSeconds(10)
        );
    }
 
 
    // Navigation
    public void navigateToModalDialogsPageTutorialURL() {
        driver.get(
            "https://www.tutorialspoint.com/selenium/practice/modal-dialogs.php"
        );
    }
 
 
    // Browser Windows actions
    public void clickBrowserWindows() {
        wait.until(
            ExpectedConditions.elementToBeClickable(browserWindowsLink)
        ).click();
 
        wait.until(
            ExpectedConditions.urlContains("browser-windows.php")
        );
 
        parentTab = driver.getWindowHandle();
    }
 
    public void clickNewTab() {
        wait.until(
            ExpectedConditions.elementToBeClickable(newTabButton)
        ).click();
    }
 
    public void clickNewWindow() {
        wait.until(
            ExpectedConditions.elementToBeClickable(newWindowButton)
        ).click();
    }
 
    public String getParentTab() {
        return parentTab;
    }
 
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
 
 
    // Alerts actions
    public void clickAlerts() {
        wait.until(
            ExpectedConditions.elementToBeClickable(alertsLink)
        ).click();
 
        wait.until(
            ExpectedConditions.urlContains("alerts.php")
        );
    }
 
    public void clickAlertButton() {
        wait.until(
            ExpectedConditions.elementToBeClickable(alertButton)
        ).click();
    }
 
    public void clickDelayedAlertButton() {
        wait.until(
            ExpectedConditions.elementToBeClickable(delayedAlertButton)
        ).click();
    }
 
 
    // Frames and Selenium Tutorial actions
    public void clickFrames() {
        wait.until(
            ExpectedConditions.elementToBeClickable(framesLink)
        ).click();
 
        wait.until(
            ExpectedConditions.urlContains("frames.php")
        );
    }
 
    public void switchToFrame1() {
        wait.until(
            ExpectedConditions.frameToBeAvailableAndSwitchToIt(0)
        );
    }
 
    public void clickSeleniumTutorial() {
        wait.until(
            ExpectedConditions.elementToBeClickable(seleniumTutorialLink)
        ).click();
    }


public void clickConfirmButton() {
    wait.until(ExpectedConditions.elementToBeClickable(confirmButton)).click();
}

public void clickPromptButton() {
    wait.until(ExpectedConditions.elementToBeClickable(promptButton)).click();
}

public String getAlertText() {
    return driver.switchTo().alert().getText();
}

public void acceptAlert() {
    driver.switchTo().alert().accept();
}

public void dismissAlert() {
    driver.switchTo().alert().dismiss();
}

public void enterPromptText(String text) {
    driver.switchTo().alert().sendKeys(text);
}

public void acceptPrompt() {
    driver.switchTo().alert().accept();
}


}


 