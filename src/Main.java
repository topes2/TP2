import java.text.DecimalFormat;
import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;

public class Main {

    public static void main(String[] args) throws Exception {

        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
        DecimalFormat df = new DecimalFormat("0.00");
        Scanner sc = new Scanner(System.in);
        VendingMachine vm;


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

        System.out.println("""
                //////////////////////////////////////////////
                |               Instruões                    |
                |Intoduza o saldo                            |
                |Introduza o nome do produto desejado        |
                |Se desejar canselar a operaçao digite       |
                |cancelar                                    |
                |                                            |
                |Moedas aceites: 0.05€ , 0.10€ , 0.20€,      |
                |0.50€ , 1€ , 2€                             |
                //////////////////////////////////////////////
                """);



        while (true) {

            vm.getProductMachine().listForClient();

            while (true) {
                System.out.println("[Saldo:" + df.format(vm.getMoneyMachine().getCb().getSaldo()) + "€]");
                String input = sc.nextLine();

                try {
                    float mon = Float.parseFloat(input);
                    vm.getMoneyMachine().getCb().AddCoin(mon, 1);
                } catch (Exception e) {

                    switch (input) {

                        case "sudo":
                            vm.adminMode();
                            break;
                        case "saveMachine":
                            VendingMachine.saveMachine(vm, sc.nextLine());
                            break;
                        case "loadMachine":
                            vm = VendingMachine.restoreMachine(sc.nextLine());
                            break;
                        case "shutDown" :
                            System.out.println("Shuting Down...");
                            return;
                        case "cancelar":
                            vm.getMoneyMachine().giveChange(0);
                            break;
                        default:
                            vm.requestProduct(input);
                            break;
                    }
                }

            }
        }
    }
}





