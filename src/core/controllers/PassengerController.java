/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.PassengerValidator;
import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Flight;
import core.models.Passenger;
import core.models.storage.PassengerRepository;
import java.util.Collections;
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

    public Response registerPassenger(String idStr, String firstname, String lastname,
            String birthStr, String phoneCodeStr, String phoneStr,
            String country) {

        Response response = PassengerValidator.parseAndValidate(
                idStr, firstname, lastname, birthStr,
                phoneCodeStr, phoneStr, country,
                passengerRepository, false // false: es registro
        );

        if (response.getStatus() != Status.OK) {
            return response;
        }

        Passenger passenger = (Passenger) response.getObject();

        // Ya validado que no existe en el validator, así que esta línea es opcional:
        if (passengerRepository.getPassengerRaw(passenger.getId()) != null) {
            return new Response("Ya existe un pasajero con ese ID.", Status.BAD_REQUEST);
        }

        if (!passengerRepository.addPassenger(passenger)) {
            return new Response("No se pudo registrar el pasajero.", Status.INTERNAL_SERVER_ERROR);
        }

        return new Response("Pasajero registrado correctamente.", Status.CREATED, passenger.clone());
    }

    public Response updatePassenger(String idStr, String firstname, String lastname,
            String birthStr, String phoneCodeStr, String phoneStr,
            String country) {

        Response response = PassengerValidator.parseAndValidate(
                idStr, firstname, lastname, birthStr,
                phoneCodeStr, phoneStr, country,
                passengerRepository, true // true: es actualización
        );

        if (response.getStatus() != Status.OK) {
            return response;
        }

        Passenger passenger = (Passenger) response.getObject();

        if (passengerRepository.getPassengerRaw(passenger.getId()) == null) {
            return new Response("No existe un pasajero con ese ID.", Status.NOT_FOUND);
        }

        if (!passengerRepository.updatePassenger(passenger)) {
            return new Response("No se pudo actualizar la información del pasajero.", Status.INTERNAL_SERVER_ERROR);
        }

        return new Response("Pasajero actualizado correctamente.", Status.OK, passenger.clone());
    }

    public Passenger getPassenger(long id) {
        return passengerRepository.getPassenger(id); // este sí clona
    }

    public List<Passenger> getAllPassengers() {
        return passengerRepository.getAllPassengers(); // ya clona
    }

    public List<Flight> getFlightsByPassengerId(long passengerId) {
        Passenger passenger = passengerRepository.getPassenger(passengerId);
        if (passenger == null) {
            return Collections.emptyList();
        }
        return passenger.getFlights(); // si son clones, perfecto
    }

}
