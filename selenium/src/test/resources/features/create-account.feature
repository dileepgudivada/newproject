
Feature: Create an Account
  As a user,
  I want to create an account
  so that I can access the Calorie Tracker application

  Scenario: Create an account and login to application
    Given I am on the Create New Account page
    When I enter all required user information
    Then I can login to the application

  Scenario: Attempt creating an account but forget required fields
    Given I am on the Create New Account page
    When I do not enter required user information
    Then I receive an error message

  Scenario: Password field should hide characters
    Given I am on the Create New Account page
    When I enter my password
    Then my password should be hidden

  Scenario: Invalid email address entered in email field
    Given I am on the Create New Account page
    When I enter an invalid email address
    Then I receive an error message

  Scenario: Error received when numeric characters entered in numeric-only fields
    Given I am on the Create New Account page
    When I enter numeric data on any text-only fields
    Then I receive an error message
