package core.models.storage;

import core.models.Flight;
import core.models.Location;
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
public class AirportStorage {

    //metodo singleton
    private static AirportStorage instance;
    public static AirportStorage getInstance() {
        if (instance == null) {
            instance = new AirportStorage();
        }
        return instance;
    }

    private final PassengerRepository passengerRepo;
    private final PlaneRepository planeRepo;
    private final FlightRepository flightRepo;
    private final LocationRepository locationRepo;

    public AirportStorage() {
        this.passengerRepo = new PassengerRepository();
        this.planeRepo = new PlaneRepository();
        this.flightRepo = new FlightRepository();
        this.locationRepo = new LocationRepository();

    }

    // Métodos de acceso a los repositorios
    public PassengerRepository getPassengerRepo() {
        return passengerRepo;
    }

    public PlaneRepository getPlaneRepo() {
        return planeRepo;
    }

    public FlightRepository getFlightRepository() {
        return flightRepo;
    }

    public LocationRepository getLocationRepository() {
        return locationRepo;
    }

}
