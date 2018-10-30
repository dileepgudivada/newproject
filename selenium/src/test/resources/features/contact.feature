@testing
Feature: contact
  As a user,
  I want to see customer service e-mail
  so that I can contact customer service
    
  
  Scenario: Go to welcome page and verify email is present
  	Given I am on the welcome page
  	Then customer service email is visible
  	
  Scenario: Go to welcome page and verify phone number is present
  	Given I am on the welcome page
  	Then customer service phone number is visible
  	
  Scenario: Go to login page and verify email is present
  	Given I am on the login page
  	Then customer service email is visible
  	
  Scenario: Go to login page and verify phone number is present
  	Given I am on the login page
  	Then customer service phone number is visible