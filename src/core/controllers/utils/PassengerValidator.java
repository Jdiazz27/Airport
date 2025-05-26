/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers.utils;

import core.models.Passenger;
import core.models.storage.PassengerRepository;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *
 * @author jdiaz
 */
public class PassengerValidator {

    public static Response parseAndValidate(String idStr, String nombre, String apellido,
            String cumpleStr, String telefonoCodeStr, String telefonoStr,
            String pais, PassengerRepository repo, boolean isUpdate) {

        // Validación de campos obligatorios
        if (isNullOrEmpty(idStr)) {
            return badRequest("El ID no puede estar vacío.");
        }
        if (isNullOrEmpty(nombre)) {
            return badRequest("El nombre no puede estar vacío.");
        }
        if (isNullOrEmpty(apellido)) {
            return badRequest("El apellido no puede estar vacío.");
        }
        if (isNullOrEmpty(cumpleStr)) {
            return badRequest("La fecha de nacimiento no puede estar vacía.");
        }
        if (isNullOrEmpty(telefonoCodeStr)) {
            return badRequest("El código de país no puede estar vacío.");
        }
        if (isNullOrEmpty(telefonoStr)) {
            return badRequest("El número de teléfono no puede estar vacío.");
        }
        if (isNullOrEmpty(pais)) {
            return badRequest("El país no puede estar vacío.");
        }

        // Validación de ID
        long id;
        try {
            id = Long.parseLong(idStr);
            if (id < 0 || idStr.length() > 15) {
                return badRequest("El ID debe ser un número válido (≥ 0) de máximo 15 dígitos.");
            }
        } catch (NumberFormatException e) {
            return badRequest("El ID debe ser un número válido.");
        }

        if (!isUpdate && repo.getPassenger(id) != null) {
            return badRequest("Ya existe un pasajero con ese ID.");
        }
        if (isUpdate && repo.getPassenger(id) == null) {
            return badRequest("El pasajero a actualizar no existe.");
        }

        //Validar nombre
        if (!nombre.matches("[\\p{L} ]+")) {
            return new Response("El nombre debe contener solo letras.", Status.BAD_REQUEST);
        }

        //Validar apellido
        if (!apellido.matches("[\\p{L} ]+")) {
            return new Response("El apellido debe contener solo letras.", Status.BAD_REQUEST);
        }

        //Validar pais
        if (!pais.matches("[\\p{L} ]+")) {
            return new Response("El país debe contener solo letras.", Status.BAD_REQUEST);
        }

        // Validación de fecha de nacimiento
        LocalDate cumple;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
            cumple = LocalDate.parse(cumpleStr, formatter);
        } catch (DateTimeParseException e) {
            return new Response("La fecha de nacimiento es inválida. Formato esperado: YYYY-MM-DD. Debe ser númerico.", Status.BAD_REQUEST);
        }

        // Validación de código de país
        int celCode;
        try {
            celCode = Integer.parseInt(telefonoCodeStr);
            if (celCode < 0 || telefonoCodeStr.length() > 3) {
                return badRequest("El código de país debe ser ≥ 0 y tener máximo 3 dígitos.");
            }
        } catch (NumberFormatException e) {
            return badRequest("El código de país debe ser numérico.");
        }

        // Validación de número de teléfono
        long cel;
        try {
            cel = Long.parseLong(telefonoStr);
            if (cel < 0 || telefonoStr.length() > 11) {
                return badRequest("El teléfono debe ser ≥ 0 y tener máximo 11 dígitos.");
            }
        } catch (NumberFormatException e) {
            return badRequest("El teléfono debe ser numérico.");
        }

        // Crear objeto si todo es válido
        Passenger passenger = new Passenger(id, nombre, apellido, cumple, celCode, cel, pais);
        return new Response("Validación exitosa.", Status.OK, passenger);
    }

    private static boolean isNullOrEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }

    private static Response badRequest(String message) {
        return new Response(message, Status.BAD_REQUEST);
    }
}
