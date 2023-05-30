package com.calkoo.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private By vatRateRadio = By.id("VAT_19");
    private By netAmountInput = By.id("NetPrice");
    private By grossAmountResult = By.id("Price");
    private By vatAmountResult = By.id("VATsum");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectCountry(String country) {
        WebElement countryDropdown = driver.findElement(By.name("Country"));
        Select selectCountry = new Select(countryDropdown);
        selectCountry.selectByVisibleText(country);
    }

    public void selectVatRate(String vatRate) {
        WebElement radioButton = driver.findElement(By.id("VAT_23"));
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
        WebElement selectedVatRateElement = driver.findElement(By.id("VAT_23"));
        return selectedVatRateElement.getAttribute("value") + "\u0025";
    }

    public void selectVatRate2(String vatRate) {
        WebElement radioButton2 = driver.findElement(By.id("VAT_19"));
        if (!radioButton2.isSelected()) {
            radioButton2.click();
        }
    }

    public boolean isVATRateDisplayedCorrectly() {
        WebElement vatRateElement = driver.findElement(vatRateRadio);
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
}
