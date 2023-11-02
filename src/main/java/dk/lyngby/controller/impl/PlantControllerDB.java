package dk.lyngby.controller.impl;

import dk.lyngby.controller.IPlantController;
import dk.lyngby.dao.impl.PlantDAO;
import dk.lyngby.dao.impl.PlantDAOMock;
import dk.lyngby.dto.PlantDTO;
import dk.lyngby.exception.ApiException;
import dk.lyngby.exception.ExceptionHandler;
import dk.lyngby.model.Plant;
import io.javalin.http.Handler;

import java.util.List;

public class PlantControllerDB implements IPlantController {

    ExceptionHandler exceptionHandler;

    @Override
    public Handler getAll() {
        PlantDAO plantDAO = PlantDAO.getInstance();
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
    @Override
    public Handler getById() {
        PlantDAO plantDAO = PlantDAO.getInstance();

        return ctx->{
            int id = Integer.parseInt(ctx.pathParam("id"));
            try {
                Plant foundPlant = plantDAO.getById(id);
                ctx.status(200);
                ctx.json(foundPlant);
            } catch (Exception e) {
                ctx.status(404);
                ApiException apiException = new ApiException(404, "No plant found with id: " + id);
                ctx.json(apiException.getMessage()+ ".  Error code: " + apiException.getStatusCode());
            }
        };
    }
    @Override
    public Handler getByType() {
        PlantDAO plantDAO = PlantDAO.getInstance();

        return ctx->{
            String type = "";
            try {
                type = ctx.pathParam("type");
                List<Plant> foundPlants = plantDAO.getByType(type);
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
    @Override
    public Handler add() {
        PlantDAO plantDAO = PlantDAO.getInstance();

        return ctx->{
            try {
                PlantDTO plant = ctx.bodyAsClass(PlantDTO.class);
                ctx.status(201);
                ctx.json(plantDAO.add(plant));
            }
            catch (Exception e){
                ctx.status(400);
                ApiException apiException = new ApiException(400, "Something went wrong with your input: " + ctx.body());
                ctx.json(apiException.getMessage()+ ".  Error code: " + apiException.getStatusCode());
            }

        };
    }

    @Override
    public Handler addPlantToReseller() {
        return null;
    }

    @Override
    public Handler getPlantByReseller() {
        return null;
    }
}
