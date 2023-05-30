package com.calkoo.steps;

import com.calkoo.pages.HomePage;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Steps {
    private WebDriver driver;
    private HomePage homePage;

    @Given("I am on the VAT Calculator page")
    public void i_am_on_the_VAT_Calculator_page() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/driver/chromedriver.exe");
        driver = new ChromeDriver();
        homePage = new HomePage(driver);
        driver.get("https://www.calkoo.com/en/vat-calculator");
    }

    @When("I select a country {string}")
    public void i_select_a_country(String country) {
        homePage.selectCountry(country);
    }

    @When("I select a VAT rate {string}")
    public void i_select_a_VAT_rate(String vatRate) {
        homePage.selectVatRate(vatRate);
    }

    @When("I select other VAT rate {string}")
    public void i_select_other_VAT_rate(String vatRate) {
        homePage.selectVatRate2(vatRate);
    }

    @Then("the selected country should be {string}")
    public void the_selected_country_should_be(String expectedCountry) {
        String selectedCountry = homePage.getSelectedCountry(driver);
        Assert.assertEquals(expectedCountry, selectedCountry);
    }

    @Then("the selected VAT rate should be {string}")
    public void the_selected_VAT_rate_should_be(String expectedVatRate) {
        String selectedVatRate = homePage.getSelectedVatRate();
        Assert.assertEquals(expectedVatRate, selectedVatRate);
    }

    @Then("the selected VAT rate should be displayed correctly in the calculator")
    public void the_selected_VAT_rate_should_be_displayed_correctly_in_the_calculator() {
        Assert.assertTrue(homePage.isVATRateDisplayedCorrectly());
    }

    @When("I enter the Net amount {string}")
    public void i_enter_the_Net_amount(String amount) {
        homePage.enterNetAmount(amount);
    }

    @Then("the calculated Gross amount should be {string}")
    public void the_calculated_Gross_amount_should_be(String expectedValue) {
        double actualValue = homePage.isGrossAmountCorrect();
        System.out.println("Expected Value: " + expectedValue);
        System.out.println("Actual Value: " + actualValue);
        Assert.assertEquals(Double.parseDouble(expectedValue), actualValue, 0.01);
    }

    @Then("the calculated VAT amount should be {string}")
    public void the_calculated_VAT_amount_should_be(String expectedAmount) {
        Assert.assertTrue(homePage.isVATAmountCorrect(expectedAmount));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
