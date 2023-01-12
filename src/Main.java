import java.util.Date;

public class Main {

    public static void main(String[] args){

        try {
            MoneyMachine mm = new MoneyMachine();
            mm.addThings(10, 2.0f);
            mm.addThings(10, 0.5f);
            mm.addThings(10, 1f);
            mm.addThings(10, 0.5f);
            mm.listAll();
            System.out.println("Ordered");
            System.out.println(mm.getTotalValue());
        } catch (Exception e) {

            System.out.println("Exception");
        }
    }
}


