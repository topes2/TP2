import java.io.Serializable;
import java.text.DecimalFormat;

public class CoinBuffer implements Serializable {
    private int[] moedas;
    private float saldo;
    public CoinBuffer(){
        moedas = new int[6];
        saldo = 0;
    }

    public float getSaldo(){
        return saldo;
    }

    public void AddCoin(float value,int n){//what coin was added

        DecimalFormat dc = new DecimalFormat("0.00");

        switch (dc.format(value)) {
            case "0.05" -> moedas[0] += n;
            case "0.10" -> moedas[1] += n;
            case "0.20" -> moedas[2] += n;
            case "0.50" -> moedas[3] += n;
            case "1.00" -> moedas[4] += n;
            case "2.00" -> moedas[5] += n;
            default -> {
                return;
            }
        }

        saldo += (value *n);

    }

    public void reset(){
        for(int i = 0; i < 6;i++){
            moedas[i] = 0;
        }
        saldo = 0;
    }


    public void listAll() {
        System.out.println( "[ [0.05€:"+ moedas[0]+"] " +
                            "[0.1€:" + moedas[1]+ "] "  +
                            "[0.2€:" + moedas[2]+ "] "  +
                            "[0.5€:" + moedas[3]+ "] " +
                            "[1.0€:" + moedas[4]+ "] " +
                            "[2.0€:" + moedas[3]+ "] ]" );
    }
}

