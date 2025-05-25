/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.models.Flight;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author jdiaz
 */
public class FlightRepository extends Observable {

    private final List<Flight> flights = new ArrayList<>();

    public boolean addFlight(Flight flight) {
        for (Flight f : flights) {
            if (Objects.equals(f.getId(), flight.getId())) {
                return false;
            }
        }
        flights.add(flight);
        return true;
    }

    public Flight getFlight(String id) {
        for (Flight f : flights) {
            if (Objects.equals(f.getId(), id)) {
                return f.clone(); // devuelve una copia
            }
        }
        return null;
    }

    public Flight getFlightRaw(String id) {
        for (Flight f : flights) {
            if (Objects.equals(f.getId(), id)) {
                return f; // devuelve el original
            }
        }
        return null;
    }

    public boolean delFlight(String id) {
        return flights.removeIf(f -> Objects.equals(f.getId(), id));
    }

    public List<Flight> getAllFlights() {
        List<Flight> sortedList = new ArrayList<>();
        for (Flight f : flights) {
            sortedList.add(f.clone());
        }

        sortedList.sort(Comparator.comparing(Flight::getDepartureDate));
        return sortedList;
    }

    public Flight findById(String id) {
        return getFlightRaw(id);
    }
}
