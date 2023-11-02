package dk.lyngby.routes;

import io.javalin.Javalin;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Routes {

    private final PlantRoute plantRoute = new PlantRoute();

    public EndpointGroup getRoutes(Javalin app) {
        return () -> {
            app.routes(() -> {

                path("/", plantRoute.plantRoutingDB());
            });
        };
    }
}
