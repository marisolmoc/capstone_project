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

    @Test(description = "SR-12111: Test Categories are displayed", priority = 1)
    public void TestCategoriesDisplayed() {
        WebElement catListGroup = driver.findElement(By.xpath("//a[contains(text(), 'CATEGORIES')]"));
        String url = driver.getCurrentUrl();
        String leftMenu = "CATEGORIES";
        CustomAssertions.isElementDisplayed(catListGroup.isDisplayed(), url, leftMenu);
    }

    @Test(description = "SR-12111: Test first category", dependsOnMethods = {"TestCategoriesDisplayed"}, priority = 2)
    private void TestFirstCategory() {
        WebElement cat1 = driver.findElement(By.xpath("//a[contains(text(), 'Phones')]"));
        String cat1Name = cat1.getText();
        CustomAssertions.isTextEqual("Phones", cat1Name);
    }

    @Test(description = "SR-12111: Test second category", dependsOnMethods = {"TestCategoriesDisplayed"}, priority = 3)
    private void TestSecondCategory(){
        WebElement cat2 = driver.findElement(By.xpath("//a[contains(text(), 'Laptops')]"));
        String cat2Name = cat2.getText();
        CustomAssertions.isTextEqual("Laptops", cat2Name);
    }

    @Test(description = "SR-12111: Test third category", dependsOnMethods = {"TestCategoriesDisplayed"}, priority = 4)
    private void TestThirdCategory(){
        WebElement cat3 = driver.findElement(By.xpath( "//a[contains(text(), 'Monitors')]"));
        String cat3Name = cat3.getText();
        CustomAssertions.isTextEqual("Monitors", cat3Name);
    }

    @Test(description = "SR-12120: Test Products Phones Catalog", priority = 5)
    public void TestPhoneProductPage() {
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

    @Test(description = "SR-12120: Test product image is present", dependsOnMethods = {"TestPhoneProductPage"})
    public void TestProductImage(){
        WebElement productImage = productSteps.getProductImage();
        String url = driver.getCurrentUrl();
        CustomAssertions.isElementDisplayed(productImage.isDisplayed(), url, "product Image");
    }

    @Test(description = "SR-12120: Test product price is present", dependsOnMethods = {"TestPhoneProductPage"})
    public void TestProductPrice(){
        WebElement productPrice = productSteps.getProductPrice();
        String url = driver.getCurrentUrl();
        CustomAssertions.isElementDisplayed(productPrice.isDisplayed(), url, "product price");
    }

    @Test(description = "SR-12120: Test product description is present", dependsOnMethods = {"TestPhoneProductPage"})
    public void TestProductDesc(){
        WebElement productDesc = productSteps.getProductDescription();
        String url = driver.getCurrentUrl();
        CustomAssertions.isElementDisplayed(productDesc.isDisplayed(), url, "product description");
    }

    @Test(description = "SR-12120: Test product description is present", dependsOnMethods = {"TestPhoneProductPage"})
    public void TestProductCartButton(){
        WebElement productCartButton = productSteps.getProductCartButton();
        String url = driver.getCurrentUrl();
        CustomAssertions.isElementDisplayed(productCartButton.isDisplayed(), url, "product cart button");
    }

    @Test(description = "SR-12121: Test add to cart browser alert", dependsOnMethods = {"TestPhoneProductPage"})
    public void TestAddToCartAlert() {
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
