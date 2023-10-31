import java.util.Arrays;
import transactionManagement.Transaction;
import transactionManagement.ShopAssistant;

public class TransactionManagement {
	private Transaction[][] transactions; // stores transactions in a way that each row represents one shop assistant's transactions
	private int shopAssistantCapacity = 20; // initial value for rows
	private int transactionCapacity = 5; // initial value for columns
	private int shopAssistantSize; // number of present shop assistants
	private int transactionSize; // number of present transactions within a row
	
	//some several constants related to transaction fee 
	private static final int BOUND1 = 499;
	private static final int FEE1 = 1;
	private static final int BOUND2 = 799;
	private static final int FEE2 = 3;
	private static final int BOUND3 = 999;
	private static final int FEE3 = 5;
	private static final int FEE4 = 9;
	
	//some other constants related to commission paid to a shop assistant
	private static final int TOTAL_REVENUE_BOUND = 7500;
	private static final int HIGH_COMMISSION = 3; // percentage
	private static final int LOWER_COMMISSION = 1; // percentage
	
	
	public TransactionManagement() {
		this.transactions = new Transaction[shopAssistantCapacity][transactionCapacity];
	}
	
	/**
	 * adds a new transaction to the transactions field 
	 * determining its transaction fee according to its total price
	 * @param transaction
	 */
	public void addTransaction(Transaction transaction) {
		//input validation
		if (transaction == null) {
			throw new IllegalArgumentException("Transaction cannot be null.");
		}
		//check and increase the capacity if it is full
		checkTransactionCapacity();
		//determine the transaction fee of the transaction being added
		setTransactionFee(transaction);
		this.transactions[shopAssistantSize][transactionSize++] = new Transaction(transaction);
	}
	
	/**
	 * adds a new row to the 2D array for another shop assistant 
	 * @param shopAssistant
	 */
	public void addShopAssistant() {
		// reset the transaction size for the new shop assistant
		transactionSize = 0;
		//check capacity and increase if needed
		checkShopAssistantCapacity();
		shopAssistantSize++;
	}
	
	//*************HELPER METHODS****************
	
	private void checkShopAssistantCapacity(){
		if (shopAssistantSize >= shopAssistantCapacity) {
			shopAssistantCapacity *= 2;
			Transaction[][] temp = new Transaction[shopAssistantCapacity][transactionCapacity];
			for (int i = 0; i < shopAssistantSize; i++) {
				for (int j = 0; j < transactionSize; j++) {
					temp[i][j] = this.transactions[i][j];
				}
			}
			this.transactions = temp;
		}
	}
	
	private void checkTransactionCapacity() {
		if (transactionSize >= transactionCapacity) {
			transactionCapacity *= 2;
			Transaction[][] temp = new Transaction[shopAssistantCapacity][transactionCapacity];
			for (int i = 0; i < shopAssistantSize; i++) {
				for (int j = 0; j < transactionSize; j++) {
					temp[i][j] = this.transactions[i][j];
				}
			}
			this.transactions = temp;
		}
	}
	
	public void setCommission(ShopAssistant shopAssistant) {
		int index = shopAssistant.getID();
		if (index >= shopAssistantSize) {
			throw new IllegalArgumentException("Shop assistant cannot be found.");
		}
		double totalRevenue = 0;
		int i = 0;
		while (transactions[index][i] != null) {
			totalRevenue += transactions[index][i].getTotalPrice();
			i++;
		}
		if (totalRevenue > TOTAL_REVENUE_BOUND) {
			shopAssistant.addCommission(HIGH_COMMISSION);
		}else {
			shopAssistant.addCommission(LOWER_COMMISSION);
		}
	}
	
	private void setTransactionFee(Transaction transaction) {
		double totalPrice = transaction.getTotalPrice();
		if (totalPrice <= BOUND1) {
			transaction.setTransactionFee(FEE1);
		}else if (totalPrice <= BOUND2) {
			transaction.setTransactionFee(FEE2);
		}else if (totalPrice <= BOUND3) {
			transaction.setTransactionFee(FEE3);
		}else {
			transaction.setTransactionFee(FEE4);
		}
	}
	
	public Transaction[][] getTransactions(){
		return transactions;
	}
	
	@Override
	public String toString() {
		String result = "";
		for (int i = 0; i <= shopAssistantSize; i++) {
			result += "-----------Shop Assistant id: " + i + "-------------\n";
			int j = 0;
			while (transactions[i][j] != null) {
				Transaction transaction = transactions[i][j];
				result += transaction + "\n";
				j++;
			}
		}
		return result;
	}
}
