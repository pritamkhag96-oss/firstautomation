
package pages;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewTabPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locator for text on the new tab
    private By sampleNewTabText =
            By.xpath("//*[contains(., 'Sample New Tab')]");

    // Constructor
    public NewTabPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
                driver, Duration.ofSeconds(10));
    }

    // Switch to the newly opened tab/window
    public void switchToNewTab(String parentWindow) {

        wait.until(d -> d.getWindowHandles().stream()
                .anyMatch(handle -> !handle.equals(parentWindow)));

        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(parentWindow)) {
                driver.switchTo().window(handle);
                return;
            }
        }
    }

    // Switch back to the parent tab/window
    public void switchToParentTab(String parentWindow) {
        driver.switchTo().window(parentWindow);
    }

    // Check whether the new tab text is visible
    public boolean isSampleNewTabVisible() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        sampleNewTabText))
                .isDisplayed();
    }

    // Get the current page title
    public String getPageTitle() {
        return driver.getTitle();
    }

    // Wait for an alert
    public void waitForAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
    }

    // Get alert text
    public String getAlertText() {
        Alert alert = driver.switchTo().alert();
        return alert.getText();
    }

    // Accept an alert
    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }
}
