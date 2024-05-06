
Feature: This feature is to test the negative test cases
  As a end user, I want to see the validation errors when try to login with invalid credentials
  
  Background:
  Given I have url to login to ecommerse website
  
  @ErrorTest
  Scenario: Verify user is seeing validation message when user name is valid and password is invalid
   	Given I have logged in with "naveen@123.com" and "password"
    Then I should see "Incorrect email or password" message