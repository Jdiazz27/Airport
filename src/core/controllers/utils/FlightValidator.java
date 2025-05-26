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

    public static Response parseAndValidate(
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
            String minutesScaleStr,
            FlightRepository flightRepository,
            LocationRepository locationRepository,
            PlaneRepository planeRepository,
            boolean createMode) {

        // Validar ID
        if (isNullOrEmpty(flightId)) {
            return new Response("ID del vuelo es obligatorio.", Status.BAD_REQUEST);
        }
        if (!flightId.matches("[A-Z]{3}\\d{3}")) {
            return new Response("Formato de ID inválido: 3 letras y 3 dígitos.", Status.BAD_REQUEST);
        }
        if (!createMode && flightRepository.getFlight(flightId) != null) {
            return new Response("El vuelo con ese ID ya existe.", Status.BAD_REQUEST);
        }

        // Validar origen y destino
        if (isNullOrEmpty(originId) || isNullOrEmpty(destinationId)) {
            return new Response("Origen y destino no pueden estar vacíos.", Status.BAD_REQUEST);
        }
        if (originId.equals(destinationId)) {
            return new Response("Origen y destino deben ser distintos.", Status.BAD_REQUEST);
        }

        Location origin = locationRepository.getLocation(originId);
        if (origin == null) {
            return new Response("Ubicación de origen no encontrada.", Status.BAD_REQUEST);
        }
        Location destination = locationRepository.getLocation(destinationId);
        if (destination == null) {
            return new Response("Ubicación de destino no encontrada.", Status.BAD_REQUEST);
        }

        // Validar avión
        Plane plane = planeRepository.getPlane(planeId);
        if (plane == null) {
            return new Response("Avión no registrado.", Status.BAD_REQUEST);
        }

        // Validar fecha y hora de salida
        if (isNullOrEmpty(dateStr) || isNullOrEmpty(timeStr)) {
            return new Response("Debe indicar fecha y hora de salida.", Status.BAD_REQUEST);
        }
        LocalDateTime departureDateTime;
        try {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-M-d'T'H:m");
            departureDateTime = LocalDateTime.parse(dateStr + "T" + timeStr, fmt);
            if (departureDateTime.isBefore(LocalDateTime.now())) {
                return new Response("La salida debe ser futura o actual.", Status.BAD_REQUEST);
            }
        } catch (DateTimeParseException ex) {
            return new Response("Formato de fecha u hora erróneo. Usa YYYY-MM-DD y HH:mm.", Status.BAD_REQUEST);
        }

        // Validar duración hacia destino
        int hArr, mArr;
        try {
            hArr = Integer.parseInt(hoursArrivalStr);
            mArr = Integer.parseInt(minutesArrivalStr);
            if (hArr < 0 || mArr < 0 || mArr > 59) {
                return new Response("Duración hacia destino inválida.", Status.BAD_REQUEST);
            }
            if (hArr == 0 && mArr == 0) {
                return new Response("Duración del vuelo no puede ser cero.", Status.BAD_REQUEST);
            }
        } catch (NumberFormatException ne) {
            return new Response("Duración del vuelo debe ser numérica.", Status.BAD_REQUEST);
        }

        // Validar escala si aplica
        boolean hasScale = !isNullOrEmpty(scaleLocationId) && !scaleLocationId.equalsIgnoreCase("None");
        Location scale = null;
        int hScale = 0, mScale = 0;
        if (hasScale) {
            if (scaleLocationId.equals(originId) || scaleLocationId.equals(destinationId)) {
                return new Response("La escala no puede coincidir con origen o destino.", Status.BAD_REQUEST);
            }
            scale = locationRepository.getLocation(scaleLocationId);
            if (scale == null) {
                return new Response("Ubicación de escala no válida.", Status.BAD_REQUEST);
            }
            try {
                hScale = Integer.parseInt(hoursScaleStr);
                mScale = Integer.parseInt(minutesScaleStr);
                if (hScale < 0 || mScale < 0 || mScale > 59) {
                    return new Response("Duración de escala inválida.", Status.BAD_REQUEST);
                }
                if (hScale == 0 && mScale == 0) {
                    return new Response("La escala debe tener duración mayor a cero.", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ne) {
                return new Response("Duración de escala debe ser valores numéricos.", Status.BAD_REQUEST);
            }
        } else {
            if (!isNullOrEmpty(hoursScaleStr) || !isNullOrEmpty(minutesScaleStr)) {
                try {
                    hScale = Integer.parseInt(hoursScaleStr);
                    mScale = Integer.parseInt(minutesScaleStr);
                    if (hScale != 0 || mScale != 0) {
                        return new Response("Sin escala, horas y minutos deben ser 0.", Status.BAD_REQUEST);
                    }
                } catch (NumberFormatException ne) {
                    return new Response("Sin escala, valores deben ser cero.", Status.BAD_REQUEST);
                }
            }
        }

        // Construir el vuelo
        Flight flight = hasScale
                ? new Flight(flightId, plane, origin, scale, destination, departureDateTime, hArr, mArr, hScale, mScale)
                : new Flight(flightId, plane, origin, destination, departureDateTime, hArr, mArr);

        return new Response("Vuelo validado con éxito.", Status.OK, flight);
    }

    public static Response applyDelay(String flightId, String delayHours, String delayMinutes, FlightRepository repo) {
        if (isNullOrEmpty(flightId)) {
            return new Response("ID de vuelo requerido para retraso.", Status.BAD_REQUEST);
        }
        Flight flight = repo.getFlight(flightId);
        if (flight == null) {
            return new Response("No existe el vuelo para retrasar.", Status.NOT_FOUND);
        }
        int dh, dm;
        try {
            dh = Integer.parseInt(delayHours);
            dm = Integer.parseInt(delayMinutes);
            if (dh < 0 || dm < 0 || dm > 59) {
                return new Response("Valores de retraso inválidos.", Status.BAD_REQUEST);
            }
            if (dh == 0 && dm == 0) {
                return new Response("El retraso debe ser mayor que cero.", Status.BAD_REQUEST);
            }
        } catch (NumberFormatException ne) {
            return new Response("Retraso debe ser numérico.", Status.BAD_REQUEST);
        }
        flight.setDepartureDate(flight.getDepartureDate().plusHours(dh).plusMinutes(dm));
        return new Response("Retraso aplicado correctamente.", Status.OK, flight.clone());
    }

    private static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}
