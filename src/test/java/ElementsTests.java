import Steps.CategoriesSteps;
import Steps.ProductSteps;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class ElementsTests extends BaseTest {
    CategoriesSteps categoriesSteps =  new CategoriesSteps(driver);
    ProductSteps productSteps = new ProductSteps(driver);

    @Test(description = "Testing that the URL is as expected")
    public void TestURLIsCorrect() {
        String expectedURL = "https://www.demoblaze.com/";
        String actualURL = driver.getCurrentUrl();
        CustomAssertions.isUrlValid(expectedURL, actualURL);
    }

    @Test(description = "SR-12111: Test Categories are displayed")
    public void TestCategoriesDisplayed() {
        WebElement catListGroup = driver.findElement(By.xpath("//a[contains(text(), 'CATEGORIES')]"));
        String url = driver.getCurrentUrl();
        String leftMenu = "CATEGORIES";
        CustomAssertions.isElementDisplayed(catListGroup.isDisplayed(), url, leftMenu);
    }

    @Test(description = "SR-12120: Test Products Phones Catalog", priority = 1)
    public void testPhoneProductPage() {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(2))
                .pollingEvery(Duration.ofMillis(2000))
                .ignoring(NoSuchElementException.class);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='cat']")));
        categoriesSteps.selectCategories("Phones");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='tbodyid']/div[1]/div/a")));

        driver.findElement(By.xpath("//*[@id='tbodyid']/div[1]/div/a")).click();

        WebElement productPhone = new WebDriverWait(driver, Duration.ofSeconds(3)).until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='item active']/img"))
        );
        productPhone.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='item active']/img")));

        String actualProductName = productSteps.getProductName();
        String expectedProductName = "Samsung galaxy s6";

        CustomAssertions.isTextEqual(expectedProductName, actualProductName);
    }

    @Test(description = "SR-12120: Test product image is present", dependsOnMethods = {"testPhoneProductPage"},
            priority = 2)
    public void testProductImage(){
        WebElement productImage = productSteps.getProductImage();
        String url = driver.getCurrentUrl();
        CustomAssertions.isElementDisplayed(productImage.isDisplayed(), url, "product Image");
    }

    @Test(description = "SR-12120: Test product price is present", dependsOnMethods = {"testPhoneProductPage"},
            priority = 3)
    public void testProductPrice(){
        WebElement productPrice = productSteps.getProductPrice();
        String url = driver.getCurrentUrl();
        CustomAssertions.isElementDisplayed(productPrice.isDisplayed(), url, "product price");
    }

    @Test(description = "SR-12120: Test product description is present", dependsOnMethods = {"testPhoneProductPage"},
            priority = 4)
    public void testProductDesc(){
        WebElement productDesc = productSteps.getProductDescription();
        String url = driver.getCurrentUrl();
        CustomAssertions.isElementDisplayed(productDesc.isDisplayed(), url, "product description");
    }

    @Test(description = "SR-12120: Test product description is present", dependsOnMethods = {"testPhoneProductPage"},
            priority = 5)
    public void testProductCartButton(){
        WebElement productCartButton = productSteps.getProductCartButton();
        String url = driver.getCurrentUrl();
        CustomAssertions.isElementDisplayed(productCartButton.isDisplayed(), url, "product cart button");
    }

    @Test(description = "SR-12121: Test add to cart browser alert", dependsOnMethods = {"testPhoneProductPage"},
            priority = 6)
    public void testAddToCartAlert() {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(2000))
                .ignoring(NoSuchElementException.class);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(), 'Add to cart')]")));
        WebElement productCartButton = productSteps.getProductCartButton();
        productCartButton.click();

        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String alertMessage = alert.getText();
        CustomAssertions.isTitleValid("Product added", alertMessage);
        alert.accept();
    }
}
