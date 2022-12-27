package Steps;

import Pages.CategoriesPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CategoriesSteps extends BaseSteps {
    CategoriesPage categoriesPage = PageFactory.initElements(_webDriver, CategoriesPage.class);

    public CategoriesSteps(WebDriver webDriver) { super(webDriver); }

    public void selectCategories(String category) {
        switch (category){
            case "Phones":
                categoriesPage.getCatItemPhones().click();
                break;
            case "Laptops":
                categoriesPage.getCatItemLaptops().click();
                break;
            default:
                categoriesPage.getCatItemMonitors().click();
                break;

        }
    }
}
