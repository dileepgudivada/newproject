package gov.uscis.odds.welcome;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.karsun.kic.tan.duke.Steps;
import com.karsun.kic.tan.duke.util.ActionByLocator;
//import static com.karsun.kic.tan.duke.Steps.executionContext;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@Component
public class WelcomePageStep extends Steps{
	private static final int TIME_OUT_SECONDS = 10;
	private WelcomePage welcomePage;
	
	private void init() {
		if (welcomePage == null) {
			welcomePage = new WelcomePage(executionContext.getDriver());
		}
		welcomePage.get();
	}
	@Given("^I am on the welcome page$")
	public void i_am_on_the_welcome_page() throws Throwable {
	    init();
	}
	
	@Then("^customer service email is visible$")
	public void customer_service_email_is_visible() throws Throwable {
		Assert.assertTrue("'Email' link is not visible", 
				ActionByLocator.isDisplayed(executionContext.getDriver(), 
						By.cssSelector("[href='mailto:info@calorietracker.com']"), TIME_OUT_SECONDS));
	}

	@Then("^customer service phone number is visible$")
	public void customer_service_phone_number_is_visible() throws Throwable {
		Assert.assertTrue("'Phone number' value is not visible", 
				ActionByLocator.isDisplayed(executionContext.getDriver(), 
						By.cssSelector("[href='tel:1-800-CAL-ORIE']"), TIME_OUT_SECONDS));
	}


}
