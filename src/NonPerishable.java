
@SuppressWarnings("unchecked")
class NonPerishable extends Product{
    double volume;
    double getVolume(){
        return volume;
    }

    public NonPerishable(String name,double cost,double volume){
        this.name = name;
        this.cost = cost;
        this.volume = volume;
    }
    @Override
    boolean is_persihable() {
        return false;
    }
}