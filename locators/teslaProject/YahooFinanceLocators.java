package teslaProject;

import org.openqa.selenium.By;

public class YahooFinanceLocators {
    // Home page locators
    public static final By SEARCH_BOX = By.id("ybar-sbq");
    public static final By SEARCH_BUTTON = By.id("header-desktop-search-button");
    
    // Autosuggest locators
    public static final By AUTOSUGGEST_LIST = By.xpath("(//ul[@role='listbox']/li/div/div/div[contains(@class,'modules-module_quoteCompanyName')])[1]");
    public static final By FIRST_AUTOSUGGEST_ITEM = By.xpath("//ul[@role='listbox']/li[1]");
    
    // Stock details page locators
    public static final By STOCK_PRICE = By.xpath("//fin-streamer[@data-field='regularMarketPrice']");
    public static final By PREVIOUS_CLOSE = By.xpath("//td[@data-test='PREV_CLOSE-value']");
    public static final By VOLUME = By.xpath("//td[@data-test='TD_VOLUME-value']");
    
    // Stock name on details page
    public static final By STOCK_NAME = By.xpath("//h1[@data-reactid='7']");
}