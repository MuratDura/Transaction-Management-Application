package transactionManagement;
import java.util.Arrays;
import helper.FileIO;

public class SalesManagementApp {
	
	

	public static void main(String[] args) {
		Product[] products = FileIO.createProductArrayByFile("products.csv");
		ShopAssistant[] shopAssistants = FileIO.createShopAssistantArrayByFile("shopAssistants.csv");
		
		final int transactionPerShopAssistant = 15;
		int totalShopAssistant = shopAssistants.length;
		
		
		
		SalaryManagement salaryManagement = new SalaryManagement();
		TransactionManagement transactionManagement = new TransactionManagement(totalShopAssistant, transactionPerShopAssistant);
		for (int i = 0; i < totalShopAssistant; i++) {
			salaryManagement.addAssistant(shopAssistants[i]);
			for (int j = 0; j < transactionPerShopAssistant; j++) {
				Transaction transaction = new Transaction(products);
				transactionManagement.addTransaction(i, transaction);
			}
			transactionManagement.setCommission(shopAssistants[i]);
		}
		
		
		
		for (int i = 0; i < totalShopAssistant; i++) {
			for (int j = 0; j < transactionPerShopAssistant; j++) {
				Transaction transaction = new Transaction(products);
				transactionManagement.addTransaction(i, transaction);
			}
		}
		
		
		
		System.out.println(Arrays.deepToString(transactionManagement.getTransactions()));
		System.out.println("\n\n\nThe highest total price transaction is " + Query.findHighestTotalPriceTransaction(transactionManagement));
		System.out.println("\n\n\nThe most expensive product in lowest price transaction is " + Query.findMostExpensiveProductInLowestPriceTransaction(transactionManagement));
		System.out.println("\n\n\nThe transaction that has the lowest transaction fee is " + Query.findLowestTransactionFee(transactionManagement));
		System.out.println("\n\n\nTotal revenue: " + Query.calculateTotalRevenue(transactionManagement));
		System.out.println("\n\n\nTotal profit: " + Query.calculateTotalProfit(transactionManagement));
	}

}
