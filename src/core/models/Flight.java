/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models;

import core.models.Location;
import core.models.Passenger;
import core.models.Plane;
import java.time.LocalDateTime;
import java.util.ArrayList;
import core.models.interfaces.Clone;

/**
 *
 * @author jdiaz
 */
public class Flight implements Clone<Flight> {

    private final String id;
    private ArrayList<Passenger> passengers;
    private Plane plane;
    private Location departureLocation;
    private Location scaleLocation;
    private Location arrivalLocation;
    private LocalDateTime departureDate;
    private int hoursDurationArrival;
    private int minutesDurationArrival;
    private int hoursDurationScale;
    private int minutesDurationScale;

    public Flight(String id, Plane plane, Location departureLocation, Location arrivalLocation, LocalDateTime departureDate, int hoursDurationArrival, int minutesDurationArrival) {
        this.id = id;
        this.passengers = new ArrayList<>();
        this.plane = plane;
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
        this.departureDate = departureDate;
        this.hoursDurationArrival = hoursDurationArrival;
        this.minutesDurationArrival = minutesDurationArrival;

        this.plane.addFlight(this);
    }

    public Flight(String id, Plane plane, Location departureLocation, Location scaleLocation, Location arrivalLocation, LocalDateTime departureDate, int hoursDurationArrival, int minutesDurationArrival, int hoursDurationScale, int minutesDurationScale) {
        this.id = id;
        this.passengers = new ArrayList<>();
        this.plane = plane;
        this.departureLocation = departureLocation;
        this.scaleLocation = scaleLocation;
        this.arrivalLocation = arrivalLocation;
        this.departureDate = departureDate;
        this.hoursDurationArrival = hoursDurationArrival;
        this.minutesDurationArrival = minutesDurationArrival;
        this.hoursDurationScale = hoursDurationScale;
        this.minutesDurationScale = minutesDurationScale;

        this.plane.addFlight(this);
    }

    public void addPassenger(Passenger passenger) {
        this.passengers.add(passenger);
    }

    public String getId() {
        return id;
    }

    public int getNumPassengers() {
        return passengers.size();
    }

    public Location getDepartureLocation() {
        return departureLocation;
    }

    public Location getScaleLocation() {
        return scaleLocation;
    }

    public Location getArrivalLocation() {
        return arrivalLocation;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public int getHoursDurationArrival() {
        return hoursDurationArrival;
    }

    public int getMinutesDurationArrival() {
        return minutesDurationArrival;
    }

    public int getHoursDurationScale() {
        return hoursDurationScale;
    }

    public int getMinutesDurationScale() {
        return minutesDurationScale;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
    }

    @Override
    public Flight clone() {
        Flight copy;

        // Verificamos si tiene escala
        if (scaleLocation != null) {
            copy = new Flight(
                    this.id,
                    this.plane,
                    this.departureLocation,
                    this.scaleLocation,
                    this.arrivalLocation,
                    this.departureDate,
                    this.hoursDurationArrival,
                    this.minutesDurationArrival,
                    this.hoursDurationScale,
                    this.minutesDurationScale
            );
        } else {
            copy = new Flight(
                    this.id,
                    this.plane,
                    this.departureLocation,
                    this.arrivalLocation,
                    this.departureDate,
                    this.hoursDurationArrival,
                    this.minutesDurationArrival
            );
        }

        for (Passenger p : this.passengers) {
            copy.addPassenger(p);
        }

        return copy;
    }

    public LocalDateTime calculateArrivalDate() {
        LocalDateTime arrival;

        if (scaleLocation != null) {
            // Si hay escala, sumamos duración escala + duración llegada
            arrival = departureDate
                    .plusHours(hoursDurationScale)
                    .plusMinutes(minutesDurationScale)
                    .plusHours(hoursDurationArrival)
                    .plusMinutes(minutesDurationArrival);
        } else {
            // Vuelo directo
            arrival = departureDate
                    .plusHours(hoursDurationArrival)
                    .plusMinutes(minutesDurationArrival);
        }
        return arrival;
    }

}
