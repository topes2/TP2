import java.util.Date;

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
        vm1.getProductMachine().listAll();
    }
}


