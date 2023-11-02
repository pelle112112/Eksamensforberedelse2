package dk.lyngby.dao.impl;

import dk.lyngby.dto.PlantDTO;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PlantDAOPopulator {
    //Used to store data in memory

    //Make singleton
    private static PlantDAOPopulator instance;
    public static PlantDAOPopulator getInstance(){
        if(instance == null){
            instance = new PlantDAOPopulator();
        }
        return instance;
    }



    public List<PlantDTO> plants = new ArrayList<>();

    public List<PlantDTO> populateList(){
        if(!this.plants.isEmpty()){
            return this.plants;
        }
        this.plants.add(new PlantDTO(1, "Rose", "Albertine", 400, 199.50));
        this.plants.add(new PlantDTO(2, "Bush", "Aronia", 100, 169.50));
        this.plants.add(new PlantDTO(3, "FruitAndBerries", "AromaApple", 100, 399.50));
        this.plants.add(new PlantDTO(4, "Rhododendron", "Astrid", 100, 269.50));
        this.plants.add(new PlantDTO(5, "Rose", "The DarkLady", 100, 199.50));


        return this.plants;
    }

    public List<PlantDTO> getPlants() {
        return plants;
    }

    public void setPlants(List<PlantDTO> plants) {
        this.plants = plants;
    }
    public void addPlant(PlantDTO plant){
        this.plants.add(plant);
    }

    public List<PlantDTO> getPlantsWithMaximumHeight(int maxHeight){
        List<PlantDTO> plantsWithMaximumHeight = new ArrayList<>();
        plantsWithMaximumHeight = this.plants.stream()
                .filter(plant -> plant.maxheight <= maxHeight)
                .toList();
        return plantsWithMaximumHeight;
    }

    public List<String> getPlantNames(){
        List<String> plantNames = new ArrayList<>();
        plantNames = this.plants.stream()
                .map(plant -> plant.name)
                .toList();
        return plantNames;
    }

    public List<PlantDTO> plantSorter(){
        List<PlantDTO> sortedPlants = new ArrayList<>();
        sortedPlants = this.plants.stream()
                .sorted(Comparator.comparing(plant -> plant.name))
                .toList();
        return sortedPlants;
    }

}
