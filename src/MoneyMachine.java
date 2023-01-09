import java.lang.*;

enum MOEDAS{ CENT5, CENT10, CENT20, CENT50, EURO1, EURO2};


public class MoneyMachine{

    private float total_value;
    private float[] moedas;


    public MoneyMachine(){
        moedas = new float[6];
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

        switch (Float.toString(m)){
            case "0.05":
                moedas[0] += n;
                break;
            case "0.1":
                moedas[1] += n;
                break;
            case "0.2":
                moedas[2] += n;
                break;
            case "0.5":
                moedas[3] += n;
                break;
            case "1.0":
                moedas[4] += n;
                break;
            case "2.0":
                moedas[5] += n;
                break;
            default:
                break;
        }

        this.total_value +=  (n*m);
    }



}


