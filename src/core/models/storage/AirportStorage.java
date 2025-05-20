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
    private final FlightRepository planeRepo;
    private final LocationRepository locationRepo;

    public AirportStorage() {
        this.passengerRepo = passengerRepo;
        this.planeRepo = planeRepo;
        this.planeRepo = planeRepo;
        this.locationRepo = locationRepo;
    }
    
    
    
    public PassengerRepository getPassengerRepo(){  //metodos para entrar a los repositorios
        return passengerRepo;
    }
    
    public PlaneRepository getPlaneRepo(){
        return planeRepo;
    }
    
    public FlightRepository getFlightRepository(){
        return flightRepo;
    }
    
    public locationRepository getLocationRepository(){
        return locationRepo;
    }
    
    
    
    

}
