package core.controllers;

import core.controllers.utils.PlaneValidator;
import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Plane;
import core.models.storage.AirportStorage;
import core.models.storage.PlaneRepository;

/**
 *
 * @author jdiaz
 */
public class PlaneController {

    public static Response registerPlane(String id, String brand, String model, String maxCapacity, String airline) {
        PlaneRepository repo = AirportStorage.getInstance().getPlaneRepo();

        Response r = PlaneValidator.parseAndValidate(id, brand, model, maxCapacity, airline, repo);
        if (r.getStatus() != Status.OK) {
            return r;
        }

        Plane plane = (Plane) r.getObject();
        boolean added = repo.addPlane(plane);

        if (!added) {
            return new Response("El avión no pudo ser registrado.", Status.INTERNAL_SERVER_ERROR);
        }

        return new Response("El avión fue registrado exitosamente.", Status.CREATED);
    }
}
