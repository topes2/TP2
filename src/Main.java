import java.text.DecimalFormat;
import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;

public class Main {

    public static void main(String[] args) throws Exception {
        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
        DecimalFormat df = new DecimalFormat("0.00");

        ProductMachine pm = new ProductMachine();
        pm.addProduct(10, new Perishable("gomas", (float)1.25 , sdformat.parse("2022-02-13")));
        pm.addProduct(10, new Perishable("batatas", (float)1.60 , sdformat.parse("2022-11-27")));
        pm.addProduct(10, new Perishable("merenda mista", (float)1.25 , sdformat.parse("2022-01-25")));
        pm.addProduct(10, new NonPerishable("pastilhas", (float) 0.95,0.075));

        MoneyMachine mm = new MoneyMachine();

        mm.addThings(10,1);
        mm.addThings(10,(float)0.1);
        mm.addThings(10,(float)0.2);
        mm.addThings(10,(float)0.5);
        mm.addThings(10,(float)0.05);
        mm.addThings(10,2);

        VendingMachine vm1 = new VendingMachine(pm,mm);

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
                                        case "seeTotal" -> System.out.println(df.format(vm1.getMoneyMachine().getTotalValue()));
                                        case "seeMoneyToCollect" -> System.out.println(df.format(vm1.getMoneyMachine().MoneyToCollect()));
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
                                            float cost = sc.nextFloat();
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




