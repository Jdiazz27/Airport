/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers.tableLists;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Passenger;
import core.models.storage.AirportStorage;
import core.models.utils.PassengerCalculations;
import core.models.utils.PassengerFormatter;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jdiaz
 */
public class PassengerTableList {

    public static Response updatePassengersList(DefaultTableModel model) {
        try {
            model.setRowCount(0);

            // Esta lista ya viene clonada y ordenada por ID
            List<Passenger> passengers = AirportStorage.getInstance().getPassengerRepo().getAllPassengers();

            if (passengers.isEmpty()) {
                return new Response("No hay pasajeros registrados.", Status.NO_CONTENT);
            }

            for (Passenger p : passengers) {
                model.addRow(new Object[]{
                    p.getId(), // ID
                    PassengerFormatter.getFullname(p), // Nombre completo
                    p.getBirthDate(), // Fecha de nacimiento
                    PassengerCalculations.calculateAge(p), // Edad
                    PassengerFormatter.generateFullPhone(p), // Teléfono
                    p.getCountry(), // País
                    PassengerCalculations.getNumFlights(p) // Número de vuelos
                });
            }

            return new Response("Lista de pasajeros cargada exitosamente.", Status.OK);
        } catch (Exception e) {
            return new Response("Error inesperado al cargar la lista de pasajeros.", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
