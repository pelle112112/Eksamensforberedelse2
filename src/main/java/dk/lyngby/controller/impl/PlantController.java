package dk.lyngby.controller.impl;

import dk.lyngby.dao.impl.PlantDAOMock;
import dk.lyngby.dto.PlantDTO;
import dk.lyngby.exception.ApiException;
import dk.lyngby.exception.ExceptionHandler;
import io.javalin.http.Handler;

import java.util.List;


public class PlantController {
    ExceptionHandler exceptionHandler;
    public Handler getAll() {
        PlantDAOMock plantDAO = PlantDAOMock.getInstance();

        return ctx->{
            if(plantDAO.getAll().isEmpty()){
                ApiException apiException = new ApiException(404, "No plants found");
                ctx.status(404);
                ctx.json(apiException.getMessage()+ ".  Error code: " + apiException.getStatusCode());
            }
            else {
                ctx.status(200);
                ctx.json(plantDAO.getAll());
            }
        };
    }
    public Handler getById() {
        PlantDAOMock plantDAO = PlantDAOMock.getInstance();

        return ctx->{
            int id = Integer.parseInt(ctx.pathParam("id"));
            try {
                PlantDTO foundPlant = plantDAO.getById(id);
                ctx.status(200);
                ctx.json(foundPlant);
            } catch (Exception e) {
                ctx.status(404);
                ApiException apiException = new ApiException(404, "No plant found with id: " + id);
                ctx.json(apiException.getMessage()+ ".  Error code: " + apiException.getStatusCode());
            }
        };
    }
    public Handler getByType() {
        PlantDAOMock plantDAO = PlantDAOMock.getInstance();

        return ctx->{
            String type = "";
            try {
                type = ctx.pathParam("type");
                List<PlantDTO> foundPlants = plantDAO.getByType(type);
                if(foundPlants.isEmpty()){
                    ctx.status(404);
                    ApiException apiException = new ApiException(404, "No plants found with type: " + type);
                    ctx.json(apiException.getMessage()+ ".  Error code: " + apiException.getStatusCode());
                }
                else {
                    ctx.status(200);
                    ctx.json(plantDAO.getByType(type));
                }
            } catch (Exception e) {
                ctx.status(400);
                ApiException apiException = new ApiException(400, "Something went wrong with your input: " + type);
                ctx.json(apiException.getMessage()+ ".  Error code: " + apiException.getStatusCode());
            }
        };
    }
    public Handler add() {
        PlantDAOMock plantDAO = PlantDAOMock.getInstance();

        return ctx->{
            try {
                PlantDTO plant = ctx.bodyAsClass(PlantDTO.class);
                ctx.status(201);
                System.out.println("test");
                ctx.json(plantDAO.add(plant));
            }
            catch (Exception e){
                ctx.status(400);
                ApiException apiException = new ApiException(400, "Something went wrong with your input: " + ctx.body());
                ctx.json(apiException.getMessage()+ ".  Error code: " + apiException.getStatusCode());
            }

        };
    }
}
