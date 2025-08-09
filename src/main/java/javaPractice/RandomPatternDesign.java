package javaPractice;

import java.util.Random;

public class RandomPatternDesign {
	
	
	public static String generateRandomPanCard()

	{
		//PAN card pattern AGBPY4403G
		/*
		 * 5 characters 
		 * 4 digits
		 * 1 character
		 */
		Random random = new Random();
		StringBuilder pan = new StringBuilder();
		
		for(int i = 0 ; i<5 ; i++)
		{
			pan.append((char)('A'+random.nextInt(26)));
		}
		
		for(int i = 0;i<4; i++)
		{
			pan.append(random.nextInt(10));
		}
		
		pan.append((char)('A'+random.nextInt(26)));
		
		return pan.toString().toUpperCase();
	}

	public static String generateRandomAadharCard()
	{
		//Aadhar card series 4003 7113 8051
		
		Random random = new Random();
		StringBuilder aadhar = new StringBuilder();
		
		for(int i = 0; i< 12; i++)
		{
			aadhar.append(random.nextInt(10));
			
			if((i+1) % 4 == 0)
			{
				aadhar.append(" ");
			}
			
		}
		
		return aadhar.toString();
	}
	
	public static void main(String[] args) {
		
		System.out.println(generateRandomAadharCard());

	}

}
