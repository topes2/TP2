import java.util.ArrayList;

public class ProductMachine extends ElementarMachine<Product> {


    public ProductMachine(){
        elements = new ArrayList<>();
    }

    public void addProduct(int n, Product p){

        for (int i = 0; i < elements.size(); i++) {
            if(elements.get(i).getThing().getName() == p.getName()){
                int x = elements.get(i).getCount();
                elements.get(i).setCount(x+n);
                return;
            }
        }

        Element x = new Element<Product>(n, p);
        x.setCount(n);
        elements.add(x);
    }
    public boolean hasProduct(Product p){

        for (int i = 0; i < elements.size(); i++) {
            if(elements.get(i).getThing().getName() == p.getName()){
                return true;
            }
        }
        return false;
    }
    public void listAllOrdered( ){}
}
