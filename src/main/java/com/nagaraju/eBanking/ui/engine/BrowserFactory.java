package com.nagaraju.eBanking.ui.engine;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Getter;
import lombok.Setter;


public class BrowserFactory {


    private static final Logger logger = LoggerFactory.getLogger(BrowserFactory.class);

    @Getter
    private WebDriver driver;

    @Getter
    private Bot bot;


    public WebDriver theTargetBrowserisOpen() {
        String browserName = PropertiesManager.getProperty("browser.name");
        switch (browserName.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--incognito", "--start-maximized");
                chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
                driver = new ChromeDriver(chromeOptions);
                //   driver.get(PropertiesManager.getProperty("base.url"));
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("-private", "--start-maximized");
                driver = new FirefoxDriver(firefoxOptions);
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--inprivate", "--start-maximized");
                driver = new EdgeDriver(edgeOptions);
                break;

            default:
                logger.error("Please pass a correct browser value");
                throw new IllegalArgumentException("Unsupported browser: " + browserName);

        }

        bot = new Bot(driver);
        return driver;
    }

    public Bot getBot() {
        return bot;
    }


}
