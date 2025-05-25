package core.models.storage;

import core.models.Passenger;
import core.models.Plane;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author jdiaz
 */
public class PassengerRepository extends Observable {

    private List<Passenger> passengers = new ArrayList<>();

    public boolean addPassenger(Passenger passenger) {
        for (Passenger p : this.passengers) {
            if (p.getId() == passenger.getId()) {
                return false;
            }
        }
        this.passengers.add(passenger);
        return true;
    }

    public Passenger getPassenger(Long id) {
        for (Passenger p : passengers) {
            if (p.getId() == id) {
                return p.clone();
            }
        }
        return null;
    }

    public boolean delPassenger(Long id) {
        for (Passenger passenger : this.passengers) {
            if (passenger.getId() == id) {
                this.passengers.remove(passenger);
                return true;
            }
        }
        return false;
    }

    public List<Passenger> getAllPassengers() {
        List<Passenger> sortedList = new ArrayList<>();
        for (Passenger p : passengers) {
            sortedList.add(p.clone());
        }
        Collections.sort(sortedList, new Comparator<Passenger>() {
            @Override
            public int compare(Passenger p1, Passenger p2) {
                return Long.compare(p1.getId(), p2.getId());
            }
        });

        return sortedList;
    }

    public Passenger getPassengerRaw(long id) {
        for (Passenger p : passengers) {
            if (p.getId() == id) {
                return p; // sin clonar
            }
        }
        return null;
    }

    public boolean updatePassenger(Passenger updatedPassenger) {
        for (int i = 0; i < passengers.size(); i++) {
            if (passengers.get(i).getId() == updatedPassenger.getId()) {
                passengers.set(i, updatedPassenger);
                notifyObservers(); // Para el patrón observer
                return true;
            }
        }
        return false;
    }

}
