import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

@SuppressWarnings("unchecked")

public class VendingMachine {
    private static MoneyMachine mm;
    private static ProductMachine pm;

    public VendingMachine( ProductMachine p, MoneyMachine m){
        VendingMachine.mm = m;
        VendingMachine.pm = p;
    }

    public static void saveMachine(VendingMachine vm, String fname) throws IOException {
        File file = new File(fname);
        if(file.createNewFile()) {
            FileOutputStream outFileStream = new FileOutputStream(file);
            ObjectOutputStream outObjectStream = new ObjectOutputStream(outFileStream);
            outObjectStream.writeObject(VendingMachine.mm);
            outObjectStream.writeObject(VendingMachine.pm);
            outObjectStream.writeObject(VendingMachine.mm.elements);
            outObjectStream.writeObject(VendingMachine.pm.elements);
        }

    }

    public static VendingMachine restoreMachine(String fname) throws IOException, ClassNotFoundException {
        File inFile = new File(fname);
        FileInputStream inFileStream = new FileInputStream(inFile);
        ObjectInputStream inObjectStream = new ObjectInputStream(inFileStream);
        MoneyMachine mm = (MoneyMachine) inObjectStream.readObject();
        ProductMachine pm = (ProductMachine) inObjectStream.readObject();
        mm.elements = (ArrayList<Element<Float>>)  inObjectStream.readObject();
        pm.elements = (ArrayList<Element<Product>>) inObjectStream.readObject();
        return new VendingMachine(pm,mm);

    }

    public boolean workinkLoop(){

        DecimalFormat df = new DecimalFormat("0.00");
        Scanner sc = new Scanner(System.in);

        System.out.println (    "##############################################\n"+
                                "|               Instruões                    |\n" +
                                "|Intoduza o saldo                            |\n"+
                                "|Introduza o nome do produto desejado        |\n"+
                                "|Se desejar canselar a operaçao digite       |\n"+
                                "|cancelar                                    |\n"+
                                "|                                            |\n"+
                                "|Moedas aceites: 0.05€ , 0.10€ , 0.20€,      |\n"+
                                "|0.50€ , 1€ , 2€                             |\n"+
                                "##############################################" );




        while (true) {

            pm.listForClient();

            while (true) {
                System.out.println("[Saldo:" + df.format(mm.getCb().getSaldo()) + "€]");
                String input = sc.nextLine();

                try {
                    float mon = Float.parseFloat(input);
                    mm.getCb().AddCoin(mon, 1);
                } catch (Exception e) {

                    switch (input) {

                        case "sudo":
                            adminMode();
                            break;
                        case "shutDown" :
                            System.out.println("Shuting Down...");
                            return true;
                        case "memMode":
                            return false;
                        case "cancelar":
                            mm.giveChange(0);
                            break;
                        default:
                            requestProduct(input);
                            break;
                    }
                    break;
                }

            }
        }

    }

    public ProductMachine getProductMachine(){
        return VendingMachine.pm;
    }

    public void setProductMachine(ProductMachine p){
        VendingMachine.pm = p;
    }

    public MoneyMachine getMoneyMachine(){
        return VendingMachine.mm;
    }

    public void setMoneyMachine(MoneyMachine m){
        VendingMachine.mm = m;
    }

    public Product requestProduct(String input){

        if (pm.hasProduct(pm.GetProduct(input))) {

            Product p1 = pm.GetProduct(input);
            if (mm.getCb().getSaldo() >= p1.getCost()) {
                mm.giveChange((float) p1.getCost());
                System.out.println("Parabens compraste " + p1.getName() + "\n\n\n");
                pm.removeOneThing(p1);
                return p1;
            }
            else{
                System.out.println("Saldo insuficinte");
            }
        }else
        {
            System.out.println("Produto em falta");
        }

        return null;
    }

    public void adminMode() {
        int qtd;
        String nome;
        float cost;
        Product product;
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
        DecimalFormat df = new DecimalFormat("0.00");

        System.out.println("[-------------Entering Admim Mode-------------]");

        while (true) {

            try {
                String comand = sc.nextLine();
                switch (comand) {
                    case "seeTotal":
                        System.out.println(df.format(mm.getTotalValue()));
                        break;
                    case "seeMoneyToCollect" :
                        System.out.println(df.format(mm.MoneyToCollect()));
                        break;
                    case "seeMoney" :
                        mm.listAll();
                        break;
                    case "addMoney" :
                        mm.addThings(sc.nextInt(), sc.nextFloat());
                        break;
                    case "addPerishable":
                        qtd = sc.nextInt();
                        sc.nextLine();
                        nome = sc.nextLine();
                        cost = sc.nextFloat();
                        sc.nextLine();
                        Date limitdate = sdformat.parse(sc.nextLine());
                        product = new Perishable(nome, cost, limitdate);
                        pm.addProduct(qtd, product);
                        break;
                    case "addNonPerishable" :
                        qtd = sc.nextInt();
                        sc.nextLine();
                        nome = sc.nextLine();
                        cost = sc.nextFloat();
                        double volume = sc.nextDouble();
                        product = new NonPerishable(nome, cost, volume);
                        pm.addProduct(qtd, product);
                        break;
                    case "productList" :
                        pm.listAllOrdered();
                        break;
                    case "Q" :
                        System.out.println("[--------------Leaving Admim Mode-------------]");
                        return;

                }
            } catch (Exception x) {
                System.out.println(x.getMessage());
            }

        }
    }
}
