package com.nagaraju.eBanking.ui.engine;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesManager {
	private static final Logger logger = LoggerFactory.getLogger(PropertiesManager.class);
	public static Properties properties;

  
	public static Properties loadConfigProperties() {
	    try (InputStream inputStream = PropertiesManager.class.getClassLoader().getResourceAsStream("config.properties")) {
	        if (inputStream == null) {
	            throw new FileNotFoundException("config.properties not found in classpath");
	        }
	        properties = new Properties();
	        properties.load(inputStream);
	       // logger.info("Config properties loaded successfully");
	       
	    } catch (IOException e) {
	        logger.error("Error loading config.properties", e);
	        throw new RuntimeException("Failed to load configuration", e);
	    }
	    return properties;
	}


    // Fetch a property value by key
    public static String getProperty(String key) {    	
    	loadConfigProperties();
        return properties.getProperty(key);
    }


   
  
}
