package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ProductPage extends BasePage{

    public ProductPage(WebDriver webDriver) {
        super(webDriver);
    }
    @FindBy(how = How.XPATH, using = "//div[@class='item active']/img")
    private WebElement productImage;

    @FindBy(how = How.XPATH, using = "//div[@id='tbodyid']/h2")
    private WebElement productName;

    @FindBy(how = How.XPATH, using = "//div[@id='tbodyid']/h3")
    private WebElement productPrice;

    @FindBy(how = How.XPATH, using = "//div[@id='myTabContent']")
    private WebElement productDescription;

    @FindBy(how = How.XPATH, using = "//a[contains(text(), 'Add to cart')]")
    private WebElement productCartButton;

    public WebElement getProductImage() { return productImage; }

    public WebElement getProductName() { return productName; }

    public WebElement getProductPrice() { return productPrice; }

    public WebElement getProductDescription() { return productDescription; }

    public WebElement getProductCartButton() { return  productCartButton; }

}