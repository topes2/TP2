abstract class Product {
    String name;
    float value;
    float getvalue(){
        return value;
    }
    abstract boolean is_persihable();
}

class Perishable extends Product {
    float[] date;
    String name;
    Perishable(String name,float[] date,float value) {
        this.name = name;
        this.date = date;
        this.value = value;
    }
    @Override
    boolean is_persihable() {
        return true;
    }
}

class NonPerishable extends Product{
    float volume;
    float getvolume(){
        return volume;
    }

    NonPerishable(String name,float value,float volume){
        this.name = name;
        this.value = value;
        this.volume = volume;
    }
    @Override
    boolean is_persihable() {
        return false;
    }
}