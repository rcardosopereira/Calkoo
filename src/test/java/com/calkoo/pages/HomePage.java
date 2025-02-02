package com.calkoo.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.NoSuchElementException;

public class HomePage {
    private final WebDriver driver;
    private final By netAmountInput = By.id("NetPrice");
    private final By grossAmountResult = By.id("Price");
    private final By vatAmountResult = By.id("VATsum");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectCountry(String country) {
        WebElement countryDropdown = driver.findElement(By.name("Country"));
        Select selectCountry = new Select(countryDropdown);
        selectCountry.selectByVisibleText(country);
    }

    public void selectVatRate(String vatRate) {
        String vatRateId = "VAT_" + vatRate.replace("%", "");
        WebElement radioButton = driver.findElement(By.id(vatRateId));
        if (!radioButton.isSelected()) {
            radioButton.click();
        }
    }

    public String getSelectedCountry(WebDriver driver) {
        Select countrySelect = new Select(driver.findElement(By.name("Country")));
        WebElement selectedOption = countrySelect.getFirstSelectedOption();
        return selectedOption.getText();
    }

    public String getSelectedVatRate() {
        WebElement selectedVatRateElement = driver.findElement(By.cssSelector("input[name='VAT']:checked"));
        return selectedVatRateElement.getAttribute("value") + "%";
    }

    public boolean isVATRateDisplayedCorrectly(String vatRate) {
        String vatRateId = "VAT_" + vatRate.replace("%", "");
        WebElement vatRateElement = driver.findElement(By.id(vatRateId));
        return vatRateElement.isSelected();
    }

    public void enterNetAmount(String amount) {
        WebElement netAmountElement = driver.findElement(netAmountInput);
        netAmountElement.clear();
        netAmountElement.sendKeys(amount);
    }

    public double isGrossAmountCorrect() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement priceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(grossAmountResult));
        String priceValue = priceElement.getAttribute("value");

        if (priceValue == null || priceValue.isEmpty()) {
            return -1; // Returns a default value when the field is empty
        }

        try {
            return Double.parseDouble(priceValue);
        } catch (NumberFormatException e) {
            return -1; // Returns a default value when unable to convert to double
        }
    }

    public boolean isVATAmountCorrect(String expectedAmount) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement vatAmountElement = wait.until(ExpectedConditions.visibilityOfElementLocated(vatAmountResult));
        String vatAmountValue = vatAmountElement.getAttribute("value");
        double expectedValue = Double.parseDouble(expectedAmount);
        double actualValue = Double.parseDouble(vatAmountValue);
        Assert.assertEquals(expectedValue, actualValue, 0.01);
        return true;
    }

    public String getErrorMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errorMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(.,'Negative values are invalid for a pie chart.×')]"))); // Locate error message
        return errorMessageElement.getText();
    }
}