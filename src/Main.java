package transactionManagement;
import helper.FileIO;

public class Main {

	public static void main(String[] args) {
		// For test
		ShopAssistant[] myShopAssistantArray = FileIO.createShopAssistantArrayByFile("shopAssistants.csv");
		System.out.println(myShopAssistantArray[99]);
		Product[] myProductArray = FileIO.createProductArrayByFile("products.csv");
		System.out.println(myProductArray[89]);
		Transaction transaction1 = new Transaction(myProductArray);
		System.out.println(transaction1);
	}

}
