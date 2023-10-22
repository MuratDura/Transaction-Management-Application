import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import ShopAssistant;

public class FileIO {
	
	public static ShopAssistant[] createShopAssistantArrayByFile(String fileName) {
		BufferedReader bufferedReader;
		String readLine;
		ShopAssistant[] shopAssistantArray = new ShopAssistant[100];
		
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
	
	public static ShopAssistant createShopAssistantByLine(String line) {
		String DELIMITER = ";";
		int TOKEN_NUMBER = 4;
		StringTokenizer stringTokenizer;
		ShopAssistant shopAssistantToReturn = null;
		
		stringTokenizer = new StringTokenizer(line, DELIMITER);
		
		if (stringTokenizer.countTokens() != TOKEN_NUMBER) {
			return null;
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
}
