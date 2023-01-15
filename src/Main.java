import java.text.DecimalFormat;
import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;

public class Main {

    public static void main(String[] args) throws Exception {
        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
        DecimalFormat df = new DecimalFormat("0.00");
        VendingMachine vm1 = VendingMachine.restoreMachine("test.dat");
        MoneyMachine mm = vm1.getMoneyMachine();
        ProductMachine pm = vm1.getProductMachine();

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
            Scanner sc = new Scanner(System.in);
            vm1.getProductMachine().listForClient();
            System.out.println("Moedas aceites: 0.05€ , 0.10€ , 0.20€ , 0.50€ , 1€ , 2€");

                while (true) {
                    System.out.println("[Saldo:" + df.format(mm.getCb().getSaldo()) + "€]");
                    String input = sc.nextLine();
                    try {
                        float mon = Float.parseFloat(input);
                        mm.getCb().AddCoin(mon, 1);
                    } catch (Exception e) {


                        if (input.equals("sudo")) {

                            boolean quit = false;
                            while (!quit) {

                                try {
                                    String comand = sc.nextLine();
                                    switch (comand) {
                                        case "seeMoney" -> vm1.getMoneyMachine().listAll();
                                        case "addMoney" -> vm1.getMoneyMachine().addThings(sc.nextInt(), sc.nextFloat());
                                        case "addPerishable" -> {
                                            int qtd = sc.nextInt();
                                            sc.nextLine();
                                            String nome = sc.nextLine();
                                            float cost = sc.nextFloat();
                                            sc.nextLine();
                                            Date limitdate = sdformat.parse(sc.nextLine());
                                            Perishable product = new Perishable(nome, cost, limitdate);
                                            vm1.getProductMachine().addProduct( qtd , product);
                                            }
                                        case "addNonPerishable" -> {
                                            int qtd = sc.nextInt();
                                            sc.nextLine();
                                            String nome = sc.nextLine();
                                            double cost = sc.nextDouble();
                                            double volume = sc.nextDouble();
                                            NonPerishable product = new NonPerishable(nome, cost,volume);
                                            vm1.getProductMachine().addProduct( qtd , product);
                                        }
                                        case "productList" -> vm1.getProductMachine().listAllOrdered();
                                        case "saveMachine" -> VendingMachine.saveMachine(vm1, sc.nextLine());
                                        case "loadMachine" -> vm1 = VendingMachine.restoreMachine(sc.nextLine());
                                        case "Q" -> quit = true;
                                        case "shutDown" -> {
                                            return;
                                        }


                                    }
                                }catch (Exception x){
                                    System.out.println(x.getMessage());
                                }
                            }

                        }
                        else if (input.equals("cancelar")) {
                            mm.giveChange(0);
                            break;
                        }

                        if (pm.hasProduct(pm.GetProduct(input))) {

                            Product p1 = pm.GetProduct(input);
                            if (mm.getCb().getSaldo() >= p1.getCost()) {
                                mm.giveChange((float) p1.getCost());
                                System.out.println("Parabens compraste " + p1.getName() + "\n\n\n");
                                pm.removeOneThing(p1);
                                break;
                            }
                            else{
                                System.out.println("Saldo insuficinte");
                            }
                        }else
                        {
                            System.out.println("Produto em falta");
                        }


                    }
                }
            }
        }
    }




