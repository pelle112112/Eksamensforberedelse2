package dk.lyngby.dto;

public class PlantDTO {
    public int id;
    public String planttype;
    public String name;
    public int maxheight;
    public double price;

    public PlantDTO(String planttype, String name, int maxheight, double price) {
        this.planttype = planttype;
        this.name = name;
        this.maxheight = maxheight;
        this.price = price;
    }

    public PlantDTO(int id, String planttype, String name, int maxheight, double price) {
        this.id = id;
        this.planttype = planttype;
        this.name = name;
        this.maxheight = maxheight;
        this.price = price;
    }


    public PlantDTO() {
    }
}
