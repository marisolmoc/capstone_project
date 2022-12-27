package Steps;

import Pages.ProductPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ProductSteps extends BaseSteps {

    public ProductSteps(WebDriver webDriver) {
        super(webDriver);
    }
    ProductPage productPage = PageFactory.initElements(_webDriver, ProductPage.class);

    public String getProductName() {
        return productPage.getProductName().getText();
    }

    public WebElement getProductImage() {
        return productPage.getProductImage();
    }

    public WebElement getProductPrice() {
        return productPage.getProductPrice();
    }

    public WebElement getProductDescription() {
        return productPage.getProductDescription();
    }

    public WebElement getProductCartButton() {
        return productPage.getProductCartButton();
    }
}
