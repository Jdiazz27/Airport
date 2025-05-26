/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers.utils;

import core.models.Plane;
import core.models.storage.PlaneRepository;
import java.util.Map;

/**
 *
 * @author jdiaz
 */
public class PlaneValidator {

    public static Response parseAndValidate(String id, String brand, String model, String maxCapacityStr, String airline, PlaneRepository repo, boolean isUpdate) {
        // Validar campos obligatorios
        Map<String, String> campos = Map.of(
                "ID", id,
                "Marca", brand,
                "Modelo", model,
                "Capacidad", maxCapacityStr,
                "Aerolínea", airline
        );

        for (Map.Entry<String, String> campo : campos.entrySet()) {
            if (isNullOrEmpty(campo.getValue())) {
                return new Response("El campo " + campo.getKey() + " no puede estar vacío.", Status.BAD_REQUEST);
            }
        }

        // Validar formato del ID
        if (!isValidPlaneId(id)) {
            return new Response("El ID del avión debe tener el formato XXYYYYY (2 letras mayúsculas seguidas de 5 dígitos).", Status.BAD_REQUEST);
        }

        //Validar marca
        if (!brand.matches("[\\p{L} ]+")) {
            return new Response("El nombre de la marca solo puede contener letras.", Status.BAD_REQUEST);
        }

        //Validar Modelo
        if (!model.matches("[\\p{L} ]+")) {
            return new Response("El nombre del modelo solo puede contener letras.", Status.BAD_REQUEST);
        }

        //Validar Airline
        if (!airline.matches("[\\p{L} ]+")) {
            return new Response("El nombre de la aerolínea solo puede contener letras.", Status.BAD_REQUEST);
        }

        // Validar existencia o duplicación según si es update
        if (!isUpdate && repo.getPlane(id) != null) {
            return new Response("Ya existe un avión con ese ID.", Status.BAD_REQUEST);
        }
        if (isUpdate && repo.getPlane(id) == null) {
            return new Response("El avión a actualizar no existe.", Status.BAD_REQUEST);
        }

        // Validar capacidad
        int capacity;
        try {
            capacity = Integer.parseInt(maxCapacityStr);
            if (capacity <= 0) {
                return new Response("La capacidad debe ser un número mayor que cero.", Status.BAD_REQUEST);
            }
        } catch (NumberFormatException e) {
            return new Response("La capacidad debe ser un número válido.", Status.BAD_REQUEST);
        }

        // Crear objeto Plane si todo es válido
        Plane plane = new Plane(id, brand, model, capacity, airline);
        return new Response("Validación exitosa", Status.OK, plane);
    }

    private static boolean isNullOrEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }

    private static boolean isValidPlaneId(String id) {
        return id != null && id.matches("^[A-Z]{2}\\d{5}$");
    }

}
