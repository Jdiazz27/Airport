/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Location;
import core.models.storage.LocationRepository;

/**
 *
 * @author jdiaz
 */
public class LocationController {

    private LocationRepository locationRepository;

    public LocationController(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Response createLocation(String id, String name, String city, String country, String latitude, String longitude) {
        Response validation = LocationValidator.parseAndValidate(id, name, city, country, latitude, longitude, repository, false);

        if (validation.getStatus() != Status.OK) {
            return validation;
        }

        Location location = (Location) validation.getObject();
        repository.addLocation(location);
        return new Response("SE ha registrado la ubicación.", Status.CREATED, location.clone());
    }

    public Response getAllLocations() {
        var list = repository.getAllLocations();
        if (list.isEmpty()) {
            return new Response("No hay ubicaciones registradas.", Status.NO_CONTENT());
        }
        return new Response("Se obtuvieron las ubicaciones de manera exitosa.", Status.OK, list);
    }

    public Response getLocationById(String id) {
        Location location = repository.getLocation(id);
        if (location == null) {
            return new Response("No se encontró la ubicación.", Status.NOT_FOUND());
        }
        return new Response("Se encontró la ubicación.", Status.OK, location);
    } 
}
