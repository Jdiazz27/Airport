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

    public static Response parseAndValidate(String idStr, String firstname, String lastname,
            String birthStr, String phoneCodeStr, String phoneStr,
            String country, PassengerRepository repo, boolean isUpdate) {

        // Validación de campos obligatorios
        if (isNullOrEmpty(idStr)) {
            return badRequest("El ID no puede estar vacío.");
        }
        if (isNullOrEmpty(firstname)) {
            return badRequest("El nombre no puede estar vacío.");
        }
        if (isNullOrEmpty(lastname)) {
            return badRequest("El apellido no puede estar vacío.");
        }
        if (isNullOrEmpty(birthStr)) {
            return badRequest("La fecha de nacimiento no puede estar vacía.");
        }
        if (isNullOrEmpty(phoneCodeStr)) {
            return badRequest("El código de país no puede estar vacío.");
        }
        if (isNullOrEmpty(phoneStr)) {
            return badRequest("El número de teléfono no puede estar vacío.");
        }
        if (isNullOrEmpty(country)) {
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

        // Validación de fecha de nacimiento
        LocalDate birthDate;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
            birthDate = LocalDate.parse(birthStr, formatter);
            if (birthDate.isAfter(LocalDate.now())) {
                return badRequest("La fecha de nacimiento no puede estar en el futuro.");
            }
        } catch (DateTimeParseException e) {
            return badRequest("La fecha de nacimiento es inválida. Formato esperado: YYYY-M-D.");
        }

        // Validación de código de país
        int phoneCode;
        try {
            phoneCode = Integer.parseInt(phoneCodeStr);
            if (phoneCode < 0 || phoneCodeStr.length() > 3) {
                return badRequest("El código de país debe ser ≥ 0 y tener máximo 3 dígitos.");
            }
        } catch (NumberFormatException e) {
            return badRequest("El código de país debe ser numérico.");
        }

        // Validación de número de teléfono
        long phone;
        try {
            phone = Long.parseLong(phoneStr);
            if (phone < 0 || phoneStr.length() > 11) {
                return badRequest("El teléfono debe ser ≥ 0 y tener máximo 11 dígitos.");
            }
        } catch (NumberFormatException e) {
            return badRequest("El teléfono debe ser numérico.");
        }

        // Crear objeto si todo es válido
        Passenger passenger = new Passenger(id, firstname, lastname, birthDate, phoneCode, phone, country);
        return new Response("Validación exitosa.", Status.OK, passenger);
    }

// Métodos auxiliares
    private static boolean isNullOrEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }

    private static Response badRequest(String message) {
        return new Response(message, Status.BAD_REQUEST);
    }

}
