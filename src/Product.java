import java.util.Calendar;
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
    Calendar limitDate;
    Calendar dayAdded;
    String name;
    Perishable(String name,Calendar limitDate,float cost) {
        this.name = name;
        this.limitDate = limitDate;
        this.cost = cost;
    }
    @Override
    boolean is_persihable() {
        return true;
    }

    @Override
    public boolean isFromToday(Calendar today){
        if( today.get(Calendar.MONTH) == dayAdded.get(Calendar.MONTH) && today.get(Calendar.DAY_OF_MONTH)  == dayAdded.get(Calendar.DAY_OF_MONTH))
            return true;
        return false;
    }

    @Override
    public boolean isOutDated(Calendar today) {
        if( today.get(Calendar.MONTH) > limitDate.get(Calendar.MONTH) && today.get(Calendar.DAY_OF_MONTH)  > limitDate.get(Calendar.DAY_OF_MONTH))
            return true;
        return false;
    }
}

class NonPerishable extends Product{
    float volume;
    float getvolume(){
        return volume;
    }

    NonPerishable(String name,float cost,float volume){
        this.name = name;
        this.cost = cost;
        this.volume = volume;
    }
    @Override
    boolean is_persihable() {
        return false;
    }
}