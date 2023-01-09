import java.util.HashMap;

public class ProductMachine {

    HashMap<String, Integer> products;


    public ProductMachine(){

        this.products = new HashMap<>();

    }

    public boolean hasProduct(Product m){

        return products.get(m.name) != null;

    }
    public void addProduct(int n, Product m){

        products.put(m.name , n);

    }
}
