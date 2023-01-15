import java.io.*;
import java.util.ArrayList;
@SuppressWarnings("unchecked")

public class VendingMachine {
    static MoneyMachine m;
    static ProductMachine p;

    public VendingMachine( ProductMachine p, MoneyMachine m){
        VendingMachine.m = m;
        VendingMachine.p = p;
    }

    public static void saveMachine(VendingMachine vm, String fname) throws IOException {
        File file = new File(fname);
        if(file.createNewFile()) {
            FileOutputStream outFileStream = new FileOutputStream(file);
            ObjectOutputStream outObjectStream = new ObjectOutputStream(outFileStream);
            outObjectStream.writeObject(VendingMachine.m);
            outObjectStream.writeObject(VendingMachine.p);
            outObjectStream.writeObject(VendingMachine.m.elements);
            outObjectStream.writeObject(VendingMachine.p.elements);
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
        return VendingMachine.p;
    }

    public void setProductMachine(ProductMachine p){
        VendingMachine.p = p;
    }

    public MoneyMachine getMoneyMachine(){
        return VendingMachine.m;
    }

    public void setMoneyMachine(MoneyMachine m){
        VendingMachine.m = m;
    }
}
