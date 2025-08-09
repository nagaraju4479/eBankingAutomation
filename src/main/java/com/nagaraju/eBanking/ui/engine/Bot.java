package com.nagaraju.eBanking.ui.engine;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.io.Files;

import lombok.Getter;

public class Bot {

	private static final Logger logger = LoggerFactory.getLogger(Bot.class);

	@Getter
	protected WebDriver driver;

	@Getter
	protected SynchronizationManager wait;

	public Bot(WebDriver driver) {
		this.driver = driver;
		this.wait = new SynchronizationManager(driver);
	}
	@Step("clicking on {}")
	public Bot click(By locator) {
		try {
			wait.waitForOverlayToDisappear();
			wait.scrollToView(locator);

			WebElement element = wait.waitForTheElementToBeClickable(locator);

			if (element.isDisplayed() && element.isEnabled()) {
				try {
					new Actions(driver).moveToElement(element).click().perform();
					logger.info("Clicked on the element : {}", locator);
				} catch (ElementClickInterceptedException e) {
					logger.warn("Element click intercepted, attempting JavaScript click for: {}", locator);
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
				}
			} else {
				logger.error("Element not interactable (either not displayed or not enabled): {}", locator);
				throw new RuntimeException("Element not interactable: " + locator);
			}
		} catch (Exception e) {
			logger.error("Click failed for : {}", locator, e);
			throw new RuntimeException("An exception occurred while clicking on the locator: " + locator, e);
		}
		return this;
	}


	public Bot enterText(By locator, String inputText) {
		try {
			wait.scrollToView(locator);
			WebElement element = wait.waitForTheElementToBeVisible(locator);
			new Actions(driver).moveToElement(element).perform();
			element.click();
			// element.clear();
			element.sendKeys(inputText);
			logger.info("Entered text '{}' into element: {}", inputText, locator);
		} catch (Exception e) {
			logger.error("Failed to enter text '{}' in element: {}", inputText, locator, e);
			throw new RuntimeException("Exception occurred while entering text in element: " + locator, e);
		}
		return this;
	}

	public WebElement getWebElement(By locator) {

		return driver.findElement(locator);

	}

	// Alert checks

	public Bot acceptAlert() {
		try {
			Alert alert = wait.waitForAlert();
			String alertText = alert.getText(); // Optional: log alert text
			logger.info("Accepting alert with text: {}", alertText);
			alert.accept();
			logger.info("Alert accepted successfully.");
		} catch (TimeoutException e) {
			logger.error("Timeout waiting for alert to appear.", e);
			throw new RuntimeException("Timeout waiting for alert.", e);
		} catch (NoAlertPresentException e) {
			logger.error("No alert present to accept.", e);
			throw new RuntimeException("No alert present.", e);
		} catch (Exception e) {
			logger.error("Unexpected error while accepting alert.", e);
			throw new RuntimeException("Unexpected error while accepting alert.", e);
		}
		return this;
	}

	public void dismissAlert() {
		try {
			Alert alert = wait.waitForAlert();
			String alertText = alert.getText(); // Optional: useful for logging
			logger.info("Dismissing alert with text: {}", alertText);
			alert.dismiss();
			logger.info("Alert dismissed successfully.");
		} catch (TimeoutException e) {
			logger.error("Timeout while waiting for alert to dismiss.", e);
			throw new RuntimeException("Timeout waiting for alert.", e);
		} catch (NoAlertPresentException e) {
			logger.error("No alert present to dismiss.", e);
			throw new RuntimeException("No alert present.", e);

		} catch (Exception e) {
			logger.error("Unexpected error while dismissing alert.", e);
			throw new RuntimeException("Unexpected error while dismissing alert.", e);
		}
	}

