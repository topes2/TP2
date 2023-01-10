import java.util.Date;


abstract class Product {
    String name;
    float cost;
    float getvalue(){
        return cost;
    }
    abstract boolean is_persihable();
}

class Perishable extends Product implements Freshness {
    Date limitDate;
    Date dayAdded;
    String name;
    public Perishable(String name,float cost, Date limitDate) {
        this.name = name;
        this.limitDate = limitDate;
        this.cost = cost;
    }
    @Override
    boolean is_persihable() {
        return true;
    }

    @Override
    public boolean isFromToday(){
        Date today = new Date();
        if( today.getMonth() == dayAdded.getMonth() && today.getDay()  == dayAdded.getDay())
            return true;
        return false;
    }

    @Override
    public boolean isOutDated() {
        Date today = new Date();
        if( today.getMonth() > limitDate.getMonth() && today.getDay()  > limitDate.getDay())
            return true;
        return false;
    }
}

class NonPerishable extends Product{
    float volume;
    float getvolume(){
        return volume;
    }

    public NonPerishable(String name,float cost,float volume){
        this.name = name;
        this.cost = cost;
        this.volume = volume;
    }
    @Override
    boolean is_persihable() {
        return false;
    }
}