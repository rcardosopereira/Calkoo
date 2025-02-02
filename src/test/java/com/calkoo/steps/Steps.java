package com.calkoo.steps;

import com.calkoo.pages.HomePage;
import com.calkoo.utils.ConfigManager;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public class Steps {
    private static final Logger logger = LogManager.getLogger(Steps.class);
    private WebDriver driver;
    private HomePage homePage;

    @Given("I am on the VAT Calculator page")
    public void i_am_on_the_VAT_Calculator_page() {
        String browser = ConfigManager.getProperty("browser");
        boolean headless = Boolean.parseBoolean(ConfigManager.getProperty("headless"));

        // Initialize WebDriver based on the browser type
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            if (headless) {
                options.addArguments("--headless");
            }
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            if (headless) {
                options.addArguments("--headless");
            }
            driver = new FirefoxDriver(options);
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        // Initialize HomePage and navigate to the VAT Calculator page
        homePage = new HomePage(driver);
        driver.manage().window().maximize();
        driver.get(ConfigManager.getProperty("url"));

        // Handle cookie consent
        handleCookieConsent();

    }

    private void handleCookieConsent() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement consentButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".fc-button.fc-cta-consent.fc-primary-button")));
            consentButton.click();
            logger.info("Clicked the 'Consent' button.");

            WebElement acceptButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".cc-btn.cc-dismiss")));
            acceptButton.click();
            logger.info("Clicked the 'I Accept!' button.");
        } catch (Exception e) {
            logger.error("Cookie consent button not found or not clickable: " + e.getMessage());
        }
    }

    @When("I select a country {string}")
    public void i_select_a_country(String country) {
        homePage.selectCountry(country);
        logger.info("Selected country: " + country);
    }

    @When("I select a VAT rate {string}")
    public void i_select_a_VAT_rate(String vatRate) {
        homePage.selectVatRate(vatRate);
        logger.info("Selected VAT rate: " + vatRate);
    }

    @When("I select other VAT rate {string}")
    public void i_select_other_VAT_rate(String vatRate) {
        homePage.selectVatRate(vatRate);
        logger.info("Selected other VAT rate: " + vatRate);
    }

    @Then("the selected country should be {string}")
    public void the_selected_country_should_be(String expectedCountry) {
        String selectedCountry = homePage.getSelectedCountry(driver);
        Assert.assertEquals(expectedCountry, selectedCountry);
        logger.info("Verified selected country: " + selectedCountry);
    }

    @Then("the selected VAT rate should be {string}")
    public void the_selected_VAT_rate_should_be(String expectedVatRate) {
        String selectedVatRate = homePage.getSelectedVatRate();
        Assert.assertEquals(expectedVatRate, selectedVatRate);
        logger.info("Verified selected VAT rate: " + selectedVatRate);
    }

    @Then("the selected VAT rate should be displayed correctly in the calculator")
    public void the_selected_VAT_rate_should_be_displayed_correctly_in_the_calculator() {
        String selectedVatRate = homePage.getSelectedVatRate(); // Get the selected VAT rate
        Assert.assertTrue(homePage.isVATRateDisplayedCorrectly(selectedVatRate)); // Pass the VAT rate as an argument
        logger.info("Verified VAT rate is displayed correctly.");
    }

    @When("I enter the Net amount {string}")
    public void i_enter_the_Net_amount(String amount) {
        homePage.enterNetAmount(amount);
        logger.info("Entered Net amount: " + amount);
    }

    @Then("the calculated Gross amount should be {string}")
    public void the_calculated_Gross_amount_should_be(String expectedValue) {
        double actualValue = homePage.isGrossAmountCorrect();
        logger.info("Expected Gross amount: " + expectedValue);
        logger.info("Actual Gross amount: " + actualValue);
        Assert.assertEquals(Double.parseDouble(expectedValue), actualValue, 0.01);
    }

    @Then("the calculated VAT amount should be {string}")
    public void the_calculated_VAT_amount_should_be(String expectedAmount) {
        Assert.assertTrue(homePage.isVATAmountCorrect(expectedAmount));
        logger.info("Verified VAT amount: " + expectedAmount);
    }

    @Then("I should see an error message {string}")
    public void i_should_see_an_error_message(String expectedErrorMessage) {
        String actualErrorMessage = homePage.getErrorMessage();
        Assert.assertEquals(expectedErrorMessage, actualErrorMessage);
        logger.info("Verified error message: " + actualErrorMessage);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            logger.info("Browser closed.");
        }
    }
}