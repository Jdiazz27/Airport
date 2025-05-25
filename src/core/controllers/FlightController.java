package core.controllers;

import core.controllers.utils.FlightValidator;
import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Flight;
import core.models.Passenger;
import core.models.storage.AirportStorage;
import core.models.storage.FlightRepository;
import core.models.storage.LocationRepository;
import core.models.storage.PassengerRepository;
import core.models.storage.PlaneRepository;
import java.util.Objects;

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

    public Response createFlight(
            String flightId,
            String originId,
            String destinationId,
            String dateStr,
            String timeStr,
            String hoursArrivalStr,
            String minutesArrivalStr,
            String planeId,
            String scaleLocationId,
            String hoursScaleStr,
            String minutesScaleStr
    ) {
        Response validation = FlightValidator.parseAndValidate(
                flightId, originId, destinationId, dateStr, timeStr,
                hoursArrivalStr, minutesArrivalStr,
                planeId, scaleLocationId, hoursScaleStr, minutesScaleStr,
                flightRepo, locationRepo, planeRepo, false
        );

        if (validation.getStatus() != Status.OK) {
            return validation;
        }

        Flight flight = (Flight) validation.getObject();

        if (!flightRepo.addFlight(flight)) {
            return new Response("No se pudo registrar el vuelo.", Status.INTERNAL_SERVER_ERROR);
        }

        flightRepo.notifyObservers();
        return new Response("Se ha registrado el vuelo.", Status.CREATED, flight.clone());
    }

    public Response addPassengerToFlight(long passengerId, String flightId) {
        PassengerRepository passengerRepo = AirportStorage.getInstance().getPassengerRepo();
        Passenger passenger = passengerRepo.getPassengerRaw(passengerId);

        if (passenger == null) {
            return new Response("Pasajero no encontrado.", Status.NOT_FOUND);
        }

        Flight flight = flightRepo.getFlightRaw(flightId);
        if (flight == null) {
            return new Response("Vuelo no encontrado.", Status.NOT_FOUND);
        }

        boolean yaTieneVuelo = passenger.getFlights().stream()
                .anyMatch(f -> Objects.equals(f.getId(), flightId));

        if (yaTieneVuelo) {
            return new Response("Este pasajero ya está registrado en ese vuelo.", Status.BAD_REQUEST);
        }

        passenger.addFlight(flight);
        flight.addPassenger(passenger);

        passengerRepo.notifyObservers();
        flightRepo.notifyObservers();

        return new Response("Pasajero añadido correctamente al vuelo.", Status.OK);
    }

    public Response delayFlight(String flightId, String hoursStr, String minutesStr) {
        Response validation = FlightValidator.validateDelay(flightId, hoursStr, minutesStr, flightRepo);
        if (validation.getStatus() != Status.OK) {
            return validation;
        }

        int hours = Integer.parseInt(hoursStr);
        int minutes = Integer.parseInt(minutesStr);

        Flight flight = flightRepo.getFlightRaw(flightId);
        if (flight == null) {
            return new Response("Vuelo no encontrado para retrasar.", Status.NOT_FOUND);
        }

        flight.setDepartureDate(flight.getDepartureDate().plusHours(hours).plusMinutes(minutes));

        flightRepo.notifyObservers();
        return new Response("Vuelo retrasado correctamente.", Status.OK, flight.clone());
    }

    public Response getFlightById(String id) {
        Flight flight = flightRepo.getFlight(id); // Usa el método que hace `.clone()`
        if (flight == null) {
            return new Response("Vuelo no encontrado.", Status.NOT_FOUND);
        }
        return new Response("Vuelo encontrado.", Status.OK, flight);
    }
}
