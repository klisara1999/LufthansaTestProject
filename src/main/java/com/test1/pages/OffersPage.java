package com.test1.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class OffersPage {
    private final WebDriver driver;
    private final WebDriverWait wait;


    private final By sortByDropdown = By.xpath("/html/body/div[3]/div[4]/div[2]/div/div[1]/div/form/div/div/div[2]/div[2]/div/div[3]/div/div/div[2]/div/div[1]/button/span");
    private final By offerItems = By.xpath("/html/body/div[3]/div[4]/div[2]/div/div[1]/div/form/div/div/div[2]/div[2]/div/div[3]");
    private final By bookAndPrepareButton = By.xpath("//button[@class='border-0 common-header-nav-button text-nowrap my-0']//span[text()='Book & Prepare']");
    private final By offersAndDestinationsLink = By.linkText("Offers & destinations");


    public OffersPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(100)); // 100-second wait for elements
    }

    // Accept cookies
    public void acceptCookies() {
        driver.findElement(By.id("cm-acceptAll")).click();
    }

    // Click on "Book & Prepare" and then select "Offers & Destinations"
    public void clickOnBookAndPrepareThenOffersAndDestinations() {
        WebElement bookAndPrepare = driver.findElement(bookAndPrepareButton);
        bookAndPrepare.click();

        WebElement offersAndDestinations = driver.findElement(offersAndDestinationsLink);
        offersAndDestinations.click();
    }

    // Select sort type from dropdown
    public void selectSortBy(String sortBy) {
        WebElement dropdown = driver.findElement(sortByDropdown);
        Select select = new Select(dropdown);
        select.selectByVisibleText(sortBy);
    }


    public List<String> getOfferNames() {
        List<WebElement> offers = driver.findElements(offerItems);
        return offers.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void waitForOffersToLoad() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(offerItems));
    }


    public boolean isSortedAlphabetically(List<String> offerNames) {
      return true; //missing implementation
    }


   public boolean isSortedByPrice(List<String> offerPrices) {
       return true ;//missing implementation
    }
}
