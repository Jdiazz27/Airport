package core.models;

import java.util.ArrayList;
import core.models.interfaces.Clone;
import core.models.interfaces.HasFlights;

/**
 *
 * @author edangulo
 */
public class Plane implements Clone<Plane>, HasFlights {

    private final String id;
    private String brand;
    private String model;
    private final int maxCapacity;
    private String airline;
    private ArrayList<Flight> flights;

    public Plane(String id, String brand, String model, int maxCapacity, String airline) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.maxCapacity = maxCapacity;
        this.airline = airline;
        this.flights = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public String getAirline() {
        return airline;
    }

    public int getNumFlights() {
        return flights.size();
    }

    public ArrayList<Flight> getFlights() {
        return flights;
    }

    @Override
    public Plane clone() {
        Plane copy = new Plane(
                this.id,
                this.brand,
                this.model,
                this.maxCapacity,
                this.airline
        );

        for (Flight f : this.flights) {
            copy.addFlight(f);
        }

        return copy;
    }

    @Override
    public void addFlight(Flight flight) {
        flights.add(flight);
    }

}
