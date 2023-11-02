package dk.lyngby.routes;

import dk.lyngby.controller.impl.PlantController;
import dk.lyngby.controller.impl.PlantControllerDB;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.*;

public class PlantRoute {

    private final PlantController plantController = new PlantController();
    private final PlantControllerDB plantControllerDB = new PlantControllerDB();

    public EndpointGroup plantRoutingMock(){
        return () -> {
            path("/plants", () -> {
                get("/", plantController.getAll());
                get("/{id}", plantController.getById());
                get("/type/{type}", plantController.getByType());
                post("/", plantController.add());
            });
        };
    }
    public EndpointGroup plantRoutingDB(){
        return () -> {
            path("/plants", () -> {
                get("/", plantControllerDB.getAll());
                get("/{id}", plantControllerDB.getById());
                get("/type/{type}", plantControllerDB.getByType());
                post("/", plantControllerDB.add());
            });
        };
    }

}
