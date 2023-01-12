import java.util.ArrayList;

public class MoneyMachine extends ElementarMachine<Float> {

    float totalValue;

    public MoneyMachine(){
        elements = new ArrayList<>();
        totalValue = 0;
    }

    public float getTotalValue() {
        return totalValue;
    }

    public void addThings(int n, float f){

        for (int i = 0; i < elements.size(); i++) {
            if(Float.toString(elements.get(i).getThing()).equals(Float.toString(f)) ){
                int x = elements.get(i).getCount();
                elements.get(i).setCount(x+n);
                totalValue += (n*f);
                return;
            }
        }

        Element x = new Element<Float>(n, f);
        x.setCount(n);
        elements.add(x);
        totalValue += (n*f);


    }

    public void addMoney(int n, float f){
        addThings(n,f);
    }




}
