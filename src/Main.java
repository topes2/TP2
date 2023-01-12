import java.util.Date;

public class Main {

    public static void main(String[] args){

        try {
            MoneyMachine mm1 = new MoneyMachine();
            mm1.addMoney(10, 0.5f);
            mm1.addMoney(10, 1.0f);
            mm1.addMoney(10, 0.1f);

            ProductMachine pm1 = new ProductMachine();
            pm1.addProduct(5, new NonPerishable("Lotion", 2.55, 3.4));

            VendingMachine vm1 = new VendingMachine(pm1, mm1);

            VendingMachine.saveMachine(vm1, "file.dat");

            vm1.getMoneyMachine().addMoney(10, 2f);

            System.out.println("Money machine of vending machine 1: listaAll");
            vm1.getMoneyMachine().listAll();

            VendingMachine vm2 = VendingMachine.restoreMachine("file.dat");

            System.out.println("Money machine of vending machine 1: listaAll");
            vm2.getMoneyMachine().listAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}


