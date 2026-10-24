
package Test;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Assertion.Assertion;
import pages.BrowserWindowsPage;
import pages.NewTabPage;
public class BrowserWindowsTest {

    WebDriver driver;
    BrowserWindowsPage browserPage;
    NewTabPage newTabPage;
    Assertion assertions;

    String parentWindow;

    // Open Chrome only once
    @BeforeClass
    public void setUp() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        parentWindow = driver.getWindowHandle();

        browserPage = new BrowserWindowsPage(driver);
        newTabPage = new NewTabPage(driver);
        assertions = new Assertion();

        browserPage.navigateToModalDialogsPageTutorialURL();
    }

    // Reset browser before every test
    @BeforeMethod
    public void resetBrowser() {

        // Return to the original window
        driver.switchTo().window(parentWindow);

        // Close any extra tabs or windows
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(parentWindow)) {
                driver.switchTo().window(handle);
                driver.close();
            }
        }

        // Return to the original window and default frame
        driver.switchTo().window(parentWindow);
        driver.switchTo().defaultContent();

        // Open the starting page
        browserPage.navigateToModalDialogsPageTutorialURL();
    }
        @org.testng.annotations.AfterMethod
        public void pauseAfterTest() throws InterruptedException {
        Thread.sleep(3000);
}
    // Close Chrome only once
    @AfterClass(alwaysRun = true)
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }

    // Test 1: New tab and parent tab
    @Test(priority = 1, groups = {"windows"})
    public void verifyNewTabAndParentTab() {

        browserPage.clickBrowserWindows();

        String parentTab = driver.getWindowHandle();

        browserPage.clickNewTab();
        newTabPage.switchToNewTab(parentTab);

        assertions.verifySampleNewTab(
                newTabPage.isSampleNewTabVisible());

        newTabPage.switchToParentTab(parentTab);

        assertions.verifyParentPageUrl(
                browserPage.getCurrentUrl());
    }

    // Test 2: New window and parent window
    @Test(priority = 2, groups = {"windows"})
    public void verifyNewWindowAndParentWindow() {

        browserPage.clickBrowserWindows();

        String parentTab = driver.getWindowHandle();

        browserPage.clickNewWindow();
        newTabPage.switchToNewTab(parentTab);

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.titleIs(
                        "Selenium Practice - Web Tables"));

        assertions.verifyNewWindowTitle(
                newTabPage.getPageTitle());

        newTabPage.switchToParentTab(parentTab);

        assertions.verifyParentPageUrl(
                browserPage.getCurrentUrl());
    }

    // Test 3: Simple alert
    @Test(priority = 3, groups = {"alerts"})
    public void verifyAlertTextAndAcceptAlert() {

        browserPage.clickAlerts();
        browserPage.clickAlertButton();

        newTabPage.waitForAlert();

        assertions.verifyAlertText(
                newTabPage.getAlertText());

        newTabPage.acceptAlert();
    }

    // Test 4: Delayed alert
    @Test(priority = 4, groups = {"alerts"})
    public void verifyDelayedAlertTextAndAcceptAlert() {

        browserPage.clickAlerts();
        browserPage.clickDelayedAlertButton();

        newTabPage.waitForAlert();

        assertions.verifyDelayedAlertText(
                newTabPage.getAlertText());

        newTabPage.acceptAlert();
    }

    // Test 5: Selenium tutorial tab inside frame
    @Test(priority = 5, groups = {"frames"})
    public void verifySeleniumTutorialTab() {

        browserPage.clickFrames();
        browserPage.switchToFrame1();

        String parentTab = driver.getWindowHandle();

        browserPage.clickSeleniumTutorial();

        newTabPage.switchToNewTab(parentTab);

        // Close the newly opened tab
        driver.close();

        // Return to the original tab
        newTabPage.switchToParentTab(parentTab);

        // Exit the frame
        driver.switchTo().defaultContent();
    }

    // Test 6: Confirm alert
    @Test(priority = 6, groups = {"alerts"})
    public void verifyConfirmBoxAccept() {

        browserPage.clickAlerts();
        browserPage.clickConfirmButton();

        newTabPage.waitForAlert();

        Assert.assertEquals(
                newTabPage.getAlertText(),
                "Press a button!");

        newTabPage.acceptAlert();
    }

    // Test 7: Prompt alert
    @Test(priority = 7, groups = {"alerts"})
    public void verifyPromptBoxEnterText() {

        browserPage.clickAlerts();
        browserPage.clickPromptButton();

        newTabPage.waitForAlert();

        Assert.assertEquals(
                newTabPage.getAlertText(),
                "What is your name?");

        browserPage.enterPromptText("Hello Selenium");
        browserPage.acceptPrompt();
    }
}
