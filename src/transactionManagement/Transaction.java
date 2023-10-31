package transactionManagement;

import java.util.Arrays;
import helper.FileIO;
import java.util.Random;

public class Transaction {
	
	private int transactionID;
	private Product[] products;
	private double totalPrice;
	private double transactionFee;
	
	private Random random = new Random();
	private static final int LOWER_BOUND_QUANTITY = 1;
	private static final int UPPER_BOUND_QUANTITY = 10;
	private static int COUNTER = 0; //to help assign transaction id
	private int size = 0; 
	private static final int NUMBER_OF_PRODUCTS = 3;
	
	public Transaction(Product[] products) {
		if (products == null) {
			throw new IllegalArgumentException("The product array cannot be null.");
		}
		this.transactionID = COUNTER++;
		this.products = new Product[NUMBER_OF_PRODUCTS];
		for (int index = 0; index < this.products.length; index++) {
			addProduct(products);
		}
	}
	
	//copy constructor
	public Transaction(Transaction source) {
		this.transactionID = source.transactionID;
		this.products = copyTransactionArray(source.products);
		this.totalPrice = source.totalPrice;
		this.transactionFee = source.transactionFee;
	}
	
	/**addProductsFrom
	 * adds a random product with a random quantity to the transaction from the array of products passed in  
	 * @param products
	 */
	private void addProduct(Product[] products) {
		
		if (size == NUMBER_OF_PRODUCTS) {
			System.out.printf("Transaction limited to %d products.", NUMBER_OF_PRODUCTS);
			return;
		}
		int randid = getRandomProductId(products.length);
		//System.out.printf("\n************random product index: %d", randid);
		Product randomProduct = new Product(products[randid]);
		this.products[size++] = randomProduct;
		setRandomQuantity(randomProduct);
		this.totalPrice += randomProduct.getPrice();
		
	}
	
	public void setTransactionFee(int transactionFeePercentage) {
		if (transactionFee < 0) {
			throw new IllegalArgumentException("Transaction fee cannot be less than 0.");
		}
		this.transactionFee = this.totalPrice * transactionFeePercentage / 100;
	}
	
	@Override
	public String toString() {
		String info = "Transaction: " + transactionID + "\nProducts: \n";
		for (int i = 0; i < this.products.length; i++) {
			info += "\t" + this.products[i].toString() + "\n";
		}
		info += "Total Price: " + totalPrice + "\nTransaction Fee = " + transactionFee + "\n";
		return info;
	}
	
	private Product[] copyTransactionArray(Product[] products) {
		Product[] newArr = new Product[products.length];
		for (int index = 0; index < products.length; index++) {
			newArr[index] = new Product(products[index]);
		}return newArr;
	}
	
	private int getRandomProductId(int bound) {
		int randomId = random.nextInt(bound);
		return randomId;
	}
	
	private void setRandomQuantity(Product product) {
		int randomQuantity = random.nextInt(LOWER_BOUND_QUANTITY, UPPER_BOUND_QUANTITY+1);
		product.setQuantity(randomQuantity);
	}

	public double getTotalPrice() {
		return this.totalPrice;
	}
	
}
