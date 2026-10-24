package Assertion;
 
import org.testng.Assert;
 
public class Assertion {
 
    // TC01 - Verify Sample New Tab is visible
    public void verifySampleNewTab(boolean isVisible) {
        Assert.assertTrue(
            isVisible,
            "Sample New Tab should be visible"
        );
    }
 
    // TC01 and TC02 - Verify parent tab/window URL
    public void verifyParentPageUrl(String actualUrl) {
        Assert.assertTrue(
            actualUrl.contains("browser-windows.php"),
            "Should return to the parent tab/window"
        );
    }
 
    // TC02 - Verify new window title
    public void verifyNewWindowTitle(String actualTitle) {
        Assert.assertEquals(
            actualTitle,
            "Selenium Practice - Web Tables",
            "New Window should display the expected page title"
        );
    }
 
    // TC03 - Verify alert text
    public void verifyAlertText(String actualText) {
        Assert.assertEquals(
            actualText,
            "Hello world!",
            "Alert text should match"
        );
    }
 
    // TC04 - Verify delayed alert text
    public void verifyDelayedAlertText(String actualText) {
        Assert.assertEquals(
            actualText,
            "Hello just appeared",
            "Delayed alert text should match expected"
        );
    }
}
 
 