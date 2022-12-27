import org.testng.Assert;

public class CustomAssertions extends Assert {
    public static void isElementDisplayed(Boolean isElementDisplayed, String url, String view){
        Assert.assertTrue(isElementDisplayed, "The element is not displayed " + "in " + url + "and in view " + view);
    }

    public static void isUrlValid(String expectedUrl, String currentUrl) {
        Assert.assertEquals(currentUrl, expectedUrl, "URL" + currentUrl + "is not as expected. Expected " + expectedUrl);
    }

    public static void isTitleValid(String expectedTitle, String actualTitle) {
        String errorMsg = "The title is not as expected. Expected: "+ expectedTitle + " Actual: " + actualTitle;
        Assert.assertEquals(actualTitle, expectedTitle, errorMsg);
    }

    public static void isTitleValidNegative (String expectedTitle, String actualTitle) {
        String errorMsg = "The title is not as expected. Expected: " + expectedTitle + " Actual: " + actualTitle;
        Assert.assertNotEquals(actualTitle, expectedTitle, errorMsg);
    }

    public static void isTextEqual (String expectedText, String currentText) {
        String errorMsg = "Text " + currentText + " is not as expected. Expected was: " + expectedText;
        Assert.assertEquals(currentText, expectedText, errorMsg);
    }
}
