import java.util.ArrayList;


@SuppressWarnings("unchecked")
public class ElementarMachine<T> {

    ArrayList<Element<T>> elements;


    public ElementarMachine(){
        elements = new ArrayList<>();
    }


    public void addThings(int n, T thing){


        for (int i = 0; i < elements.size(); i++) {
            if(elements.get(i).getThing() == thing){
                int x = elements.get(i).getCount();
                elements.get(i).setCount(x+n);
                return;
            }
        }

        Element x = new Element<T>(n, thing);
        elements.add(x);

    }
    public boolean removeOneThing(T thing){

        for (int i = 0; i < elements.size(); i++) {
            if(elements.get(i).getThing() == thing){
                int x = elements.get(i).getCount();
                if ((elements.get(i).getCount() - 1) > 0)
                    elements.get(i).setCount(x-1);
                return true;
            }
        }
        return false;
    }
    public void listAll(){
        for (Element<T> element :
             elements) {
            System.out.println(element.toString());
        }
    }
}
