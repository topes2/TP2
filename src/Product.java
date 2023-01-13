import java.util.Date;

@SuppressWarnings("unchecked")
public abstract class Product {
    String name;
    double cost;
    public double getCost(){
        return cost;
    }

    public String getName() {
        return name;
    }

    abstract boolean is_perishable();

    /**
     * @return String
     */
    @Override
    public String toString() {
        return "Element [" + getClass().getSimpleName() + "=Product [name=" + getName() + ", cost=" + getCost() + "]";
    }
}


