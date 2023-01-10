import java.lang.*;
import java.util.HashMap;

enum MOEDAS{ CENT5, CENT10, CENT20, CENT50, EURO1, EURO2}


public class MoneyMachine{

    private float total_value;
    private HashMap<MOEDAS,Integer> moedas;


    public MoneyMachine(){
        moedas = new HashMap<>();
        moedas.put(MOEDAS.CENT5,0);
        moedas.put(MOEDAS.CENT10,0);
        moedas.put(MOEDAS.CENT20,0);
        moedas.put(MOEDAS.CENT50,0);
        moedas.put(MOEDAS.EURO1,0);
        moedas.put(MOEDAS.EURO2,0);
        total_value = 0;

    }
    public float getTotalValue(){
        return total_value;
    }

    /**
     * *
     * @param n --> quantas vezes é adicionado m
     * @param m --> o valor da moeda inserida
     */
    public void addMoney(int n, float m){

        int x = moedas.get(moeda(m));
        moedas.put(moeda(m), x+n);

        this.total_value +=  (n*m);
    }


    private MOEDAS moeda(float m){
        return switch (Float.toString(m)) {
            case "0.05" -> MOEDAS.CENT5;
            case "0.1" -> MOEDAS.CENT10;
            case "0.2" -> MOEDAS.CENT20;
            case "0.5" -> MOEDAS.CENT50;
            case "1.0" -> MOEDAS.EURO1;
            case "2.0" -> MOEDAS.EURO2;
            default -> null;
        };
    }



}


