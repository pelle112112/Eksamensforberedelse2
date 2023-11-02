package dk.lyngby.config;

import dk.lyngby.dao.impl.PlantDAOPopulator;
import dk.lyngby.routes.Routes;
import io.javalin.Javalin;
import io.javalin.config.JavalinConfig;
import io.javalin.plugin.bundled.RouteOverviewPlugin;
import jakarta.persistence.EntityManagerFactory;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class ApplicationConfig {

    public static void configuration(JavalinConfig config) {
        config.routing.contextPath = "/api"; // base path for all routes
        config.http.defaultContentType = "application/json"; // default content type for requests
        config.plugins.register(new RouteOverviewPlugin("/")); // enables route overview at /
    }

    public static void startServer(Javalin app, int port) {
        Routes routes = new Routes();
        app.updateConfig(ApplicationConfig::configuration);
        app.routes(routes.getRoutes(app));
        app.start(port);
    }

    public static void stopServer(Javalin app) {
        app.stop();
    }


    public static void main(String[] args) {

        PlantDAOPopulator plantDAOMock = PlantDAOPopulator.getInstance();
        plantDAOMock.populateList();
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
        PopulateDB populateDB = PopulateDB.getInstance();
        populateDB.truncateDB(emf);
        populateDB.populate(emf);
        startServer(Javalin.create(), 7777);
    }
}
