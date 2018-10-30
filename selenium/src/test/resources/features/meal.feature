@wip
Feature: meal
	As a user,
	I want meal options,
	So that I can customize my calorie tracking experience
	
	Background:
		Given I am logged in
	
	@smoke
	Scenario: meal option present in add
		Then there should be a drop-down menu category in the add bar
	
	@smoke
	Scenario: meal option present on search
		Then there should be a drop-down menu category in the search
	
	@smoke
	Scenario: meal option in add has drop-down selections
		When I click the meal option in add
		Then I can choose mealtime
	
	@smoke
	Scenario: meal option in search has drop-down selections
		When I click the meal option in search
		Then I can choose mealtime
		
	@smoke
	Scenario: category and search are compatible 
		When I click meal time in search 
		And I choose a mealtime 
		And I select 12/01/2014 in from date
		And I select 12/01/2017 in to date
		And I click search 
		Then all mealtime from 12/01/2014 to 12/01/2017 are displayed 

	