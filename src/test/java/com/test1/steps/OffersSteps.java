package com.test1.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.test1.pages.TestBase;
import com.test1.pages.OffersPage;

import java.time.Duration;

public class OffersSteps {

    private WebDriver driver;
    private WebDriverWait wait;
    private OffersPage offersPage;

    // Constructor
    public OffersSteps() {
        this.driver = TestBase.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.offersPage = new OffersPage(driver);
    }

    @Given("I navigate to the Lufthansa offers page")
    public void iNavigateToTheLufthansaOffersPage() {
        System.out.println("Navigating to the Lufthansa offers page...");
        driver.get("https://www.lufthansa.com/ie/en/homepage");
        offersPage.acceptCookies();
        wait.until(ExpectedConditions.titleContains("Lufthansa"));
    }

    @When("I click on Book & Prepare and select Offers & Destinations")
    public void clickOnBookAndPrepareThenOffersAndDestinations() {
        offersPage.clickOnBookAndPrepareThenOffersAndDestinations();
    }

    @Then("I change the value of the dropdown from {string} to {string}")
    public void iChangeTheValueOfTheDropdownFromPriceToAlphabet(String initialSort, String newSort) {
        offersPage.selectSortBy(newSort);
    }

    @Then("I verify the offers are sorted by {string}")
    public void iVerifyTheOffersAreSortedBy(String sortType) {
        offersPage.waitForOffersToLoad();
        if (sortType.equalsIgnoreCase("Alphabet")) {
            boolean sorted = offersPage.isSortedAlphabetically(offersPage.getOfferNames());
            assert sorted : "Offers are not sorted alphabetically";
        } else if (sortType.equalsIgnoreCase("Price")) {
            boolean sorted = offersPage.isSortedByPrice(offersPage.getOfferNames()); // Assuming prices are part of the offer names
            assert sorted : "Offers are not sorted by price";
        }
    }

    @When("I change the value of the {string} dropdown from {string} to {string}")
    public void iChangeTheValueOfTheSortByDropdownFromAlphabetToPrice(String dropdown, String initialValue, String newValue) {
        offersPage.selectSortBy(newValue);
    }

    @After
    public void tearDown() {
        TestBase.quitDriver();
    }
}
