import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;

public class ProductMachine {

    HashMap<String, Deque<Product>> products;
    ArrayList<Product> productList;


    public ProductMachine(){

        products = new HashMap<>();
        productList = new ArrayList<>();
    }

    public boolean hasProduct(Product m){

        return products.get(m.name).peekFirst() != null;

    }
    public void addProduct(int n, Product m) {

        if (products.get(m.name)!= null) {
            for (int i = 0; i < n; i++) {
                products.get(m.name).add(m);
            }
        }
        else {
            products.put(m.name, new ArrayDeque<>());
            addProduct(n,m);
        }
    }

    public void listAllOrdered(){

        int  bottom;
        Product aux, aux2;
        boolean exchanged = true;

        bottom = productList.size() - 2;
        while (exchanged) {
            exchanged = false;
            for (int i = 0; i <= bottom; i++) {
                if (productList.get(i).cost > productList.get(i+1).cost) {
                    aux2 = productList.remove(i+1);
                    aux = productList.remove(i); // exchange
                    productList.add(i, aux2);
                    productList.add(i+1, aux);
                    exchanged = true; // exchange is made
                }
            }
            bottom--;
        }


    }

}
