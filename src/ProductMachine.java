import java.io.Serializable;
import java.util.ArrayList;
public class ProductMachine extends ElementarMachine<Product> implements Serializable {


    public ProductMachine(){
        elements = new ArrayList<>();
    }

    public void addProduct(int n, Product p){

       for (int i = 0; i < elements.size(); i++) {
            if(elements.get(i).getThing().getName().equals(p.getName())){
                int x = elements.get(i).getCount();
                elements.get(i).setCount(x+n);
                return;
            }
        }

        Element<Product> x = new Element<Product>(n, p);
        x.setCount(n);
        elements.add(x);


    }
    public boolean hasProduct(Product p){

        for (int i = 0; i < elements.size(); i++) {
            if(elements.get(i).getThing().getName().equals(p.getName())){
                return true;
            }
        }
        return false;
    }

    public Product GetProduct(String name){
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).getThing().getName().equals(name)) {
                return elements.get(i).getThing();
            }
        }
        return null;
    }

    public void sortProducts(){

        Element<Product> temp, temp2;
        int  bottom, i;
        boolean exchanged = true;
        bottom = elements.size() - 2;
        while (exchanged) {
            exchanged = false;
            for (i = 0; i <= bottom; i++) {
                if (elements.get(i).getThing().getCost() > elements.get(i+1).getThing().getCost())  {
                    temp = elements.remove(i+1); // exchange
                    temp2 = elements.remove(i);
                    elements.add(i, temp);
                    elements.add(i+1, temp2);
                    exchanged = true; // exchange is made
                }
            }
            bottom--;
        }

    }
    public void listAll(){

        for ( Element<Product> x:
             elements) {
            System.out.println("Element [" + x.getThing().getClass().getSimpleName() + " =Product [name= " + x.getThing().getName() +
                    ", cost= " + x.getThing().cost + "], count = " + x.getCount()+"]");
        }

    }

    public void listAllOrdered(){
        sortProducts();
        listAll();
    }
}
