/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.models.flight.Flight;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author jdiaz
 */
public class FlightRepository {

    private List<Flight> flights = new ArrayList<>();

    public boolean addFlight(Flight flight) {
        for (Flight f : this.flights) {
            if (f.getId().equals(flight.getId())) {
                return false;
            }
        }
        this.flights.add(flight);
        return true;
    }

    public Flight getFlight(String id) {
        for (Flight f : flights) {
            if (f.getId().equals(id)) {
                return f.clone();
            }
        }
        return null;
    }

    public boolean delFlight(String id) {
        for (Flight f : this.flights) {
            if (f.getId().equals(id)) {
                this.flights.remove(f);
                return true;
            }
        }
        return false;
    }

    public List<Flight> getAllFlights() {
        List<Flight> sortedList = new ArrayList<>();

        for (Flight f : flights) {
            sortedList.add(f.clone());
        }

        Collections.sort(sortedList, new Comparator<Flight>() {
            @Override
            public int compare(Flight f1, Flight f2) {
                return f1.getId().compareTo(f2.getId());
            }
        });

        return sortedList;
    }

    public Flight findById(String id) {
        for (Flight f : flights) {
            if (f.getId().equals(id)) {
                return f;
            }
        }
        return null;
    }

}
