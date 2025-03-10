package teslaProject;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected static final Logger logger = LogManager.getLogger(BaseTest.class);
    
    @BeforeClass
    public void setUp() {
        logger.info("Setting up WebDriver");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        logger.info("WebDriver setup completed");
    }
    
    @AfterClass
    public void tearDown() {
        logger.info("Tearing down WebDriver");
        if (driver != null) {
            driver.quit();
            logger.info("WebDriver closed successfully");
        }
    }
    
    /**
     * Wait for an element to be visible
     * @param element WebElement to wait for
     * @return WebElement after it's visible
     */
    protected WebElement waitForElementVisible(WebElement element) {
        logger.debug("Waiting for element to be visible");
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
    
    /**
     * Wait for an element to be clickable
     * @param element WebElement to wait for
     * @return WebElement after it's clickable
     */
    protected WebElement waitForElementClickable(WebElement element) {
        logger.debug("Waiting for element to be clickable");
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}