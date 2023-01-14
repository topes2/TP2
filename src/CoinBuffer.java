public class CoinBuffer {
    public int[] count;

    public CoinBuffer(){
    }

    public float totalbag(){
        //add o valor de cada moeda
        float total = 0 + count[0]*.10f + count[1]*.20f+ count[2]*.50f + count[3]*1f + count[4]*2f;
        return total;
    }

    public void AddCoin(float value,int n){//what coin was added
        switch (Float.toString(value)){
            case "0.10":
                count[0] += n;
                break;
            case ".20":
                count[1] += n;
                break;
            case "0.50":
                count[2] += n;
                break;
            case "1":
                count[3] += n;
                break;
            case "2":
                count[4] +=n;
                break;
        }
    }

    public void reset(){
        for(int i = 0; i <= 5;i++){
            count[i] = 0;
        }
    }

    public void listAll(){
        System.out.println(
                "Moedas de 0.10 euros "+ count[0] +
                "Moedas de 0.20 euros " + count[1] +
                "Moedas de 0.50 euros " + count[2] +
                "Moedas de 1 euro " + count[3] +
                "Moedas de 2 euros " + count[4]);
    }
}

