package com.nagaraju.eBanking.ui.engine;

import java.util.Random;
import java.util.UUID;

import org.openqa.selenium.WebDriver;

public class Common {
	
	public static WebDriver driver;
	
	
	public static String random()
	{
		 return UUID.randomUUID().toString().substring(0, 6);
		
	}
	
	public static String generatePanNumber()
	{
		Random random = new Random();
		StringBuilder pan = new StringBuilder();
		//PAN card Format AGBPY4403G
		
		//generate 5 characters and append to the pan
		for(int i=0;i<5;i++)
		{
			pan.append((char) (65 + random.nextInt(26)));
		}
		
		//generate 4 random digits and add to pan
		for(int i=0 ; i<4 ; i++) {
			
			pan.append(random.nextInt(10));
		}
		
		//generate a single letter 
		pan.append((char) ('A'+ random.nextInt(26)));
		
		return pan.toString().toUpperCase();
		
	}
	
	public static String generateRandomAadharNumber()
	{
		Random random = new Random();
        StringBuilder aadhaar = new StringBuilder();

        for (int i = 0; i < 12; i++) {
            aadhaar.append(random.nextInt(10)); // append digits 0-9

            // Add space after every 4 digits except the last
            if ((i + 1) % 4 == 0) {
                aadhaar.append(" ");
            }
        }

        return aadhaar.toString();
	}
	
	public static String generateRandomPhoneNumber()
	{
		Random random = new Random();
		StringBuilder mobile = new StringBuilder();
		
		//Generate 10 digit number 
		mobile.append(9);
		for(int i=0; i<9; i++) {
			mobile.append(random.nextInt(10));
		}
		
		return mobile.toString();
	}
	
	public static void takeScreenshot()
	{
		
	}



	

}
