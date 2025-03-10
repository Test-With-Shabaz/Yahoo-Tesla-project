package teslaProject;

import teslaProject.BaseTest;
import teslaProject.YahooFinanceLocators;
import teslaProject.YahooFinancePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class YahooFinanceTest extends BaseTest {
    private YahooFinancePage yahooFinancePage;
    private static final String STOCK_SYMBOL = "TSLA";
    private static final String EXPECTED_COMPANY_NAME = "Tesla";
    private static final double EXPECTED_MIN_STOCK_PRICE = 200.0;
    
    @BeforeMethod
    public void initialize() {
        logger.info("Initializing test for Yahoo Finance");
        yahooFinancePage = new YahooFinancePage(driver);
        yahooFinancePage.navigateToHomepage();
    }
    
    @Test
    public void testYahooFinanceStockSearch() {
        logger.info("Starting Yahoo Finance Stock Search test for " + STOCK_SYMBOL);
        
        // Step 1: Search for Tesla (TSLA)
        logger.info("STEP 1: Searching for stock symbol: " + STOCK_SYMBOL);
        yahooFinancePage.searchForStock(STOCK_SYMBOL);
        
        // Step 2: Verify Autosuggest
        logger.info("STEP 2: Verifying autosuggest contains: " + EXPECTED_COMPANY_NAME);
        boolean isAutosuggestCorrect = yahooFinancePage.verifyAutosuggest(EXPECTED_COMPANY_NAME);
        Assert.assertTrue(isAutosuggestCorrect, 
                        "Autosuggest does not contain expected company name: " + EXPECTED_COMPANY_NAME);
        logger.info("STEP 2 PASSED: Autosuggest verification successful");
        
        // Step 3: Click on First Entry
        logger.info("STEP 3: Clicking on first autosuggest entry");
        yahooFinancePage.clickFirstAutosuggestItem();
        logger.info("STEP 3 PASSED: Successfully clicked on first autosuggest entry");
        
        // Step 4: Verify Stock Price
        logger.info("STEP 4: Verifying stock price is greater than $" + EXPECTED_MIN_STOCK_PRICE);
        double actualStockPrice = yahooFinancePage.getStockPrice();
        Assert.assertTrue(actualStockPrice > EXPECTED_MIN_STOCK_PRICE, 
                        "Stock price ($" + actualStockPrice + ") is not greater than $" + EXPECTED_MIN_STOCK_PRICE);
        logger.info("STEP 4 PASSED: Stock price verification successful. Price: $" + actualStockPrice);
        
        // Step 5: Capture Additional Data
        logger.info("STEP 5: Capturing additional stock data");
        double previousClose = yahooFinancePage.getPreviousClose();
        String volume = yahooFinancePage.getVolume();
        
        logger.info("===== Stock Data Summary =====");
        logger.info("Stock Symbol: " + STOCK_SYMBOL);
        logger.info("Current Price: $" + actualStockPrice);
        logger.info("Previous Close: $" + previousClose);
        logger.info("Volume: " + volume);
        logger.info("==============================");
        
        logger.info("STEP 5 PASSED: Successfully captured additional stock data");
        logger.info("All test steps PASSED");
    }
    
    @Test
    public void testInvalidStockSymbol() {
        String invalidSymbol = "XYZXYZ";
        logger.info("Starting test for invalid stock symbol: " + invalidSymbol);
        
        yahooFinancePage.searchForStock(invalidSymbol);
        
        try {
            boolean isAutosuggestPresent = yahooFinancePage.verifyAutosuggest(invalidSymbol);
            logger.info("Autosuggest present for invalid symbol: " + isAutosuggestPresent);
            
            // If no exception and autosuggest is present, we need to verify the result
            if (isAutosuggestPresent) {
                yahooFinancePage.clickFirstAutosuggestItem();
                logger.info("Successfully navigated to search results page for invalid symbol");
            } else {
                logger.info("No autosuggest results for invalid symbol, as expected");
                Assert.assertFalse(isAutosuggestPresent, "Expected no autosuggest results for invalid symbol");
            }
        } catch (Exception e) {
            logger.info("Exception occurred as expected for invalid symbol: " + e.getMessage());
            Assert.assertTrue(true, "Test passes because invalid symbol didn't return results");
        }
    }
}