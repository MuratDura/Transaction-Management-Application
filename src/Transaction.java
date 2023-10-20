package project;
import java.util.Arrays;
import helper.FileIO;
import java.util.Random;
public class Transaction {
	
	private int transactionID;
	private Product[] products;
	private int totalPrice;
	private int transactionFee;
	
	private Random random = new Random();
	private static final int LOWER_BOUND_QUANTITY = 1;
	private static final int UPPER_BOUND_QUANTITY = 10;
	private static int COUNTER;
	private int size = 0; 
	private static final int NUMBER_OF_PRODUCTS = 3;
	private FileIO fileIO = new FileIO();
	
	
	public Transaction() {
		this.products = new Product[NUMBER_OF_PRODUCTS];
		for (int index = 0; index < this.products.length; index++) {
			int quantity = random.nextInt(LOWER_BOUND_QUANTITY, UPPER_BOUND_QUANTITY + 1);
			addProduct(fileIO.getRandomProduct(), quantity);
		}
	}
	
	private void addProduct(Product product, int quantity) {
		if (size == NUMBER_OF_PRODUCTS) {
			System.out.printf("Transactions limited to %d products.", NUMBER_OF_PRODUCTS);
			return;
		}
		if (product == null || !(product instanceof Product)) {
			throw new IllegalArgumentException("Product cannot be null and must be of type \"Product\"");
		}
		if (!(1 <= quantity && quantity <= 10)) {
			throw new IllegalArgumentException("Quantity must be between 1 and 10.");
		}
		this.products[size++] = product;
		this.totalPrice += product.getPrice()*quantity;
	}
	
	public void setTransactionFee(int transactionFee) {
		if (transactionFee < 0) {
			throw new IllegalArgumentException("Transaction fee cannot be less than 0.");
		}
	}
	
	@Override
	public String toString() {
		return "Transaction [transactionID=" + transactionID + ", products=" + Arrays.toString(products)
				+ ", totalPrice=" + totalPrice + ", transactionFee=" + transactionFee  + ", size="
				+ size + "]";
	}

	public static void main(String[] args) {
		Transaction transaction = new Transaction();
		System.out.println(transaction);
	}

}