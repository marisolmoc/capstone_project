import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;

public class BaseTest {

    public WebDriver driver = get_driver();

    private WebDriver get_driver() {
        System.setProperty("webDriver.chrome.driver", "/Users/mm0922/projects/Capstone_Project/chromedriver");
        this.driver = new ChromeDriver();
        this.driver.get("https://www.demoblaze.com/");
        return driver;
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {driver.quit();}
}
