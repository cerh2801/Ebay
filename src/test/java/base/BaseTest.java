package base;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class BaseTest {
    protected WebDriver driver;

    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\cerh2\\Downloads\\chromedriver-win64 (1)\\chromedriver-win64\\chromeDriver.exe");
        ChromeOptions co = new ChromeOptions();
        co.setBinary("C:\\Users\\cerh2\\Downloads\\chrome-win64 (1)\\chrome-win64\\chrome.exe");
        driver = new ChromeDriver(co);
    }

//    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }

    public void screen() {

        driver.manage().window().maximize();
    }
}
