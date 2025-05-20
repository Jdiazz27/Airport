/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
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

    public Response registerLocation(Location location) {
        if (location == null) {
            return new Response(false, "La localizacion no puede ser nula. ", location.clone());
        }

        String id = location.getAirportId();
        if (id == null || !id.matches("[A-Z]{3}")) {
            return new Response(false, "El ID del aerouperto debe contener 3 letras en mayuscula.", location.clone());
        }

        if (locationRepository.findById(id) != null) {
            return new Response(false, "Ya existe una localizacion con esa ID", location.clone());
        }
        
        if (location.getAirportName().isBlank() || location.getAirportCity().isBlank() || location.getAirportCountry().insBlank()) {
            return new Response(false, "Nombre, ciudad y país no deben etar vacios ", location.clone());
        }
        
        double lat = location.getAirportLatitude();
        double lon = location.getAirportLongitude();
        
        if (lat < -90 || lat > 90 || lon < -180 || lon > 180) {
            return new Response(false, "Latitud debe estar entre -90 y 90. Longitud debe estar entre -180 y 180", location.clone());      
        }
        
        boolean added = locationRepository.addLocation(location.clone());
        
        if (added) {
            return new Response(true, "Localizacion registrada correctamente", location.clone());
        } else {
            return new Response(false, "No se pudo registrar la localizacion", location.clone());
        }
        
        public Location getLocation(String id){
            return locationRepository.getLocation(id);
        }
        
        public java.util.List<Location> getAllLocations(){
            return locationRepository.getAllLocations();
        }
        
    }
}
