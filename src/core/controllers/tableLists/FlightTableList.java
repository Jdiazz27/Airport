/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers.tableLists;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Flight;
import core.models.storage.AirportStorage;
import core.models.utils.FlightTimeCalculator;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jdiaz
 */
public class FlightTableList {

    public static Response updateFlightsList(DefaultTableModel model) {
        try {
            model.setRowCount(0); // Limpia la tabla antes de agregar nuevos datos

            var flightRepo = AirportStorage.getInstance().getFlightRepository();
            List<Flight> flights = flightRepo.getAllFlights();

            if (flights.isEmpty()) {
                return new Response("No se encontraron vuelos registrados.", Status.NO_CONTENT);
            }

            var flightCalculations = new FlightTimeCalculator();

            for (Flight flight : flights) {
                model.addRow(new Object[]{
                    flight.getId(),
                    flight.getDepartureLocation().getAirportCity(),
                    flight.getArrivalLocation().getAirportCity(),
                    flight.getScaleLocation() != null
                    ? flight.getScaleLocation().getAirportCity()
                    : "Sin escala",
                    flight.getDepartureDate(),
                    flightCalculations.calculateArrivalDate(flight),
                    flight.getPlane().getId(),
                    flight.getNumPassengers(),});
            }

            return new Response("Vuelos cargados exitosamente en la tabla.", Status.OK);

        } catch (Exception e) {
            return new Response("Ocurrió un error al cargar los vuelos.", Status.INTERNAL_SERVER_ERROR);
        }
    }

}
