import transactionManagement.Transaction;
import transactionManagement.Product;
import transactionManagement.ShopAssistant;


public class Query {

	// Query 1
	public static Transaction findHighestTotalPriceTransaction(TransactionManagement transactionManagement) {
        Transaction highestTotalPriceTransaction=null;
        double maxTotalPrice=Double.MIN_VALUE;

        Transaction[][] transactions=transactionManagement.getTransactions();

        for (int i=0; i<=transactionManagement.getShopAssistantSize(); i++) {
            int j=0;
            while (transactions[i][j]!=null) {
                Transaction transaction= transactions[i][j];
                if (transaction.getTotalPrice() > maxTotalPrice) {
                    maxTotalPrice= transaction.getTotalPrice();
                    highestTotalPriceTransaction= transaction;
                }
                j++;
            }
        }

        return highestTotalPriceTransaction;
    }
	
	// Query 2
    public static Product findMostExpensiveProductInLowestPriceTransaction(TransactionManagement transactionManagement) {
        Product mostExpensiveProduct=null;
        double minTotalPrice= Double.MAX_VALUE;

        
        Transaction[][] transactions=transactionManagement.getTransactions();

        
        for (int i=0; i<=transactionManagement.getShopAssistantSize(); i++) {
            int j=0;
            while (transactions[i][j]!=null) {
                Transaction transaction=transactions[i][j];
                if (transaction.getTotalPrice() < minTotalPrice) {
                    minTotalPrice=transaction.getTotalPrice();
                    Product[] products=transaction.getProducts();
                    mostExpensiveProduct=findMostExpensiveProduct(products);
                }
                		j++;
            }
        }

        return mostExpensiveProduct;
    }
	
    // Query 3
    public static Transaction findLowestTransactionFee(TransactionManagement transactionManagement) {
        Transaction lowestTransactionFeeTransaction=null;
        double minTransactionFee=Double.MAX_VALUE;

        Transaction[][] transactions=transactionManagement.getTransactions();

        for (int i=0; i <=transactionManagement.getShopAssistantSize(); i++) {
            int j=0;
            while (transactions[i][j]!=null) {
                Transaction transaction=transactions[i][j];
                if (transaction.getTransactionFee() < minTransactionFee) {
                    minTransactionFee = transaction.getTransactionFee();
                    lowestTransactionFeeTransaction=transaction;
                }
                j++;
            }
        }

        return lowestTransactionFeeTransaction;
    }
    
    // Query 4 
    public static ShopAssistant findHighestSalaryShopAssistant(TransactionManagement transactionManagement) {
        ShopAssistant highestSalaryShopAssistant = null;
        double maxTotalSalary=Double.MIN_VALUE;

        for (int i=0; i<=transactionManagement.getShopAssistantSize(); i++) {
            ShopAssistant shopAssistant=transactionManagement.getTransactions()[i][0].getProducts()[0].getShopAssistant();
            transactionManagement.setCommission(shopAssistant);
            double totalSalary=shopAssistant.getTotalSalary();
            if (totalSalary > maxTotalSalary) {
                maxTotalSalary=totalSalary;
                highestSalaryShopAssistant=shopAssistant;
            }
        }

        return highestSalaryShopAssistant;
    }

    // Query 5
    public static double calculateTotalRevenue(TransactionManagement transactionManagement) {
        double totalRevenue=0;

        for (int i=0; i<=transactionManagement.getShopAssistantSize(); i++) {
            int j=0;
            while (transactionManagement.getTransactions()[i][j]!=null) {
                Transaction transaction= transactionManagement.getTransactions()[i][j];
                totalRevenue+=transaction.getTotalPrice() +transaction.getTransactionFee();
                j++;
            }
        }

        return totalRevenue;
    }
    
    // Query 6
    public static double calculateTotalProfit(TransactionManagement transactionManagement) {
        double totalProfit=0;

        for (int i=0; i<=transactionManagement.getShopAssistantSize(); i++) {
            ShopAssistant shopAssistant=transactionManagement.getTransactions()[i][0].getProducts()[0].getShopAssistant();
            transactionManagement.setCommission(shopAssistant);
            double totalSalary=shopAssistant.getTotalSalary();

            int j=0;
            while (transactionManagement.getTransactions()[i][j]!=null) {
                Transaction transaction=transactionManagement.getTransactions()[i][j];
                double transactionCost=transaction.getTotalPrice() + transaction.getTransactionFee();
                totalProfit+= transactionCost-totalSalary;
                j++;
            }
        }

        return totalProfit;
    }

    	// Helper method for finding the most expensive product
    private static Product findMostExpensiveProduct(Product[] products) {
        Product mostExpensiveProduct=null;
        double maxPrice=Double.MIN_VALUE;

        for (Product product : products) {
            if (product.getPrice() > maxPrice) {
                maxPrice=product.getPrice();
                mostExpensiveProduct=product;
            }
        }

        return mostExpensiveProduct;
    }
}