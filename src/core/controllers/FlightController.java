package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.flight.Flight;
import core.models.storage.FlightRepository;
import core.models.storage.LocationRepository;
import core.models.storage.PlaneRepository;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author jdiaz
 */
public class FlightController {

    private final FlightRepository flightRepo;
    private final LocationRepository locationRepo;
    private final PlaneRepository planeRepo;

    public FlightController(FlightRepository flightRepo, LocationRepository locationRepo, PlaneRepository planeRepo) {
        this.flightRepo = flightRepo;
        this.locationRepo = locationRepo;
        this.planeRepo = planeRepo;
    }

    public Response createFlight(String flightId, String originId, String destinationId,
            String dateStr, String timeStr, String limitStr, String planeId) {

        Response validation = FlightValidator.parseAndValidate(flightId, originId, destinationId, dateStr,
                timeStr, limitStr, planeId, flightRepo, locationRepo, planeRepo, false);

        if (validation.getStatus() != Status.OK) {
            return validation;
        }

        Flight flight = (Flight) validation.getObject();
        flightRepo.addFlight(flight);
        return new Response("Se ha registrado el vuelo.", Status.CREATED, flight);
    }
;
}
