package com.nagaraju.eBanking.ui.engine;

import org.openqa.selenium.WebDriver;

public class BasePage{
	
	 protected WebDriver driver;
	    protected Bot bot;

	    public BasePage(TestContext context) {
	        this.driver = context.getDriver();
	        this.bot = context.getBot();
	    }
}
