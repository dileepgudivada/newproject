@wip
Feature: landing page
  As a user,
  I want a functional landing page
  so that I can I can move to different locations of the app

  Background:
    Given I am on the landing page
    
  @smoke
  Scenario: Clicking a link takes you the appropriate page
    When I click any link
    Then I am taken to the link page 
    
   @smoke
   Scenario: Clicking a link moves you the appropriate section in the landing page 
  	 When I click any link
     Then I am moved to the link section on the same page
     
   @smoke
   Scenario: Contact information is present 
     Then there is contactinfo
     
   Scenario: Images loaded 
     Then there is a testimonial image
     Then these is an image behind the main login
     Then there is an image in the about section 
     
     
     
    