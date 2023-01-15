import java.io.Serializable;
import java.text.DecimalFormat;
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

        if(p == null)
            return false;

        for (Element<Product> element : elements) {
            if (element.getThing().getName().equals(p.getName()) && element.getCount() > 0) {
                return true;
            }
        }
        return false;
    }

    public Product GetProduct(String name){
        for (Element<Product> element : elements) {
            if (element.getThing().getName().equals(name))
                    return element.getThing();
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


        for ( Element<Product> x: elements) {
            System.out.println("Element [" + x.getThing().getClass().getSimpleName() + "=Product [name=" + x.getThing().getName() +
                    ", cost=" + (float)x.getThing().cost  + "], count = " + x.getCount()+"]");
        }

    }

    public void listAllOrdered(){
        sortProducts();
        listAll();
    }

    public void listForClient(){

        DecimalFormat df = new  DecimalFormat("0.00");

        System.out.println( "///////////////////////////////////////////////\n" +
                            "||      Nome      |   Preço   |  Quantidade  ||" );

        for ( Element<Product> x: elements) {
            System.out.format("|| %14s | %9s | %7d      ||\n", x.getThing().getName(), df.format(x.getThing().getCost()) , x.getCount());
        }

        System.out.println( "///////////////////////////////////////////////\n");
    }

}
