package com.test1.steps;
import com.test1.pages.FlightSearchPage;
import com.test1.pages.TestBase;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FlightSearchSteps extends FlightSearchPage {
    private WebDriverWait wait;
    WebDriver driver;
    FlightSearchPage flightSearchPage;
    String departureDate;
    String returnDate;
    public FlightSearchSteps() {
        super(TestBase.getDriver());
        this.driver = TestBase.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.flightSearchPage = new FlightSearchPage(driver);
    }
    @Given("I navigate to the Lufthansa homepage")
    public void navigateToHomepage() {
        driver.get("https://www.lufthansa.com/ie/en/homepage");
        flightSearchPage.acceptCookies(driver);
        wait.until(ExpectedConditions.titleContains("Lufthansa"));
        flightSearchPage = new FlightSearchPage(driver);
    }

    @When("I enter departure airport {string} and destination airport {string}")
    public void enterAirports(String departureAirport, String destinationAirport) {
        flightSearchPage.enterDepartureAirport(departureAirport);
        flightSearchPage.enterDestinationAirport(destinationAirport);
    }

    @When("I select departure day one week after current date and return date two days later")
    public void selectDates() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate today = LocalDate.now();
        departureDate = today.plusWeeks(1).format(formatter);
        returnDate = today.plusWeeks(1).plusDays(2).format(formatter);
        flightSearchPage.enterDepartureDate(departureDate);
        flightSearchPage.enterReturnDate(returnDate);
    }

    @When("I click the Continue button")
    public void clickContinue() {
        flightSearchPage.clickContinue();
    }

    @When("I click the Search Flights button")
    public void clickSearchFlights() {
        flightSearchPage.clickSearchFlights();
    }

    @Then("I verify the results contain departure airport {string} and destination airport {string}")
    public void verifyResultsContainAirports(String departureCode, String destinationCode) {
        boolean resultsCorrect = flightSearchPage.verifyResultsContainAirportCodes(departureCode, destinationCode);
        assert(resultsCorrect);
    }

    @Then("I verify the correct departure and return dates are displayed")
    public void verifyCorrectDatesDisplayed() {
        boolean datesCorrect = flightSearchPage.verifyCorrectDatesDisplayed(departureDate, returnDate);
        assert(datesCorrect);
    }
    @After
    public void tearDown() {
        TestBase.quitDriver();
    }
}