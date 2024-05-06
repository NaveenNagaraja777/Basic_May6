
Feature: Purchasing an item from ecommerce website
  As a user, I want to purchase an item from the ecommerce website successfully

	Background:
	Given I have url to login to ecommerse website

  @Smoke
  Scenario Outline: As a valid user, I want to purchase an item
    Given I have logged in with valid <username> and <password> 
    When I add the <product> 
    And  Checkout the <product> to submit the order
    Then I see "Thankyou for the order." success message

    Examples: 
      | username  | password | product |
      | naveen@123.com |Brave@2024 | ADIDAS ORIGINAL |