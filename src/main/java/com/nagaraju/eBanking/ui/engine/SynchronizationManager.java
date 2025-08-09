package com.nagaraju.eBanking.ui.engine;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Getter;

public class SynchronizationManager {
	
	
	@Getter
	private WebDriver driver;
	
	@Getter
	private WebDriverWait wait;
	
	private static final Logger logger = LoggerFactory.getLogger(SynchronizationManager.class);

	public SynchronizationManager(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Constants.MAX_WAIT_TIME);
	}

	public WebElement waitForTheElementToBeVisible(By locator) {

		try {
			return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		} catch (Exception e) {
			logger.error("Element not visible: " + locator, e);
			throw new RuntimeException("Element not visible: " + locator, e);
		}

	}
	
	public WebElement moveToElement(By locator) {
	    try {
	        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	        new Actions(driver).moveToElement(element).perform();
	        logger.info("Moved to element: {}", locator);
	        return element;
	    } catch (Exception e) {
	        logger.error("Failed to move to element: {}", locator, e);
	        throw new RuntimeException("Exception occurred while moving to the element: " + locator, e);
	    }
	}

	
	public WebElement waitForElementIsPresent(By locator)
	{
		try {
			return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			
		} catch (Exception e) {
			logger.error("Element not visible: " + locator, e);
			throw new RuntimeException("Element not present in DOM: " + locator, e);
		}
	}
	
	public Boolean waitForTheElementNotToBeVisible(By locator) {

		try {
			return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
		} catch (Exception e) {
			logger.error("Element is visible: " + locator, e);
			throw new RuntimeException("Element is visible: " + locator, e);
		}

	}
	
	public Boolean waitForTheElementToBeSelected(By locator)
	{
		try {
			
			boolean isSelected = wait.until(ExpectedConditions.elementToBeSelected(locator));
			 logger.info("Element is selected: " + locator.toString());
			 return isSelected;
	    } catch (TimeoutException e) {
	        logger.error("Timeout waiting for element to be selected: " + locator.toString(), e);
	        throw new RuntimeException("Element not selected within timeout: " + locator, e);
	    }
	}
	
	public WebElement waitForTheElementToBeClickable(By locator) {
	    try {
	        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
	        logger.info("Element {} is clickable", locator);
	        return element;
	    } catch (Exception e) {
	        logger.error("Element {} is not clickable within the expected time", locator, e);
	        throw new RuntimeException("Exception occurred while waiting for element to be clickable: " + locator, e);
	    }
	}
	
	public void waitForOverlayToDisappear()
	{
		try {
			
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("overlayer")));
			logger.info("Overlay present");
			
		} catch (TimeoutException e) {
			logger.error("Overlay did not disappear within the timeout.");
		}
		
	}
	 public Alert waitForAlert() {
	        try {
	            return  wait.until(ExpectedConditions.alertIsPresent());
	        } catch (Exception e) {
	            logger.error("Alert not present", e);
	            throw new RuntimeException("Alert not present", e);
	        }
	    }

	    public boolean waitForAttributeContains(By locator, String attribute, String value) {
	        try {
	            return wait.until(ExpectedConditions.attributeContains(locator, attribute, value));
	        } catch (Exception e) {
	            logger.error("Attribute does not contain value: " + attribute + "=" + value, e);
	            return false;
	        }
	    }

	    public WebDriver waitForFrameAndSwitchToIt(By frameLocator) {
	        try {
	            return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
	        } catch (Exception e) {
	            logger.error("Frame not available to switch: " + frameLocator, e);
	            throw new RuntimeException("Frame not available: " + frameLocator, e);
	        }
	    }
	    public void scrollToView(By locator) {
	        try {
	            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
	            logger.info("Scrolled to the element: {}", locator);
	        } catch (Exception e) {
	            logger.error("Failed to scroll to element: {}", locator, e);
	            throw new RuntimeException("Unable to scroll to element: " + locator, e);
	        }
	    }
	    
	   
	    
	    
	    

	    
}
