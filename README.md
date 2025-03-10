Yahoo Tesla Online test

## Test Scenarios

The test automation suite covers the following scenarios:

1. Search for Tesla (TSLA) on Yahoo Finance homepage
2. Verify that the autosuggest shows "Tesla" as the first result
3. Click on the first autosuggest result
4. Verify that the stock price is greater than $200
5. Capture and log additional stock data (Previous Close and Volume)

## Prerequisites

- Java 11 or higher
- Maven 3.6 or higher
- Chrome browser installed

## Setup and Execution

1. Clone this repository:
   ```bash
   git clone https://github.com/yourusername/yahoofinance-test-automation.git
   cd yahoofinance-test-automation
   ```

2. Run the tests using Maven:
   ```bash
   mvn clean test
   ```

## Key Features

- **Page Object Model**: Separation of test logic from page interactions
- **Explicit Locators**: All locators are maintained in a separate class
- **Base Test Class**: Common functionality like implicit waits and WebDriver setup
- **Log4j Integration**: Comprehensive logging of each test step
- **Assertions**: TestNG assertions to validate test conditions
- **Error Handling**: Test for invalid stock symbols and edge cases
- **Configurable**: Easy to extend for testing other stock symbols

## Test Reports

Test reports are generated in the `target/surefire-reports` directory after test execution.

## Logs

Logs are generated in the `logs` directory with detailed information about each test step.
