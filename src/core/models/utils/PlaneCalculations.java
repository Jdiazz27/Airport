package core.models.utils;

import core.models.Plane;

/**
 *
 * @author jdiaz
 */
public class PlaneCalculations {

    public static int getNumFlights(Plane plane) {
        return plane.getFlights().size();
    }
}
