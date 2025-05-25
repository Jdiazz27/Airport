package core.data;

import core.models.Flight;
import core.models.Location;
import core.models.Passenger;
import core.models.Plane;
import core.models.storage.AirportStorage;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 *
 * @author jdiaz
 */
public class JsonDataLoader {

    private final AirportStorage storage = AirportStorage.getInstance();

    public void loadAllData() {
        loadLocations();
        loadPlanes();
        loadPassengers();
        loadFlights();
    }

    private void loadLocations() {
        try {
            FileReader reader = new FileReader("C:/Users/jdiaz/OneDrive/Escritorio/III/POO/Airport/json/locations.json");
            JSONArray array = new JSONArray(new JSONTokener(reader));

            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);

                Location loc = new Location(
                        obj.getString("airportId"),
                        obj.getString("airportName"),
                        obj.getString("airportCity"),
                        obj.getString("airportCountry"),
                        obj.getDouble("airportLatitude"),
                        obj.getDouble("airportLongitude")
                );

                storage.getLocationRepository().addLocation(loc);
            }

            System.out.println("[INFO] Locations cargados satisfactoriamente.");
        } catch (Exception e) {
            System.out.println("[ERROR] No se pudo cargar Locations: " + e.getMessage());
        }
    }

    private void loadPlanes() {
        try {
            FileReader reader = new FileReader("C:/Users/jdiaz/OneDrive/Escritorio/III/POO/Airport/json/planes.json");
            JSONArray array = new JSONArray(new JSONTokener(reader));

            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                Plane plane = new Plane(
                        obj.getString("id"),
                        obj.getString("brand"),
                        obj.getString("model"),
                        obj.getInt("maxCapacity"),
                        obj.getString("airline")
                );
                storage.getPlaneRepo().addPlane(plane);
            }

            System.out.println("[INFO] Aviones cargados correctamente.");
        } catch (Exception e) {
            System.out.println("[ERROR] Problema al cargar aviones: " + e.getMessage());
        }
    }

    private void loadPassengers() {
        try {
            FileReader reader = new FileReader("C:/Users/jdiaz/OneDrive/Escritorio/III/POO/Airport/json/passengers.json");
            JSONArray array = new JSONArray(new JSONTokener(reader));

            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                Passenger p = new Passenger(
                        obj.getLong("id"),
                        obj.getString("firstname"),
                        obj.getString("lastname"),
                        LocalDate.parse(obj.getString("birthDate")),
                        obj.getInt("countryPhoneCode"),
                        obj.getLong("phone"),
                        obj.getString("country")
                );
                storage.getPassengerRepo().addPassenger(p);
            }

            System.out.println("[INFO] Pasajeros registrados con éxito.");
        } catch (Exception e) {
            System.out.println("[ERROR] Fallo al leer datos de pasajeros: " + e.getMessage());
        }
    }

    private void loadFlights() {
        try {
            FileReader reader = new FileReader("C:/Users/jdiaz/OneDrive/Escritorio/III/POO/Airport/json/flights.json");
            JSONArray array = new JSONArray(new JSONTokener(reader));

            for (int i = 0; i < array.length(); i++) {
                JSONObject f = array.getJSONObject(i);

                Plane plane = storage.getPlaneRepo().findById(f.getString("plane"));
                Location dep = storage.getLocationRepository().findById(f.getString("departureLocation"));
                Location arr = storage.getLocationRepository().findById(f.getString("arrivalLocation"));
                Location scale = null;

                if (!f.isNull("scaleLocation")) {
                    scale = storage.getLocationRepository().findById(f.getString("scaleLocation"));
                }

                Flight flight;
                if (scale != null) {
                    flight = new Flight(
                            f.getString("id"),
                            plane, dep, scale, arr,
                            LocalDateTime.parse(f.getString("departureDate")),
                            f.getInt("hoursDurationArrival"),
                            f.getInt("minutesDurationArrival"),
                            f.getInt("hoursDurationScale"),
                            f.getInt("minutesDurationScale")
                    );
                } else {
                    flight = new Flight(
                            f.getString("id"),
                            plane, dep, arr,
                            LocalDateTime.parse(f.getString("departureDate")),
                            f.getInt("hoursDurationArrival"),
                            f.getInt("minutesDurationArrival")
                    );
                }

                if (plane != null) {
                    plane.addFlight(flight);
                }

                storage.getFlightRepository().addFlight(flight);
            }

            System.out.println("[INFO] Información de vuelos importada correctamente.");
        } catch (Exception e) {
            System.out.println("[ERROR] No se logró importar vuelos: " + e.getMessage());
        }
    }

}
