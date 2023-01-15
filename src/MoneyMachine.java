import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class MoneyMachine extends ElementarMachine<Float> implements Serializable {

     private float totalValue;
     private CoinBuffer cb;



    public MoneyMachine(){
        elements = new ArrayList<>();
        totalValue = 0;
        cb = new CoinBuffer();
    }

    public CoinBuffer getCb(){
    return cb;
    };

   public void giveChange(float cost){
        sortCoins();
        DecimalFormat dc = new DecimalFormat("0.00");
        totalValue += cb.getSaldo();
        float troco1 = cb.getSaldo() - cost;
        System.out.println("                       [Troco:"+Float.parseFloat(dc.format(troco1)) + "€]");

        if(cost > 0) {

            float troco = troco1;
            cb.reset();
            while (!dc.format(troco).equals("0.00")){
                for (Element<Float> x: elements) {
                    if (troco - x.getThing() >= 0 && removeOneThing(x.getThing()) ){
                        troco -= x.getThing();
                        addToTotal(-(x.getThing()));
                        cb.AddCoin(x.getThing(), 1);
                        troco = Float.parseFloat(dc.format(troco));
                        break;
                    }
                }
            }
        }


        cb.listAll();
        cb.reset();
   }

   public float MoneyToCollect(){

        float moneyToChange=0;

       for (Element<Float> element: elements) {
           moneyToChange += (element.getCount() * element.getThing());
       }

       return totalValue - moneyToChange;
   }

    public float getTotalValue() {
    return totalValue;
    }

    public void addToTotal(float valuetoadd){
    totalValue += valuetoadd;
    }

    public void sortCoins(){

    Element<Float> temp, temp2;
    int  bottom, i;
    boolean exchanged = true;
    bottom = elements.size() - 2;
    while (exchanged) {
        exchanged = false;
        for (i = 0; i <= bottom; i++) {
            if (elements.get(i).getThing() < elements.get(i+1).getThing())  {
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

    public void addThings(int n, float f){

        for (Element<Float> element : elements) {
            if (Float.toString(element.getThing()).equals(Float.toString(f))) {
                int x = element.getCount();
                element.setCount(x + n);
                totalValue += (n * f);
                return;
            }
        }
    Element<Float> x = new Element<>(0, f);
    elements.add(x);
    addThings(n,f);
    }

    public void addMoney(int n, float f){
    addThings(n,f);
    }




}
