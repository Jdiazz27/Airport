/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.data;

import core.models.Flight;
import core.models.Location;
import core.models.Passenger;
import core.models.Plane;
import core.models.storage.AirportStorage;
import java.lang.reflect.Method;
import java.util.List;
import javax.swing.JComboBox;

/**
 *
 * @author jdiaz
 */
public class ComboLoader {

    public static void loadPassengers(JComboBox<String> combo) {
        for (Passenger p : AirportStorage.getInstance().getPassengerRepo().getAllPassengers()) {
            combo.addItem(String.valueOf(p.getId()));
        }
    }

    public static void loadPlanes(JComboBox<String> combo) {
        for (Plane p : AirportStorage.getInstance().getPlaneRepo().getAllPlanes()) {
            combo.addItem(String.valueOf(p.getId()));
        }
    }

    public static void loadLocations(JComboBox<String> combo) {
        for (Location l : AirportStorage.getInstance().getLocationRepository().getAllLocations()) {
            combo.addItem(String.valueOf(l.getAirportId()));
        }
    }

    public static void loadFlights(JComboBox<String> combo) {
        for (Flight f : AirportStorage.getInstance().getFlightRepository().getAllFlights()) {
            combo.addItem(String.valueOf(f.getId()));
        }
    }

}
