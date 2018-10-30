package gov.uscis.odds.login;

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

public class LoginPage extends Page {
	private static final int TIMEOUT_SECONDS = 10;
	private static boolean initialRun = true;
	
	private By usernameInput = By.cssSelector("[name='username']");
	private By passwordInput = By.cssSelector("[name='password']");
	private By loginButton = By.xpath("//button");

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@Override
	protected boolean isLoaded() {
		if (initialRun) {
			initialRun = false;
			return false;
		}
		return ActionByLocator.isDisplayed(driver, By.cssSelector("[name='username']"), TIMEOUT_SECONDS);
	}

	@Override
	protected void load() {
		driver.get(LoadProperties.getApplicationURL());
		
		waitForAngular();
		
		if (driver.findElements(By.cssSelector("[class='logout']")).size() != 0) {
			ActionByLocator.click(driver, By.cssSelector("[class='logout']"), TIMEOUT_SECONDS);
			new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("[class='please-wait-spinner']")));
		}
		
		Assert.assertTrue("Could not load Login page", isLoaded());
	}

	public void login(JsonObject loginObject) {		
		String username = loginObject.get("username").getAsString();
		String password = loginObject.get("password").getAsString();
		loginWithRetry(username, password);
	}

	public String getErrorMessage() {
		waitForAngular();
		return ActionByLocator.getText(driver, By.cssSelector("[ng-repeat*='errorMessages']"), TIMEOUT_SECONDS).trim();
	}

	private void loginWithRetry(String username, String password) {
		sendLoginKeys(username, password);		
		if (!ActionByLocator.getElement(driver, usernameInput, TIMEOUT_SECONDS).getAttribute("value").contains(username)) {
			Util.waitFor(5);
			sendLoginKeys(username, password);
		}
		
		ActionByLocator.click(driver, loginButton, TIMEOUT_SECONDS);
	}

	private void sendLoginKeys(String username, String password) {
		waitForAngular();
		ActionByLocator.clear(driver, usernameInput, TIMEOUT_SECONDS);
		ActionByLocator.sendKeys(driver, usernameInput, username, TIMEOUT_SECONDS);
		
		waitForAngular();
		ActionByLocator.clear(driver, passwordInput, TIMEOUT_SECONDS);
		ActionByLocator.sendKeys(driver, passwordInput, password, TIMEOUT_SECONDS);
	}
	
	private void waitForAngular() {
		new NgWebDriver((JavascriptExecutor) driver).waitForAngularRequestsToFinish();
	}
}
