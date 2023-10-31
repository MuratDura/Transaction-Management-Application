import transactionManagement.ShopAssistant;

public class SalaryManagement {
    // Variable defining the initial capacity
    private int capacity = 30;
    // Index variable to track the number of ShopAssistant added so far
    private int index = 0;
    // Array to store ShopAssistant objects
    private ShopAssistant[] salaryManager;

    // Constructor method: creates an array with the initial capacity
    public SalaryManagement() {
        salaryManager = new ShopAssistant[capacity];
    }

    // Method to add a new ShopAssistant object
    public void AddAssistant(ShopAssistant assistant) {
        // Increase the capacity if the array is full
        if (salaryManager.length == capacity) {
            salaryManager = increaseCapacity(salaryManager);
        }
        // Throw an error if the incoming object is null or of the wrong type
        if (assistant == null) {
            throw new IllegalArgumentException("Shop assistant cannot be null and must be of type \"ShopAssistant\"");
        }
        // Add the incoming ShopAssistant object to the array and increment the index
        this.salaryManager[index] = assistant;
        index++;
    }

    // Helper method to double the capacity of the array
    private ShopAssistant[] increaseCapacity(ShopAssistant[] salaryManager) {
        // Create a temporary array with the new capacity
        ShopAssistant[] temp = new ShopAssistant[salaryManager.length * 2];
        // Copy current elements to the new array
        for (int i = 0; i < salaryManager.length; i++)
            temp[i] = salaryManager[i];
        // Update the capacity
        capacity = capacity * 2;
        // Return the reference to the expanded array
        return temp;
    }

    // Method to return a string representation of the class
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        int count = 0;
        // Loop over the array, adding filled elements to the string
        for (ShopAssistant assistant : salaryManager) {
            if (count >= index) {
                break;
            }
            sb.append(assistant.toString());
            if (count != index - 1) {
                sb.append(",\n ");  // Add a new line and comma after each element
            }
            count++;
        }
        return sb.toString();
    }
}