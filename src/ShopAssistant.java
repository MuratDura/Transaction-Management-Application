import java.util.Random;
public class ShopAssistant {
    private final int  ID;
    private final String name;
    private final String surname;
    private final String phone_number;
    private int upper_limit = 15;  //the maximum years of experience a person in this company has.
    private int seniority;
    private int junior_boundry = 1; //Experience required to become mid level (in years)
    private int mid_boundry = 3;  //Experience required to become a senior level (in years)
    private int senior_boundry = 5; //Experience required to become a expert level (in years)
    private int junior_salary = 1500;
    private int mid_salary = 2000;
    private int senior_salary = 2500;
    private int expert_salary = 3000;
    private Random rand;
    private int commission = 0;
    private int totalSalary=0;
    private int weekly_salary;
    private int bonus;
    public ShopAssistant(int ID,String name,String surname,String phone_number){
        this.ID=ID;
        this.name=name;
        this.surname=surname;
        this.phone_number=phone_number;
        this.seniority=create_seniority();
        this.weekly_salary=calc_weekly_sal_basis();
    }
    private int create_seniority(){
        return rand.nextInt(upper_limit+1);
    }
    public String toString(){
        return (ID + name + surname + seniority + phone_number + weekly_salary + commission + totalSalary);
    }
    private int calc_weekly_sal_basis(){
        if(seniority<junior_boundry){return junior_salary;}
        else if (seniority<mid_boundry) {return mid_salary;}
        else if (seniority<senior_boundry) {return senior_salary;}
        else {return expert_salary;}
    }

    public void addCommission(int commission) {
        if (commission<0){
            System.out.println("FATAL ERROR: Commission can not be negative");
        }
        else {this.commission += commission;}
}
    public void setTotalSalary(){
        totalSalary = (weekly_salary*4)+commission+bonus;
    }

    public void setBonus(int bonus) {
        if(bonus<0){
            System.out.println("FATAL ERROR: Bonus can not be negative");
        }
        else{this.bonus = bonus;}
    }
}

