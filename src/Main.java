import java.text.DecimalFormat;
import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;

public class Main {

    public static void main(String[] args) throws Exception {

        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
        Scanner sc = new Scanner(System.in);
        VendingMachine vm;
        Boolean shutDown = false;

        System.out.println("[Restore Vending Machinhe from file?]");
        if (sc.nextLine().equals("s")){
            System.out.print("[Enter file name:");
            vm = VendingMachine.restoreMachine(sc.nextLine());
        }
        else {

            ProductMachine pm = new ProductMachine();
            pm.addProduct(10, new Perishable("gomas", (float) 1.25, sdformat.parse("2022-02-13")));
            pm.addProduct(10, new Perishable("batatas", (float) 1.60, sdformat.parse("2022-11-27")));
            pm.addProduct(10, new Perishable("merenda mista", (float) 1.25, sdformat.parse("2022-01-25")));
            pm.addProduct(10, new NonPerishable("pastilhas", (float) 0.95, 0.075));

            MoneyMachine mm = new MoneyMachine();
            mm.addThings(10, 1);
            mm.addThings(10, (float) 0.1);
            mm.addThings(10, (float) 0.2);
            mm.addThings(10, (float) 0.5);
            mm.addThings(10, (float) 0.05);
            mm.addThings(10, 2);

            vm = new VendingMachine(pm, mm);
        }




        while (!shutDown){

            shutDown = vm.workinkLoop();

            if(!shutDown) {
                System.out.println("[Load or Save machinhe?]");
                switch (sc.nextLine()){
                    case "saveMachine":
                        VendingMachine.saveMachine(vm, sc.nextLine());
                        break;
                    case "loadMachine":
                        vm = VendingMachine.restoreMachine(sc.nextLine());
                        break;
                    default:
                        break;
                }
            }

        }







    }
}





