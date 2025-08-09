package com.eBanking.stepDefinitions;

import com.nagaraju.eBanking.ui.engine.Bot;
import com.nagaraju.eBanking.ui.engine.BrowserFactory;
import com.nagaraju.eBanking.ui.engine.PropertiesManager;
import com.nagaraju.eBanking.ui.engine.TestContext;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Hooks {

    private static final Logger logger = LoggerFactory.getLogger(BrowserFactory.class);
    private TestContext context;
    private PropertiesManager propertiesManager;

    public Hooks(TestContext context) {
        this.context = context;

    }


    @Before
    public void setup() {
        BrowserFactory browserFactory = new BrowserFactory();

        WebDriver driver = browserFactory.theTargetBrowserisOpen();
        Bot bot = browserFactory.getBot();

        context.setDriver(driver);
        context.setBot(bot);


    }
	
	/*@After
	public void tearDown() {
	    WebDriver driver = context.getDriver();
	    if (driver != null) {
	        driver.quit();
	        logger.info("Driver session is closed now!");
	    }
	}*/

}
