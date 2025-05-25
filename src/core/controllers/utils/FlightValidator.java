/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers.utils;

import core.models.Location;
import core.models.Plane;
import core.models.Flight;
import core.models.storage.FlightRepository;
import core.models.storage.LocationRepository;
import core.models.storage.PlaneRepository;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *
 * @author jdiaz
 */
public class FlightValidator {

    private static boolean isNullOrEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }

    private static boolean isValidId(String id) {
        return id.matches("[A-Z]{3}\\d{3}");
    }

    private static boolean isValidDuration(String hStr, String mStr) {
        try {
            int h = Integer.parseInt(hStr);
            int m = Integer.parseInt(mStr);
            return h >= 0 && m >= 0 && m < 60 && (h > 0 || m > 0);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static LocalDateTime parseDateTime(String dateStr, String timeStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d'T'H:m");
        return LocalDateTime.parse(dateStr + "T" + timeStr, formatter);
    }

    public static Response parseAndValidate(
            String id,
            String departureLocationId,
            String arrivalLocationId,
            String departureDateStr,
            String departureTimeStr,
            String hoursArrivalStr,
            String minutesArrivalStr,
            String planeId,
            String scaleLocationId,
            String hoursScaleStr,
            String minutesScaleStr,
            FlightRepository flightRepo,
            LocationRepository locationRepo,
            PlaneRepository planeRepo,
            boolean isUpdate
    ) {
        // ID
        if (isNullOrEmpty(id)) {
            return error("El ID no puede estar vacío.");
        }
        if (!isValidId(id)) {
            return error("El ID debe tener el formato XXX123.");
        }
        if (!isUpdate && flightRepo.getFlight(id) != null) {
            return error("Ya existe un vuelo con ese ID.");
        }

        // Ubicaciones
        if (isNullOrEmpty(departureLocationId) || isNullOrEmpty(arrivalLocationId)) {
            return error("La ubicación de salida y llegada no pueden estar vacías.");
        }
        if (departureLocationId.equals(arrivalLocationId)) {
            return error("La ubicación de salida y llegada no pueden ser iguales.");
        }

        Location departure = locationRepo.getLocation(departureLocationId);
        Location arrival = locationRepo.getLocation(arrivalLocationId);
        if (departure == null) {
            return error("La ubicación de salida no existe.");
        }
        if (arrival == null) {
            return error("La ubicación de llegada no existe.");
        }

        // Avión
        Plane plane = planeRepo.getPlane(planeId);
        if (plane == null) {
            return error("El avión no existe.");
        }

        // Fecha y hora
        if (isNullOrEmpty(departureDateStr) || isNullOrEmpty(departureTimeStr)) {
            return error("La fecha y la hora de salida no pueden estar vacías.");
        }

        LocalDateTime departureDateTime;
        try {
            departureDateTime = parseDateTime(departureDateStr, departureTimeStr);
            if (departureDateTime.isBefore(LocalDateTime.now())) {
                return error("La fecha de salida debe ser futura o actual.");
            }
        } catch (DateTimeParseException e) {
            return error("Formato de fecha u hora inválido. Usa YYYY-MM-DD y HH:mm.");
        }

        // Duración llegada
        if (!isValidDuration(hoursArrivalStr, minutesArrivalStr)) {
            return error("Duración de llegada inválida.");
        }
        int hoursArrival = Integer.parseInt(hoursArrivalStr);
        int minutesArrival = Integer.parseInt(minutesArrivalStr);

        // Escala
        boolean hasScale = !isNullOrEmpty(scaleLocationId);
        Location scale = null;
        int hoursScale = 0, minutesScale = 0;

        if (hasScale) {
            if (scaleLocationId.equals(departureLocationId) || scaleLocationId.equals(arrivalLocationId)) {
                return error("La escala no puede ser igual a la salida o llegada.");
            }

            scale = locationRepo.getLocation(scaleLocationId);
            if (scale == null) {
                return error("La ubicación de escala no existe.");
            }

            if (!isValidDuration(hoursScaleStr, minutesScaleStr)) {
                return error("Duración de escala inválida.");
            }
            hoursScale = Integer.parseInt(hoursScaleStr);
            minutesScale = Integer.parseInt(minutesScaleStr);
        }

        Flight flight = hasScale
                ? new Flight(id, plane, departure, scale, arrival, departureDateTime, hoursArrival, minutesArrival, hoursScale, minutesScale)
                : new Flight(id, plane, departure, arrival, departureDateTime, hoursArrival, minutesArrival);

        return new Response("Validación exitosa.", Status.OK, flight);
    }

    public static Response validateDelay(String flightId, String hoursStr, String minutesStr, FlightRepository repo) {
        if (isNullOrEmpty(flightId)) {
            return error("El ID del vuelo no puede estar vacío.");
        }

        Flight flight = repo.getFlight(flightId);
        if (flight == null) {
            return new Response("El vuelo no existe.", Status.NOT_FOUND);
        }

        try {
            int hours = Integer.parseInt(hoursStr);
            int minutes = Integer.parseInt(minutesStr);

            if (hours < 0 || minutes < 0 || minutes > 59 || (hours == 0 && minutes == 0)) {
                return error("Horas o minutos inválidos.");
            }

            flight.setDepartureDate(flight.getDepartureDate().plusHours(hours).plusMinutes(minutes));
            return new Response("Vuelo retrasado correctamente.", Status.OK, flight.clone());

        } catch (NumberFormatException e) {
            return error("Las horas y minutos deben ser numéricos.");
        }
    }

    private static Response error(String msg) {
        return new Response(msg, Status.BAD_REQUEST);
    }
}
