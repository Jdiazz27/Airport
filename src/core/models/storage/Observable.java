package core.models.storage;
import core.models.interfaces.Observer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jdiaz
 */
public abstract class Observable {

    private final List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer o) {
        observers.add(o);
    }

    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    public void notifyObservers() {
        for (Observer o : observers) {
            o.update();
        }
    }
}
