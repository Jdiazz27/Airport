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
import java.time.LocalDateTime;

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
        PassengerRepository passengerRepository = AirportStorage.getInstance().getPassengerRepo();
        Passenger passenger = passengerRepository.getPassengerRaw(passengerId);

        if (passenger == null) {
            return new Response("No se encontró el pasajero.", Status.NOT_FOUND);
        }

        Flight flight = flightRepo.getFlightRaw(flightId);
        if (flight == null) {
            return new Response("No se encontró el vuelo.", Status.NOT_FOUND);
        }

        // Verificar si el pasajero ya está en el vuelo
        for (Flight f : passenger.getFlights()) {
            if (f.getId().equals(flightId)) {
                return new Response("El pasajero ya está asignado a este vuelo.", Status.BAD_REQUEST);
            }
        }

        // Agregar vuelo al pasajero y pasajero al vuelo
        passenger.addFlight(flight);
        flight.addPassenger(passenger);

        passengerRepository.notifyObservers();
        flightRepo.notifyObservers();

        return new Response("Pasajero agregado al vuelo exitosamente.", Status.OK);
    }


    public Response delayFlight(String flightId, String hoursStr, String minutesStr) {
        Response validation = FlightValidator.applyDelay(flightId, hoursStr, minutesStr, flightRepo);
        if (validation.getStatus() != Status.OK) {
            return validation;
        }

        int delayHours;
        int delayMinutes;
        try {
            delayHours = Integer.parseInt(hoursStr);
            delayMinutes = Integer.parseInt(minutesStr);
        } catch (NumberFormatException e) {
            return new Response("Las horas y minutos deben ser valores numéricos válidos.", Status.BAD_REQUEST);
        }

        Flight flight = flightRepo.getFlightRaw(flightId);
        if (flight == null) {
            return new Response("No se encontró el vuelo para aplicar retraso.", Status.NOT_FOUND);
        }

        LocalDateTime updatedDeparture = flight.getDepartureDate()
            .plusHours(delayHours)
            .plusMinutes(delayMinutes);

        flight.setDepartureDate(updatedDeparture);

        flightRepo.notifyObservers();

        return new Response("Retraso aplicado con éxito al vuelo.", Status.OK, flight.clone());
    }

    public Response getFlightById(String id) {
        Flight flight = flightRepo.getFlightRaw(id);
        if (flight == null) {
            return new Response("Vuelo no encontrado.", Status.NOT_FOUND);
        }
        return new Response("Vuelo encontrado.", Status.OK, flight);
    }
}
