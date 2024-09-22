Feature: Search flights
Scenario: Search flights on Lufthansa homepage
Given I navigate to the Lufthansa homepage
When I enter departure airport "TIA" and destination airport "JFK"
And I select departure day one week after current date and return date two days later
And I click the Continue button
And I click the Search Flights button
Then I verify the results contain departure airport "TIA" and destination airport "JFK"
And I verify the correct departure and return dates are displayed