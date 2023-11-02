package dk.lyngby.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dk.lyngby.dto.PlantDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "plants")
public class Plant {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private int id;
    private String type;
    private String name;
    private int maxheight;
    private double price;
    @ManyToOne
    //this stops the Requests from going into an infinite loop
    @JsonIgnoreProperties("plants")
    private Reseller reseller;

    public Plant(String type, String name, int maxheight, double price) {
        this.type = type;
        this.name = name;
        this.maxheight = maxheight;
        this.price = price;
    }

    public Plant(String type, String name, int maxheight, double price, Reseller reseller) {
        this.type = type;
        this.name = name;
        this.maxheight = maxheight;
        this.price = price;
        this.reseller = reseller;
    }

    public Plant(PlantDTO plantDTO){
        this.type = plantDTO.planttype;
        this.name = plantDTO.name;
        this.maxheight = plantDTO.maxheight;
        this.price = plantDTO.price;
    }

    @Override
    public String toString() {
        return "Plant{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", maxheight=" + maxheight +
                ", price=" + price +
                '}';
    }
}
