/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers.utils;

import core.models.Location;
import core.models.storage.LocationRepository;

/**
 *
 * @author jdiaz
 */
public class LocationValidator {

    public static Response parseAndValidate(
            String id, String name, String city, String country,
            String latStr, String lonStr,
            LocationRepository locationRepository, boolean isUpdate
    ) {
        // Validaciones de campos vacíos
        if (isNullOrEmpty(id)) {
            return badRequest("El ID no puede estar vacío.");
        }
        if (!id.matches("^[A-Z]{3}$")) {
            return badRequest("El ID debe tener exactamente 3 letras mayúsculas.");
        }

        if (isNullOrEmpty(name)) {
            return badRequest("El nombre no puede estar vacío.");
        }
        if (isNullOrEmpty(city)) {
            return badRequest("La ciudad no puede estar vacía.");
        }
        if (isNullOrEmpty(country)) {
            return badRequest("El país no puede estar vacío.");
        }

        // Validación de coordenadas
        double latitude = validateCoordinate(latStr, -90, 90);
        if (Double.isNaN(latitude)) {
            return badRequest("La latitud debe estar entre -90 y 90 y tener máximo 4 decimales.");
        }

        double longitude = validateCoordinate(lonStr, -180, 180);
        if (Double.isNaN(longitude)) {
            return badRequest("La longitud debe estar entre -180 y 180 y tener máximo 4 decimales.");
        }

        // Validación de duplicado
        if (!isUpdate && locationRepository.getLocation(id) != null) {
            return badRequest("Ya existe una ubicación con ese ID.");
        }

        // Creación y retorno
        Location location = new Location(id, name, city, country, latitude, longitude);
        return new Response("Validación exitosa.", Status.OK, location);
    }

    private static double validateCoordinate(String value, double min, double max) {
        if (isNullOrEmpty(value)) {
            return Double.NaN;
        }
        try {
            double num = Double.parseDouble(value);
            if (num < min || num > max || !hasAtMostFourDecimals(value)) {
                return Double.NaN;
            }
            return num;
        } catch (NumberFormatException e) {
            return Double.NaN;
        }
    }

    private static boolean hasAtMostFourDecimals(String numberStr) {
        String[] parts = numberStr.split("\\.");
        return parts.length == 1 || parts[1].length() <= 4;
    }

    private static boolean isNullOrEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }

    private static Response badRequest(String message) {
        return new Response(message, Status.BAD_REQUEST);
    }
}
