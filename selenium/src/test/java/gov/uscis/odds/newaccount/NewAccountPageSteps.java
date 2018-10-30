package gov.uscis.odds.newaccount;


import org.junit.Assert;
import org.springframework.stereotype.Component;

import com.karsun.kic.tan.duke.Steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@Component
public class NewAccountPageSteps extends Steps {
	private static final int TIME_OUT_SECONDS = 10;
	private NewAccountPage AccountPage ;
	
	private void init() {
		if (AccountPage == null) {
			AccountPage = new NewAccountPage(executionContext.getDriver());
		}
		AccountPage.get();	
	}
	
	@Given("^I am on the Create New Account page$")
	public void i_am_on_the_Create_New_Account_page() {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();
		init();
	}

	@When("^I enter all required user information$")
	public void i_enter_all_required_user_information() {
	    // Write code here that turns the phrase above into concrete actions
		AccountPage.createAccount(executionContext.getCurrentScenarioObj().get("signIn").getAsJsonObject());
	    //throw new PendingException();
	}

	@Then("^I can login to the application$")
	public void i_can_login_to_the_application() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^I do not enter required user information$")
	public void i_do_not_enter_required_user_information() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^I receive an error message$")
	public void i_receive_an_error_message() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^I enter my password$")
	public void i_enter_my_password() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^my password should be hidden$")
	public void my_password_should_be_hidden() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^I enter an invalid email address$")
	public void i_enter_an_invalid_email_address() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^I enter numeric data on any text-only fields$")
	public void i_enter_numeric_data_on_any_text_only_fields() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
}