	public String getAlertText() {
		try {
			Alert alert = wait.waitForAlert();
			String alertText = alert.getText();
			logger.info("Alert text retrieved: {}", alertText);
			return alertText;
		} catch (TimeoutException e) {
			logger.error("Timeout while waiting for alert text.", e);
			throw new RuntimeException("Timeout waiting for alert.", e);
		} catch (NoAlertPresentException e) {
			logger.error("No alert present to get text from.", e);
			throw new RuntimeException("No alert present.", e);
		} catch (Exception e) {
			logger.error("Unexpected error while getting alert text.", e);
			throw new RuntimeException("Unexpected error while getting alert text.", e);
		}

	}
	/*
	 * End of Alert message handling
	 */

	/*
	 * Handling screenshots
	 */

	public static String captureScreenshot(WebDriver driver, String screenshotName) {
		String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + screenshotName + "_" + timestamp + ".png";
		File destFile = new File(path);
		try {
			Files.copy(srcFile, destFile);
		} catch (IOException e) {
			logger.error("Unexpected error occured while copying source file to destincation", e);

		}

		return path;
	}

	public  Bot highlightElement(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='3px solid red'", element);
		return this;
	}

	public String getAttributeValue(By locator, String attributName) {
		WebElement attributeElement = driver.findElement(locator);
		return attributeElement.getDomAttribute(attributName);
	}

	/*
	 * End of Handling screenshots
	 */

	/*
	 * Handling dropdowns
	 */

	public Bot selectValueFromDropdown(By locator, String value) {
		try {
			WebElement dropdown = driver.findElement(locator);
			Select select = new Select(dropdown);
			select.selectByValue(value);
			logger.info("Selected '{}' from dropdown using value.", value);
		} catch (NoSuchElementException e) {
			logger.error("The option with value '{}' was not found in the dropdown. {}", value, e.getMessage());
		} catch (Exception e) {
			logger.error("Unexpected error occurred while selecting value from dropdown: {}", e.getMessage());
		}
		return this;
	}

	/*
	 * Upload file
	 */

	public Bot uploadFile(By locator, String relativePath) {

		String path = System.getProperty("user.dir") + File.separator
				+ (relativePath);
		try {
			wait.scrollToView(locator);
			WebElement element = wait.waitForTheElementToBeVisible(locator);
			new Actions(driver).moveToElement(element).perform();
			element.sendKeys(path);
			logger.info("File uploaded from '{}' into element: {}", path, locator);
		} catch (Exception e) {
			logger.error("File upload failed from '{}' in element: {}", path, locator, e);
			throw new RuntimeException("Exception occurred while entering text in element: " + locator, e);
		}
		return this;

	}

	public Bot switchToChildWindow(By locator) {
		String parentWindow = driver.getWindowHandle();
		Set<String> allWindows = driver.getWindowHandles();

		for (String window : allWindows) {
			if (!window.equals(parentWindow)) {
				driver.switchTo().window(window);
				WebDriverWait wait = new WebDriverWait(driver, Constants.MAX_WAIT_TIME);
				try {
					wait.until(ExpectedConditions.presenceOfElementLocated(locator));
					logger.info("Switched to child window with element: " + locator);
					return this;
				} catch (TimeoutException e) {
					logger.warn("Element not found in child window: " + window, e);
				}
			}
		}

		driver.switchTo().window(parentWindow);
		throw new RuntimeException("Child window with element " + locator + " not found.");
	}


	public Bot switchToParentWindow(String parentWindowHandle) {

		driver.switchTo().window(parentWindowHandle);
		logger.info("Switched back to parent window: " + parentWindowHandle);
		return this;
	}

	public Bot waitForModalWindowVisible(By modalLocator) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Constants.MAX_WAIT_TIME);
			wait.until(ExpectedConditions.visibilityOfElementLocated(modalLocator));
			logger.info("Modal is visible: " + modalLocator);
		} catch (TimeoutException e) {
			logger.error("Modal did not appear in time: " + modalLocator, e);
			throw e;
		}
		return this;
	}





}

