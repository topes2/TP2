import java.text.SimpleDateFormat;
import java.util.Date;


class Perishable extends Product implements Freshness {
    Date limitDate;
    Date dayAdded;
    public Perishable(String name,float cost,  Date limitDate) {

        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.dayAdded = sdformat.parse(sdformat.format(new Date()));
            this.limitDate = sdformat.parse(sdformat.format(limitDate));
            this.name = name;
            this.cost = cost;
        }
        catch (Exception e ){
            System.out.println(e.getMessage());
        }

    }
    @Override
    boolean is_perishable() {
        return true;
    }

    @Override
    public boolean isFromToday(){

        Date now = new Date();

        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            now = sdformat.parse(sdformat.format(now));
        }
        catch ( Exception e){
            System.out.println(e.getMessage());
        }

        return now.equals(limitDate);
    }

    @Override
    public boolean isOutDated() {
        Date now = new Date();
        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            now = sdformat.parse(sdformat.format(now));
        }
        catch ( Exception e){
            System.out.println(e.getMessage());
        }

        if(now.compareTo(limitDate) > 0 )
            return true;
        return false;

    }
}
