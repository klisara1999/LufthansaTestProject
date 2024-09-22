Feature: Offers Sorting Functionality

  Scenario: Sort offers alphabetically and by price
    Given I navigate to the Lufthansa offers page
    When I click on Book & Prepare and select Offers & Destinations
    Then I change the value of the dropdown from 'Price' to 'Alphabet'
    Then I verify the offers are sorted by 'Alphabet'
    When I change the value of the 'Sort By' dropdown from 'Alphabet' to 'Price'
    Then I verify the offers are sorted by 'Price'