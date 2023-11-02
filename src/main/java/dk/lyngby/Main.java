package dk.lyngby;

import dk.lyngby.config.ApplicationConfig;
import dk.lyngby.exception.ExceptionHandler;
import dk.lyngby.exception.ApiException;
import dk.lyngby.exception.Message;
import dk.lyngby.routes.Routes;
import io.javalin.Javalin;
import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Routes routes = new Routes();
    private static final Javalin app = Javalin.create();
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    private static final ExceptionHandler EXCEPTION_HANDLER = new ExceptionHandler();
    private static int count = 0;

    private static void requestInfoHandler(Context ctx) {
        String requestInfo = ctx.req().getMethod() + " " + ctx.req().getRequestURI();
        ctx.attribute("requestInfo", requestInfo);
    }

    public static void main(String[] args) {

        app.before(Main::requestInfoHandler);
        app.updateConfig(ApplicationConfig::configuration);

        app.routes(routes.getRoutes(app));

        app.after(ctx -> LOGGER.info(" Request {} - {} was handled with status code {}", count++, ctx.attribute("requestInfo"), ctx.status()));

        app.error(400, ctx -> {
            ctx.status(400);
            ctx.json(new Message(400, "Bad Request - " + ctx.req().getRequestURI()));
        });

        app.exception(ApiException.class, EXCEPTION_HANDLER::apiExceptionHandler);
        app.exception(Exception.class, EXCEPTION_HANDLER::exceptionHandler);

        app.start(7071);
    }
}