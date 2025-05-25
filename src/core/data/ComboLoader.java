/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.data;

import core.models.storage.AirportStorage;
import java.lang.reflect.Method;
import java.util.List;
import javax.swing.JComboBox;

/**
 *
 * @author jdiaz
 */
public class ComboLoader {

    public static void loadPassengers(JComboBox<String> combo) {
        loadIds(combo, AirportStorage.getInstance().getPassengerRepo().getAllPassengers());
    }

    public static void loadPlanes(JComboBox<String> combo) {
        loadIds(combo, AirportStorage.getInstance().getPlaneRepo().getAllPlanes());
    }

    public static void loadLocations(JComboBox<String> combo) {
        loadIds(combo, AirportStorage.getInstance().getLocationRepository().getAllLocations());
    }

    public static void loadFlights(JComboBox<String> combo) {
        loadIds(combo, AirportStorage.getInstance().getFlightRepository().getAllFlights());
    }

    private static <T> void loadIds(JComboBox<String> combo, List<T> items) { // Método genérico para poblar el combo con IDs
        combo.removeAllItems(); // Limpia combo antes de cargar
        for (T item : items) {
            String id = extractId(item);
            if (id != null) {
                combo.addItem(id);
            }
        }
    }

    // Extrae el ID usando reflexión simple
    private static String extractId(Object obj) {
        try {
            Method getIdMethod = obj.getClass().getMethod("getId");
            Object id = getIdMethod.invoke(obj);
            return id != null ? id.toString() : null;
        } catch (Exception e) {
            // Si no tiene getId o falla, ignora
            return null;
        }
    }
}
