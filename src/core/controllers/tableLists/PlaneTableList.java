/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers.tableLists;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Plane;
import core.models.storage.AirportStorage;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jdiaz
 */
public class PlaneTableList {

    public static Response updatePlanesList(DefaultTableModel model) {
        try {
            model.setRowCount(0); // Limpia la tabla antes de actualizarla

            var planeRepo = AirportStorage.getInstance().getPlaneRepo();
            List<Plane> planes = planeRepo.getAllPlanes();

            if (planes.isEmpty()) {
                return new Response("No se encontraron aviones registrados.", Status.NO_CONTENT);
            }

            for (Plane plane : planes) {
                model.addRow(new Object[]{
                    plane.getId(),
                    plane.getBrand(),
                    plane.getModel(),
                    plane.getMaxCapacity(),
                    plane.getAirline(),
                    plane.getFlights().size() // Cantidad de vuelos asociados
                });
            }

            return new Response("Lista de aviones actualizada exitosamente.", Status.OK);

        } catch (Exception e) {
            return new Response("Ocurrió un error al cargar la lista de aviones.", Status.INTERNAL_SERVER_ERROR);
        }
    }

}
