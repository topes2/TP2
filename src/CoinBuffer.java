import java.io.Serializable;
import java.text.DecimalFormat;

public class CoinBuffer implements Serializable {
    private int[] count;
    private float saldo;
    public CoinBuffer(){
        count = new int[6];
        saldo = 0;
    }

    public float getSaldo(){
        return saldo;
    }

    public void AddCoin(float value,int n){//what coin was added

        DecimalFormat dc = new DecimalFormat("0.00");

        switch (dc.format(value)){
            case "0.05":
                count[0] += n;
                break;
            case "0.10":
                count[1] += n;
                break;
            case "0.20":
                count[2] += n;
                break;
            case "0.50":
                count[3] += n;
                break;
            case "1.00":
                count[4] += n;
                break;
            case "2.00":
                count[5] +=n;
                break;
            default:
                return;
        }

        saldo += (value *n);

    }

    public void reset(){
        for(int i = 0; i < 6;i++){
            count[i] = 0;
        }
        saldo = 0;
    }


    public void listAll(){
        System.out.println(
                "Moedas de 0.05 euros "+ count[0] +
                "\nMoedas de 0.10 euros "+ count[1] +
                "\nMoedas de 0.20 euros " + count[2] +
                "\nMoedas de 0.50 euros " + count[3] +
                "\nMoedas de 1 euro " + count[4] +
                "\nMoedas de 2 euros " + count[5]);
    }
}

