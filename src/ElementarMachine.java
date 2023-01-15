import java.util.ArrayList;


@SuppressWarnings("unchecked")
public class ElementarMachine<T> {

    ArrayList<Element<T>> elements;


    public ElementarMachine(){
        elements = new ArrayList<>();
    }


    public void addThings(int n, T thing){


        for (Element<T> element : elements) {
            if (element.getThing() == thing) {
                int x = element.getCount();
                element.setCount(x + n);
                return;
            }
        }

        Element<T> x = new Element<T>(n, thing);
        elements.add(x);

    }
    public boolean removeOneThing(T thing){

        for (Element<T> element : elements) {
            if (element.getThing() == thing) {
                if ((element.getCount() - 1) >= 0) {
                    element.setCount(element.getCount() - 1);
                    return true;
                }
            }
        }
        return false;
    }
    public void listAll(){
        for (Element<T> element : elements) {
            System.out.println(element.toString());
        }
    }
}
