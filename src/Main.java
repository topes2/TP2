import java.util.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        ProductMachine pm = new ProductMachine();
        pm.addProduct(10,new NonPerishable("lube",10,13.2));
        pm.addProduct(10,new NonPerishable("goo",10,13.2));
        pm.addProduct(10,new NonPerishable("pixel7",10,13.2));
        pm.addProduct(10,new Perishable("cake",1.7f,new Date()));
        pm.addProduct(10,new Perishable("weed",1.8f,new Date()));
        pm.addProduct(10,new Perishable("foam",1.2f,new Date()));
        MoneyMachine mm = new MoneyMachine();
        VendingMachine vm1 = new VendingMachine(pm, mm);
        while (true){
            CoinBuffer CB = new CoinBuffer();
            Scanner sc = new Scanner(System.in);
            vm1.getProductMachine().listAll();
            System.out.println("Deseja efetar uma compra? \nSim     Não");
            String temp = sc.nextLine().toUpperCase();

            if(temp.equals("SIM")){ //TODO: O Loop esta mais ou menos com a framework feita mas de resto tens de acabar a parte de fazer return de um product tipo o proximo todo
                while(true){
                    String input = sc.nextLine();
                    try{
                        float mon = Float.parseFloat(input);
                        CB.AddCoin(mon,1);
                    }catch (Exception e){
                        if( pm.hasProduct(pm.GetProduct(input))){ // TODO:nao esta a devolver um producto na parte de getProduct e nao da para poder usar o elements
                            Product p1 = pm.GetProduct(input);
                            if(CB.totalbag() == p1.getCost()){
                                System.out.println("Parabens compraste "+ p1.getName());
                                pm.removeOneThing(p1);
                            }
                        }

                    }
                }
            }
            CB.reset();
        }
    }
}


