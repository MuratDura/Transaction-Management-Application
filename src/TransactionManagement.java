package transactionManagement;
import java.util.Arrays;

public class TransactionManagement {
	private Transaction[][] transactions; // stores transactions in a way that each row represents one shop assistant's transactions
	private final int shopAssistantCapacity;
	private final int transactionCapacity;
	private static final int MAX_CAPACITY = 10_000; 
	
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
	
	
	public TransactionManagement(int shopAssistantCapacity, int transactionCapacity) {
		this.shopAssistantCapacity = shopAssistantCapacity;
		this.transactionCapacity = transactionCapacity;
		if (shopAssistantCapacity > MAX_CAPACITY || transactionCapacity > MAX_CAPACITY) {
			throw new IllegalArgumentException("Shop Assistant or Transaction Capacity cannot be greater than " + MAX_CAPACITY);
		}
		this.transactions = new Transaction[shopAssistantCapacity][transactionCapacity];
	}
	
	/**
	 * adds a new transaction to the transactions field 
	 * determining its transaction fee according to its total price
	 * @param transaction
	 */
	public void addTransaction(int shopAssistantID, Transaction transaction) {
		//input validation
		if (shopAssistantID < 0) {
			throw new IllegalArgumentException("Shop assistant ID cannot be less than 0.");
		}
		if (transaction == null) {
			throw new IllegalArgumentException("Transaction cannot be null.");
		}
		
		for (int index = 0; index < transactions[shopAssistantID].length; index++) {
	        if (this.transactions[shopAssistantID][index] == null) {
	        	Transaction copyTransaction = new Transaction(transaction);
	            this.transactions[shopAssistantID][index] = transaction;
	            setTransactionFee(transaction);
	            break;
	        }
	    }
	}
	
	public void setCommission(ShopAssistant shopAssistant) {
		int index = shopAssistant.getID();
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
	
	public int getShopAssistantCapacity() {
		return this.shopAssistantCapacity;
	}
	
	public int getTransactionCapacity() {
		return this.transactionCapacity;
	}
