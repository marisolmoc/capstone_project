package Pages;

import org.openqa.selenium.WebDriver;

public class BasePage {

    WebDriver _webDriver;

    public BasePage (WebDriver webDriver) {
        this._webDriver = webDriver;
    }
}
