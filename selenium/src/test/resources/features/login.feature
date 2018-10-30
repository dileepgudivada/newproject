Feature: Login
  As a user,
  I want to login to the Calories Tracker
  so that I can have a customized calories-tracking experience

  @smoke
  Scenario: Login with valid credentials
    Given I am on the login page
    When I use valid username and password
    Then the user page is displayed
    
  @smoke
  Scenario: Link to create an account is displayed
    Given I am on the login page
    Then the "Create an Account" link is displayed
    
  @smoke
  Scenario: Login page element labels are displayed
    Given I am on the login page
    Then the expected element labels are displayed

  @smoke
  Scenario: Login with invalid username and password
    Given I am on the login page
    When I use invalid username and password
    Then I receive an "Access denied" message
