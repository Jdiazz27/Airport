/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Passenger;
import core.models.storage.PassengerRepository;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jdiaz
 */
public class PassengerController {

    private final PassengerRepository passengerRepository;

    public PassengerController(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    public Response registerPassenger(Passenger passenger) {
        try {
            if (passenger == null) {
                return new Response("El pasajero no puede ser nulo.", Status.BAD_REQUEST, null);
            }

            long id = passenger.getId();
            if (id < 0 || String.valueOf(id).length() > 15) {
                return new Response("El ID debe ser un número mayor o igual a 0 y de máximo 15 dígitos.", Status.BAD_REQUEST, passenger.clone());
            }

            if (passengerRepository.findById(id) != null) {
                return new Response("Ya existe un pasajero con ese ID.", Status.BAD_REQUEST, passenger.clone());
            }

            if (passenger.getFirstname().isBlank() || passenger.getLastname().isBlank() || passenger.getCountry().isBlank()) {
                return new Response("Nombre, apellido y país no deben estar vacíos.", Status.BAD_REQUEST, passenger.clone());
            }

            if (passenger.getBirthDate() == null) {
                return new Response("La fecha de nacimiento no puede ser nula.", Status.BAD_REQUEST, passenger.clone());
            }

            int code = passenger.getCountryPhoneCode();
            if (code < 0 || String.valueOf(code).length() > 3) {
                return new Response("El código de país debe ser mayor o igual a 0 y de máximo 3 dígitos.", Status.BAD_REQUEST, passenger.clone());
            }

            long phone = passenger.getPhone();
            if (phone < 0 || String.valueOf(phone).length() > 11) {
                return new Response("El número de teléfono debe ser mayor o igual a 0 y de máximo 11 dígitos.", Status.BAD_REQUEST, passenger.clone());
            }

            boolean added = passengerRepository.addPassenger(passenger.clone());
            if (added) {
                return new Response("Pasajero registrado correctamente.", Status.CREATED, passenger.clone());
            } else {
                return new Response("No se pudo registrar el pasajero.", Status.INTERNAL_SERVER_ERROR, passenger.clone());
            }
        } catch (Exception e) {
            return new Response("Error interno al registrar pasajero: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR, null);
        }
    }

    public Response updatePassenger(Passenger passenger) {
        try {
            if (passenger == null) {
                return new Response("El pasajero no puede ser nulo.", Status.BAD_REQUEST, null);
            }

            long id = passenger.getId();
            if (passengerRepository.findById(id) == null) {
                return new Response("No existe un pasajero con ese ID.", Status.NOT_FOUND, passenger.clone());
            }

            if (passenger.getFirstname().isBlank() || passenger.getLastname().isBlank() || passenger.getCountry().isBlank()) {
                return new Response("Nombre, apellido y país no deben estar vacíos.", Status.BAD_REQUEST, passenger.clone());
            }

            if (passenger.getBirthDate() == null) {
                return new Response("La fecha de nacimiento no puede ser nula.", Status.BAD_REQUEST, passenger.clone());
            }

            int code = passenger.getCountryPhoneCode();
            if (code < 0 || String.valueOf(code).length() > 3) {
                return new Response("El código de país debe ser mayor o igual a 0 y de máximo 3 dígitos.", Status.BAD_REQUEST, passenger.clone());
            }

            long phone = passenger.getPhone();
            if (phone < 0 || String.valueOf(phone).length() > 11) {
                return new Response("El número de teléfono debe ser mayor o igual a 0 y de máximo 11 dígitos.", Status.BAD_REQUEST, passenger.clone());
            }

            boolean updated = passengerRepository.updatePassenger(passenger.clone());
            if (updated) {
                return new Response("Pasajero actualizado correctamente.", Status.OK, passenger.clone());
            } else {
                return new Response("No se pudo actualizar la información del pasajero.", Status.INTERNAL_SERVER_ERROR, passenger.clone());
            }
        } catch (Exception e) {
            return new Response("Error interno al actualizar pasajero: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR, null);
        }
    }

    public Passenger getPassenger(long id) {
        try {
            return passengerRepository.getPassenger(id);
        } catch (Exception e) {
            return null; // Puedes registrar el error si deseas
        }
    }

    public List<Passenger> getAllPassengers() {
        try {
            return passengerRepository.getAllPassengers();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
