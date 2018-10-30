package gov.uscis.odds.newaccount;

import gov.uscis.odds.util.LoadProperties;
import gov.uscis.odds.util.Util;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.gson.JsonObject;
import com.karsun.kic.tan.duke.Page;
import com.karsun.kic.tan.duke.util.ActionByLocator;
import com.paulhammant.ngwebdriver.NgWebDriver;

public class NewAccountPage extends Page {
	private static final int TIME_OUT_SECONDS = 10;
	private static boolean initialRun = true;
	
	private By prefixSelect = By.cssSelector("[id='prefix']");
	private By firstnameInput = By.cssSelector("[name='firstname']");
	private By lastnameInput = By.cssSelector("[name='lastname']");
	private By emailaddressInput = By.cssSelector("[name='emailaddress']");
	private By usernameInput = By.cssSelector("[name='username']");
	private By passwordInput = By.cssSelector("[name='password']");
	private By confirmpasswordInput = By.cssSelector("[id='confirmpassword']");
	private By submitButton = By.cssSelector("[id='submit']");
	//private By submitButton = By.xpath("//button");

	public NewAccountPage(WebDriver driver) {
		super(driver);
	}

	@Override
	protected boolean isLoaded() {
		if (initialRun) {
			initialRun = false;
			return false;
		}
		return ActionByLocator.isDisplayed(driver, By.cssSelector("[name='username']"), TIME_OUT_SECONDS);
	}

	@Override
	protected void load() {
		driver.get(LoadProperties.getApplicationURL("web.urlaccount"));
		
		waitForAngular();
		
		if (driver.findElements(By.cssSelector("[class='submit']")).size() != 0) {
			ActionByLocator.click(driver, By.cssSelector("[class='submit']"), TIME_OUT_SECONDS);
			new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("[class='please-wait-spinner']")));
		}
		
		Assert.assertTrue("Could not load Login page", isLoaded());
	}

	public void createAccount(JsonObject AccountObject) {		
		String prefix = AccountObject.get("prefix").getAsString();
		String firstname = AccountObject.get("firstname").getAsString();
		String lastname = AccountObject.get("lastname").getAsString();
		String emailaddress = AccountObject.get("emailaddress").getAsString();
		String username = AccountObject.get("username").getAsString();
		String password = AccountObject.get("password").getAsString();
		String confirmpassword = AccountObject.get("confirmpassword").getAsString();
		
		if (!ActionByLocator.getElement(driver, usernameInput, TIME_OUT_SECONDS).getAttribute("value").contains(username)) {
			Util.waitFor(5);
			sendNewAccountKeys(prefix, firstname, lastname, emailaddress, username, password, confirmpassword);
		}
		
		//loginWithRetry(username, password);
	}

	public String getErrorMessage() {
		waitForAngular();
		return ActionByLocator.getText(driver, By.cssSelector("[ng-repeat*='errorMessages']"), TIME_OUT_SECONDS).trim();
	}


	private void sendNewAccountKeys(String prefix, String firstname, String lastname, String emailaddress, String username, String password, String confirmpassword) {
		
		waitForAngular();
		//ActionByLocator.clear(driver, prefixSelect, TIME_OUT_SECONDS);
		ActionByLocator.sendKeys(driver, prefixSelect, username, TIME_OUT_SECONDS);
		waitForAngular();
		ActionByLocator.clear(driver, firstnameInput, TIME_OUT_SECONDS);
		ActionByLocator.sendKeys(driver, firstnameInput, firstname, TIME_OUT_SECONDS);
		waitForAngular();
		ActionByLocator.clear(driver, lastnameInput, TIME_OUT_SECONDS);
		ActionByLocator.sendKeys(driver, lastnameInput, lastname, TIME_OUT_SECONDS);
		waitForAngular();
		ActionByLocator.clear(driver, emailaddressInput, TIME_OUT_SECONDS);
		ActionByLocator.sendKeys(driver, emailaddressInput, emailaddress, TIME_OUT_SECONDS);
		waitForAngular();
		ActionByLocator.clear(driver, usernameInput, TIME_OUT_SECONDS);
		ActionByLocator.sendKeys(driver, usernameInput, username, TIME_OUT_SECONDS);

		
		waitForAngular();
		ActionByLocator.clear(driver, passwordInput, TIME_OUT_SECONDS);
		ActionByLocator.sendKeys(driver, passwordInput, password, TIME_OUT_SECONDS);
		
		waitForAngular();
		ActionByLocator.clear(driver, confirmpasswordInput, TIME_OUT_SECONDS);
		ActionByLocator.sendKeys(driver, confirmpasswordInput, confirmpassword, TIME_OUT_SECONDS);
		
		
	}
	
	private void waitForAngular() {
		new NgWebDriver((JavascriptExecutor) driver).waitForAngularRequestsToFinish();
	}
}
