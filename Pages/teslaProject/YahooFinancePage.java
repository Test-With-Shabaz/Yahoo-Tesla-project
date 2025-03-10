package teslaProject;

import teslaProject.BaseTest;
import teslaProject.YahooFinanceLocators;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class YahooFinancePage extends BaseTest {
    private static final Logger logger = LogManager.getLogger(YahooFinancePage.class);
    private WebDriver driver;
    
    @FindBy(id = "yfin-usr-qry")
    private WebElement searchBox;
    
    @FindBy(id = "header-desktop-search-button")
    private WebElement searchButton;
    
    @FindBy(xpath = "//ul[@role='listbox']/li[1]")
    private WebElement firstAutosuggestItem;
    
    @FindBy(xpath = "//fin-streamer[@data-field='regularMarketPrice']")
    private WebElement stockPrice;
    
    @FindBy(xpath = "//td[@data-test='PREV_CLOSE-value']")
    private WebElement previousClose;
    
    @FindBy(xpath = "//td[@data-test='TD_VOLUME-value']")
    private WebElement volume;
    
    public YahooFinancePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    /**
     * Navigate to Yahoo Finance homepage
     */
    public void navigateToHomepage() {
        logger.info("Navigating to Yahoo Finance homepage");
        driver.get("https://finance.yahoo.com/");
        logger.info("Successfully navigated to Yahoo Finance homepage");
    }
    
    /**
     * Search for a stock symbol
     * @param symbol Stock symbol to search for
     */
    public void searchForStock(String symbol) {
        logger.info("Searching for stock symbol: " + symbol);
        waitForElementVisible(searchBox).clear();
        searchBox.sendKeys(symbol);
        logger.info("Entered stock symbol in search box");
    }
    
    /**
     * Check if autosuggest appears and contains the expected text
     * @param expectedText Text expected in the first autosuggest item
     * @return true if autosuggest contains expected text, false otherwise
     */
    public boolean verifyAutosuggest(String expectedText) {
        logger.info("Verifying autosuggest contains: " + expectedText);
        waitForElementVisible(firstAutosuggestItem);
        String actualText = firstAutosuggestItem.getText();
        boolean containsExpectedText = actualText.contains(expectedText);
        logger.info("Autosuggest verification result: " + containsExpectedText + 
                    " (actual text: '" + actualText + "')");
        return containsExpectedText;
    }
    
    /**
     * Click on the first autosuggest item
     */
    public void clickFirstAutosuggestItem() {
        logger.info("Clicking on first autosuggest item");
        waitForElementClickable(firstAutosuggestItem).click();
        logger.info("Clicked on first autosuggest item");
    }
    
    /**
     * Get the current stock price
     * @return The stock price as a double
     */
    public double getStockPrice() {
        logger.info("Getting stock price");
        waitForElementVisible(stockPrice);
        String priceText = stockPrice.getText().replace(",", "");
        double price = Double.parseDouble(priceText);
        logger.info("Current stock price: " + price);
        return price;
    }
    
    /**
     * Get the previous close price
     * @return The previous close price as a double
     */
    public double getPreviousClose() {
        logger.info("Getting previous close value");
        waitForElementVisible(previousClose);
        String closeText = previousClose.getText().replace(",", "");
        double close = Double.parseDouble(closeText);
        logger.info("Previous close: " + close);
        return close;
    }
    
    /**
     * Get the trading volume
     * @return The volume as a String (could have M/B suffix)
     */
    public String getVolume() {
        logger.info("Getting volume value");
        waitForElementVisible(volume);
        String volumeText = volume.getText();
        logger.info("Volume: " + volumeText);
        return volumeText;
    }
}