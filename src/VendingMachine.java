import java.io.*;

public class VendingMachine {
    static MoneyMachine m;
    static ProductMachine p;

    public VendingMachine( ProductMachine p, MoneyMachine m){
        VendingMachine.m = m;
        VendingMachine.p = p;
    }

    public static void saveMachine(VendingMachine vm, String fname) throws IOException {
        File file = new File(fname);
        file.createNewFile();
        FileOutputStream outFileStream = new FileOutputStream(file);
        ObjectOutputStream outObjectStream = new ObjectOutputStream(outFileStream);
        outObjectStream.writeObject(m);
        outObjectStream.writeObject(p);
    }

    public static VendingMachine restoreMachine(String fname) throws IOException, ClassNotFoundException {
        File inFile = new File(fname);
        FileInputStream inFileStream = new FileInputStream(inFile);
        ObjectInputStream inObjectStream = new ObjectInputStream(inFileStream);
        MoneyMachine mm = (MoneyMachine) inObjectStream.readObject();
        ProductMachine pm = (ProductMachine) inObjectStream.readObject();
        VendingMachine vm = new VendingMachine(pm,mm);
        return vm;
    }

    public ProductMachine getProductMachine(){
        return p;
    }

    public void setProductMachine(ProductMachine p){
        VendingMachine.p = p;
    }

    public MoneyMachine getMoneyMachine(){
        return m;
    }

    public void setMoneyMachine(MoneyMachine m){
        VendingMachine.m = m;
    }
}
