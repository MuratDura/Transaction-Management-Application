package helper;

import transactionManagement.Product;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import transactionManagement.ShopAssistant;

public class FileIO {
	
	private static final int productArraySize = 90;
	private static final int shopAssistantArraySize = 100;
	
	// Takes file path as string and returns shopAssistant array
	public static ShopAssistant[] createShopAssistantArrayByFile(String fileName) {
		BufferedReader bufferedReader;
		String readLine;
		ShopAssistant[] shopAssistantArray = new ShopAssistant[shopAssistantArraySize];
		
		try {
			bufferedReader = new BufferedReader(new FileReader(fileName));
			readLine = bufferedReader.readLine();
			
			int arrayCounter = 0;
			while (readLine != null) {
				if (createShopAssistantByLine(readLine) == null) {
					bufferedReader.close();
					return null;
				} 
				else {
					shopAssistantArray[arrayCounter] = createShopAssistantByLine(readLine);
					readLine = bufferedReader.readLine();
					arrayCounter ++;
				}
			}
			bufferedReader.close();
		}
		catch (FileNotFoundException e) {
			System.out.println(e.getLocalizedMessage());
			return null;
		}
		catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
			return null;
		}
		return shopAssistantArray; 
	}
	// Takes a line and creates shopAssistant object
	public static ShopAssistant createShopAssistantByLine(String line) {
		String DELIMITER = ";";
		int TOKEN_NUMBER = 4;
		StringTokenizer stringTokenizer;
		ShopAssistant shopAssistantToReturn = null;
		
		stringTokenizer = new StringTokenizer(line, DELIMITER);
		
		if (stringTokenizer.countTokens() != TOKEN_NUMBER) {
			throw new IllegalArgumentException("Token numbers of shop assistants don't match.");
		}
		else {
			try {
				int ID = Integer.valueOf(stringTokenizer.nextToken());
				String name = stringTokenizer.nextToken();
				String surname = stringTokenizer.nextToken();
				String phoneNumber = stringTokenizer.nextToken();
				shopAssistantToReturn = new ShopAssistant(ID, name, surname, phoneNumber);
			}
			catch (IllegalArgumentException e) {
				System.out.println(e.getLocalizedMessage());
			}
		}
		return shopAssistantToReturn;
	}
	
	// Takes file path as string and returns array
	public static Product[] createProductArrayByFile(String fileName) {
		BufferedReader bufferedReader;
		String readLine;
		Product[] productArray = new Product[productArraySize];
		
		try {
			bufferedReader = new BufferedReader(new FileReader(fileName));
			readLine = bufferedReader.readLine();
			
			int arrayCounter = 0;
			while (readLine != null) {
				if (createProductByLine(readLine) == null) {
					bufferedReader.close();
					return null;
				} 
				else {
					productArray[arrayCounter] = createProductByLine(readLine);
					readLine = bufferedReader.readLine();
					arrayCounter ++;
				}
			}
			bufferedReader.close();
		}
		catch (FileNotFoundException e) {
			System.out.println(e.getLocalizedMessage());
			return null;
		}
		catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
			return null;
		}
		
		return productArray;
	}
	// Takes a line and creates Product object
	public static Product createProductByLine(String line) {
		String DELIMITER = ";";
		int TOKEN_NUMBER = 3;
		StringTokenizer stringTokenizer;
		Product productToReturn = null;
		
		stringTokenizer = new StringTokenizer(line, DELIMITER);
		
		if (stringTokenizer.countTokens() != TOKEN_NUMBER) {
			throw new IllegalArgumentException("Token numbers of products don't match.");
		}
		else {
			try {
				int ID = Integer.valueOf(stringTokenizer.nextToken());
				String name = stringTokenizer.nextToken();
				String priceTemp = stringTokenizer.nextToken();
				// Converting 5,99 to 5.99 format
				priceTemp = priceTemp.replaceAll(",", ".");
				double price = Double.parseDouble(priceTemp);
				productToReturn = new Product(ID, name, price);
			}
			catch (IllegalArgumentException e) {
				System.out.println(e.getLocalizedMessage());
			}
		}
		return productToReturn;
	}
	
}
