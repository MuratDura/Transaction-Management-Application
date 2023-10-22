public class Main {
    public static void main(String[] args) {
        ShopAssistant[] myShopAssistantArray = FileIO.createShopAssistantArrayByFile("shopAssistants.csv");
		System.out.println(myShopAssistantArray[99]); 

    }
}
