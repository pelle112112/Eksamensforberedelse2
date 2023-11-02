package dk.lyngby.dao;

import dk.lyngby.dto.PlantDTO;

import java.util.List;

public interface IDAO<T, D> {

    List<T> getAll();
    T getById(int id);
    List<T> getByType(String type);
    T add(D d);

}
