package transactionManagement;
import helper.FileIO;
import transactionManagement.ShopAssistant;

public class Main {

	public static void main(String[] args) {
		// For test
		SalaryManagement myShopAssistantArray = FileIO.createShopAssistantArrayByFile("shopAssistants.csv");
		myShopAssistantArray.toString();
		Product[] myProductArray = FileIO.createProductArrayByFile("products.csv");
		System.out.println(myProductArray[89]);
		Transaction transaction1 = new Transaction(myProductArray);
		System.out.println(transaction1);
	}

}
