public class MoneyMachine{

    private float total_value;

    public float getTotalValue(){
        return total_value;
    }

    /**
     * *
     * @param n --> quantas vezes é adicionado m
     * @param m --> o valor da moeda inserida
     */
    public void addMoney(int n, float m){
        total_value = total_value + (n*m);
    }

}


