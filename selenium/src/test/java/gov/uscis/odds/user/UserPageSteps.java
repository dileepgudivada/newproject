package gov.uscis.odds.user;


import org.junit.Assert;
import org.springframework.stereotype.Component;

import com.karsun.kic.tan.duke.Steps;
import cucumber.api.java.en.Then;

@Component
public class UserPageSteps extends Steps {
	private UserPage userPage;
	
	private void init() {
		if (userPage == null) {
			userPage = new UserPage(executionContext);
		}
		userPage.get();
	}
	
	@Then("^the user page is displayed$")
	public void the_user_page_is_displayed() {
	    init();
	    Assert.assertEquals("User page title", executionContext.getDriver().getTitle(), "Calories Tracker");
	}
}
