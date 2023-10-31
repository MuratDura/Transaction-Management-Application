package transactionManagement;

import java.util.Random;

public class ShopAssistant {
    // Class properties (attributes)
    private final int ID;
    private final String name;
    private final String surname;
    private final String phoneNumber;

    // Constants related to seniority and salaries
    private static final int UPPER_LIMIT = 15;
    private int seniority;
    private static final int JUNIOR_BOUNDARY = 1;
    private static final int MID_BOUNDARY = 3;
    private static final int SENIOR_BOUNDARY = 5;
    private static final int JUNIOR_SALARY = 1500;
    private static final int MID_SALARY = 2000;
    private static final int SENIOR_SALARY = 2500;
    private static final int EXPERT_SALARY = 3000;

    // Utility objects and additional properties
    private final Random rand = new Random();
    private int commission = 0;
    private int bonus = 0;
    private int weeklySalary;
    private int totalSalary = (weeklySalary * 4) + commission + bonus;;



    // Constructor for ShopAssistant
    public ShopAssistant(int ID, String name, String surname, String phoneNumber) {
        // Phone number validation
        if (phoneNumber.length() != 8 || phoneNumber.charAt(3) != '-') {
            throw new IllegalArgumentException("Invalid phone number format. Expected format: XXX-YYYY");
        }
        for (int i = 0; i < phoneNumber.length(); i++) {
            if (i != 3 && !Character.isDigit(phoneNumber.charAt(i))) {
                throw new IllegalArgumentException("Invalid phone number format. Expected format: XXX-YYYY");
            }
        }
        // Initializing class properties
        this.ID = ID;
        this.name = capitalizeFirstLetter(name);
        this.surname = capitalizeFirstLetter(surname);
        this.phoneNumber = phoneNumber;
        this.seniority = createSeniority();
        this.weeklySalary = calcWeeklySalaryBasis();
    }

    // Helper function to generate a random seniority value
    private int createSeniority() {
        return rand.nextInt(UPPER_LIMIT + 1);
    }

    // Override toString method for proper representation of the object
    @Override
    public String toString() {
        return ID +" " +name + " " +surname + " " + seniority + " " + phoneNumber + " " + weeklySalary + " " + commission + " " + totalSalary;
    }

    // Calculate weekly salary based on seniority
    private int calcWeeklySalaryBasis() {
        if (seniority < JUNIOR_BOUNDARY) {
            return JUNIOR_SALARY;
        } else if (seniority < MID_BOUNDARY) {
            return MID_SALARY;
        } else if (seniority < SENIOR_BOUNDARY) {
            return SENIOR_SALARY;
        } else {
            return EXPERT_SALARY;
        }
    }

    // Add commission to the employee
    public void addCommission(int commission) {
        if (commission < 0) {
            System.out.println("FATAL ERROR: Commission can not be negative");
        } else {
            this.commission += commission;
        }
    }
    
    public int getID() {
    	return this.ID;
    }

    public void setTotalSalary() {
        totalSalary = (weeklySalary * 4) + commission + bonus;
    }

    // Set a bonus for the employee
    public void setBonus(int bonus) {
        if (bonus < 0) {
            System.out.println("FATAL ERROR: Bonus can not be negative");
        } else {
            this.bonus = bonus;
        }
    }

    // Helper function to capitalize the first letter of a string
    private String capitalizeFirstLetter(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }
}
