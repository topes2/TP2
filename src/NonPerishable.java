import java.io.Serializable;

@SuppressWarnings("unchecked")
class NonPerishable extends Product implements Serializable {
    double volume;
    double getVolume(){

        return volume;
    }

    public NonPerishable(String name,float cost,double volume){
        this.name = name;
        this.cost = cost;
        this.volume = volume;
    }

    public NonPerishable(String name,double cost,double volume){
        this.name = name;
        this.cost = cost;
        this.volume = volume;
    }

    @Override
    boolean is_perishable() {
        return false;
    }
}