/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers.tableLists;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Location;
import core.models.storage.AirportStorage;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jdiaz
 */
public class LocationTableList {

    public static Response updateLocationsList(DefaultTableModel model) {
        try {
            model.setRowCount(0); // Limpiar tabla antes de cargar los datos

            var locationRepo = AirportStorage.getInstance().getLocationRepository();
            List<Location> locations = locationRepo.getAllLocations();

            if (locations.isEmpty()) {
                return new Response("No se encontraron aeropuertos registrados.", Status.NO_CONTENT);
            }

            for (Location location : locations) {
                model.addRow(new Object[]{
                    location.getAirportId(),
                    location.getAirportName(),
                    location.getAirportCity(),
                    location.getAirportCountry()
                });
            }

            return new Response("Lista de aeropuertos cargada exitosamente.", Status.OK);

        } catch (Exception e) {
            return new Response("Ocurrió un error al cargar la lista de aeropuertos.", Status.INTERNAL_SERVER_ERROR);
        }
    }

}
