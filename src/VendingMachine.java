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

        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
        DecimalFormat df = new DecimalFormat("0.00");

        System.out.println("[-------------Entering Admim Mode-------------]");

        while (true) {

            try {
                String comand = sc.nextLine();
                switch (comand) {
                    case "seeTotal" -> System.out.println(df.format(mm.getTotalValue()));
                    case "seeMoneyToCollect" -> System.out.println(df.format(mm.MoneyToCollect()));
                    case "seeMoney" -> mm.listAll();
                    case "addMoney" -> mm.addThings(sc.nextInt(), sc.nextFloat());
                    case "addPerishable" -> {
                        int qtd = sc.nextInt();
                        sc.nextLine();
                        String nome = sc.nextLine();
                        float cost = sc.nextFloat();
                        sc.nextLine();
                        Date limitdate = sdformat.parse(sc.nextLine());
                        Perishable product = new Perishable(nome, cost, limitdate);
                        pm.addProduct(qtd, product);
                    }
                    case "addNonPerishable" -> {
                        int qtd = sc.nextInt();
                        sc.nextLine();
                        String nome = sc.nextLine();
                        float cost = sc.nextFloat();
                        double volume = sc.nextDouble();
                        NonPerishable product = new NonPerishable(nome, cost, volume);
                        pm.addProduct(qtd, product);
                    }
                    case "productList" -> pm.listAllOrdered();
                    case "Q" -> {
                        System.out.println("[--------------Leaving Admim Mode-------------]");
                        return;
                    }

                }
            } catch (Exception x) {
                System.out.println(x.getMessage());
            }

        }
    }
}
