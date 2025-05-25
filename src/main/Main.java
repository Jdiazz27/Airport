/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import com.formdev.flatlaf.FlatDarkLaf;
import core.data.JsonDataLoader;
import core.models.Flight;
import core.models.Plane;
import core.models.storage.AirportStorage;
import core.view.AirportFrame;
import javax.swing.UIManager;

/**
 *
 * @author jdiaz
 */
public class Main {

    public static void main(String[] args) {
        System.setProperty("flatlaf.useNativeLibrary", "false");
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize Look and Feel");
        }
        
        System.out.println("Iniciando carga de datos...\n");
        JsonDataLoader loader = new JsonDataLoader();
        loader.loadAllData();

        AirportStorage storage = AirportStorage.getInstance();

        // Mostrar información de aviones
        System.out.println(">>> Lista de aviones disponibles:");
        for (Plane p : storage.getPlaneRepo().getAllPlanes()) {
            System.out.printf("-> Avión %s (%s)\n", p.getId(), p.getBrand());
            System.out.println("   Total de vuelos asignados: " + p.getFlights().size());
        }

        // Mostrar información de vuelos
        System.out.println("\n>>> Información de vuelos registrados:");
        for (Flight f : storage.getFlightRepository().getAllFlights()) {
            System.out.printf("-> Vuelo %s | Avión: %s\n", f.getId(), f.getPlane().getId());
            System.out.printf("   Ruta: %s -> %s\n",
                    f.getDepartureLocation().getAirportId(),
                    f.getArrivalLocation().getAirportId()
            );
        }
        java.awt.EventQueue.invokeLater(() -> {
            new AirportFrame().setVisible(true);
        });
    }

}
