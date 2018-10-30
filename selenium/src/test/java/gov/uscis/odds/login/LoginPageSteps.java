package gov.uscis.odds.login;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.karsun.kic.tan.duke.Steps;
import com.karsun.kic.tan.duke.util.ActionByLocator;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@Component
public class LoginPageSteps extends Steps {
	private static final int TIME_OUT_SECONDS = 10;
	private LoginPage loginPage;
	
	private void init() {
		if (loginPage == null) {
			loginPage = new LoginPage(executionContext.getDriver());
		}
		loginPage.get();
	}
	
	@Given("^I am on the login page$")
	public void i_am_on_the_login_page() {
	   init();
	}

	@When("^I use valid username and password$")
	public void user_login() {
	    loginPage.login(executionContext.getCurrentScenarioObj().get("signIn").getAsJsonObject());
	}

	@When("^I use invalid username and password$")
	public void i_use_invalid_username_and_password() {
		user_login();
	}
	
	@Then("^I receive an \"([^\"]*)\" message$")
	public void i_receive_an_message(String errorMessage) {
		Assert.assertEquals("Error message does not match", errorMessage, loginPage.getErrorMessage());
	}
	
	@Then("^the \"([^\"]*)\" link is displayed$")
	public void the_link_is_displayed(String linkText) {
	    init();
	    Assert.assertTrue("'Create an Account' link is not displayed", ActionByLocator.isDisplayed(executionContext.getDriver(), By.xpath("//a[text()='"+linkText+"']"), TIME_OUT_SECONDS));
	}
	
	@Then("^the expected element labels are displayed$")
	public void the_expected_element_labels_are_displayed() {
		init();
		JsonArray labels = executionContext.getCurrentScenarioObj().get("labels").getAsJsonArray();
		
		for (JsonElement label : labels) {
			Assert.assertTrue("Label not found: " +label.getAsString(), ActionByLocator.isDisplayed(executionContext.getDriver(), By.xpath("//*[contains(text(),'"+label.getAsString()+"')]"), TIME_OUT_SECONDS));
		}
	}

}
