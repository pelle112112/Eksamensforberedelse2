package dk.lyngby.dao.impl;

import dk.lyngby.dao.IDAO;
import dk.lyngby.dto.PlantDTO;
import dk.lyngby.exception.ApiException;
import dk.lyngby.exception.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

public class PlantDAOMock implements IDAO<PlantDTO, PlantDTO> {

    //Singleton
    private static PlantDAOMock instance;
    private final ExceptionHandler exceptionHandler = new ExceptionHandler();
    private ApiException apiException;
    public static PlantDAOMock getInstance(){
        if(instance == null){
            instance = new PlantDAOMock();
        }
        return instance;
    }


    @Override
    public List<PlantDTO> getAll() {
        PlantDAOPopulator plantDAOMock = PlantDAOPopulator.getInstance();

        return plantDAOMock.getPlants();
    }

    @Override
    public PlantDTO getById(int id) {
        PlantDAOPopulator plantDAOMock = PlantDAOPopulator.getInstance();
        List<PlantDTO> plants = plantDAOMock.getPlants();

        return plants.stream()
                .filter(plant -> plant.id == id)
                .findFirst()
                .orElse(null);

    }

    @Override
    public List<PlantDTO> getByType(String type) {
        PlantDAOPopulator plantDAOMock = PlantDAOPopulator.getInstance();
        List<PlantDTO> plants = plantDAOMock.getPlants();
        List<PlantDTO> plantsWithType = new ArrayList<>();
        plantsWithType =  plants.stream()
                .filter(plant -> plant.planttype.equals(type))
                .toList();
        return plantsWithType;
    }

    @Override
    public PlantDTO add(PlantDTO plant) {
        PlantDAOPopulator plantDAOMock = PlantDAOPopulator.getInstance();

        plantDAOMock.addPlant(plant);
        return plant;
    }
}
