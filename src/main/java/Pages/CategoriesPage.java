package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CategoriesPage extends BasePage {
    public CategoriesPage(WebDriver webDriver) { super(webDriver); }

    @FindBy(how = How.XPATH, using = "//a[contains(text(), 'Phones')]")
    private WebElement catItemPhones;

    @FindBy(how = How.XPATH, using = "//a[contains(text(), 'Laptops')]")
    private WebElement catItemLaptops;

    @FindBy(how = How.XPATH, using = "//a[contains(text(), 'Monitors')]")
    private WebElement catItemMonitors;

    public WebElement getCatItemPhones() {
        return catItemPhones;
    }

    public WebElement getCatItemLaptops() {
        return catItemLaptops;
    }

    public WebElement getCatItemMonitors() {
        return catItemMonitors;
    }
}
