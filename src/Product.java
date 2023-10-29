package transactionManagement;

public class Product {
	
	private final int ID;
	private final String name;
	private double price;
	private int quantity;
	
	// No-arg constructor
	public Product() {
		this.ID = 0;
		this.name = "";
		this.price = 0.0;
		this.quantity = 0;
	}
	// Parametrized constructor
	public Product(int ID, String name, double price) {
		this.ID = ID;
		this.name = name;
		this.price = price;
		this.quantity = 1;
	}
	// Copy constructor
	public Product(Product product) {
		this.ID = product.getID();
		this.name = product.getName();
		this.price = product.getPrice();
		this.quantity = product.getQuantity();
	}
	
	public int getID() {
		return ID;
	}
	public String getName() {
		return name;
	}
	public double getPrice() {
		return price*quantity;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String toString() {
		return String.valueOf(ID) + " " + name + " " + String.valueOf(price) + " " + String.valueOf(quantity);
	}
}
