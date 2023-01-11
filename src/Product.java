import java.util.Date;

@SuppressWarnings("unchecked")
public abstract class Product {
    public String name;
    double cost;
    double getvalue(){
        return cost;
    }
    abstract boolean is_persihable();
}


