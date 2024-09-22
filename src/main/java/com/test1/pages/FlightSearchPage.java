package com.test1.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FlightSearchPage {
    private final WebDriver driver;
    private WebDriverWait wait;

    private final By departureAirportField = By.xpath("/html/body/div[3]/div[4]/div/div/div[2]/div/div/div[2]/div[1]/div/section/div[2]/div[1]/div/div/form/div[2]/div[1]/div[1]/div[1]/div/div[1]/div[1]/div[1]/input");
    private final By destinationAirportField = By.xpath("/html/body/div[3]/div[4]/div/div/div[2]/div/div/div[2]/div[1]/div/section/div[2]/div[1]/div/div/form/div[2]/div[1]/div/div[3]/div/div[1]/div[1]/div[1]/input");
    private final By dateFieldsSelection = By.xpath("//*[@id=\"dcep-a33dbe5e6-838a-40c7-b46b-cfc71c8ce67c-flm-flight-flightQuery.flightSegments[0].travelDatetime\"]");
    private final By returnDateField = By.xpath("/html/body/div[6]/div/div/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/input");
    private final By departureDateField = By.xpath("/html/body/div[6]/div/div/div[2]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/input");
    private final By continueButton = By.xpath("/html/body/div[6]/div/div/div[2]/div[3]/div[2]/div[1]/button");
    private final By searchFlightsButton = By.xpath("/html/body/div[3]/div[4]/div/div/div[2]/div/div/div[2]/div[1]/div/section/div[2]/div[1]/div/div/form/div[2]/div[4]/button");
    private final By resultsContainer = By.xpath("/html/body/app/refx-app-layout/div/div[2]/refx-upsell/refx-basic-in-flow-layout/div/div[6]/div[4]/div/div/div/refx-upsell-premium-cont/refx-upsell-premium-pres/div");
    private final By resultsContainerHeader= By.xpath("/html/body/app/refx-app-layout/div/div[2]/refx-upsell/refx-basic-in-flow-layout/div/div[1]/div/div/refx-search-recap-cont/refx-flight-recap-pres/div[1]/div/div/div/refx-flight-recap-dates");

    public void acceptCookies(WebDriver driver) {
        driver.findElement(By.id("cm-acceptAll")).click();
    }

    public FlightSearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(100)); // 20-second wait for elements

    }

    public void enterDepartureAirport(String airportCode) {
        WebElement departureField = wait.until(ExpectedConditions.visibilityOfElementLocated(departureAirportField));
        String prefilledValue = departureField.getAttribute("DUBLIN");
        for (int i = 0; i < prefilledValue.length(); i++) {
            departureField.sendKeys(Keys.BACK_SPACE);
        }
        departureField.sendKeys(airportCode);
    }

    public void enterDestinationAirport(String airportCode) {
        WebElement destinationField = wait.until(ExpectedConditions.visibilityOfElementLocated(destinationAirportField));
        destinationField.clear();
        destinationField.sendKeys(airportCode);
    }

    public void enterDepartureDate(String date) {
        driver.findElement(dateFieldsSelection).click();
        WebElement dateField = wait.until(ExpectedConditions.visibilityOfElementLocated(departureDateField));
        dateField.clear();
        dateField.sendKeys(date);
    }

    public void enterReturnDate(String date) {
        WebElement returnField = wait.until(ExpectedConditions.visibilityOfElementLocated(returnDateField));
        returnField.clear();
        returnField.sendKeys(date);
    }

    public void clickContinue() {
        WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(continueButton));
        continueBtn.click();
    }

    public void clickSearchFlights() {
        WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(searchFlightsButton));
        searchBtn.click();
    }

    public boolean verifyResultsContainAirportCodes(String departureCode, String destinationCode) {
        WebElement results = wait.until(ExpectedConditions.visibilityOfElementLocated(resultsContainer));
        String resultsText = results.getText();
        return resultsText.contains(departureCode) && resultsText.contains(destinationCode);
    }

    public boolean verifyCorrectDatesDisplayed(String departureDate, String returnDate) {
        WebElement results = wait.until(ExpectedConditions.visibilityOfElementLocated(resultsContainerHeader));
        String resultsText = results.getText();
        return resultsText.contains(departureDate) && resultsText.contains(returnDate);
    }
}
