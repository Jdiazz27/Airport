/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers.tableLists;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Passenger;
import core.models.Flight;
import core.models.storage.AirportStorage;
import core.models.utils.FlightTimeCalculator;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jdiaz
 */
public class ShowMyFlightsTableList {

    public static Response updateShowMyFlights(DefaultTableModel model, String passengerId) {
        try {
            model.setRowCount(0); // Limpia la tabla antes de llenarla

            long id = Long.parseLong(passengerId);
            var passengerRepo = AirportStorage.getInstance().getPassengerRepo();
            Passenger passenger = passengerRepo.getPassenger(id);

            if (passenger == null) {
                return new Response("No se encontró un pasajero con el ID proporcionado.", Status.NOT_FOUND);
            }

            List<Flight> flights = passenger.getFlights();
            if (flights == null || flights.isEmpty()) {
                return new Response("El pasajero no tiene vuelos registrados.", Status.NO_CONTENT);
            }

            var flightCalculations = new FlightTimeCalculator();

            for (Flight flight : flights) {
                model.addRow(new Object[]{
                    flight.getId(),
                    flight.getDepartureDate(),
                    flightCalculations.calculateArrivalDate(flight)
                });
            }

            return new Response("Vuelos del pasajero cargados correctamente.", Status.OK);

        } catch (NumberFormatException e) {
            return new Response("El ID del pasajero no tiene un formato válido.", Status.BAD_REQUEST);
        } catch (Exception e) {
            return new Response("Ocurrió un error al obtener los vuelos del pasajero.", Status.INTERNAL_SERVER_ERROR);
        }
    }

}
