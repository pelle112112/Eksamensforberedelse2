package dk.lyngby.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "resellers")
public class Reseller {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private int id;
    private String name;
    private String address;
    private String phone;
    @OneToMany(mappedBy = "reseller", fetch = FetchType.EAGER)
    private List<Plant> plants;

    public void setPlants(List<Plant> plants) {
        if(plants != null) {
            this.plants = plants;
            for (Plant plant : plants) {
                plant.setReseller(this);
            }
        }
    }
    public void addPlant(Plant plant) {
        if ( plant != null) {
            this.plants.add(plant);
            plant.setReseller(this);
        }
    }
}
