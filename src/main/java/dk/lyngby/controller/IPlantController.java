package dk.lyngby.controller;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public interface IPlantController {
    Handler getAll();
    Handler getById();
    Handler getByType();

    Handler add();

    Handler addPlantToReseller();
    Handler getPlantByReseller();

}
