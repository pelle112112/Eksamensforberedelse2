package dk.lyngby.dao.impl;

import dk.lyngby.config.HibernateConfig;
import dk.lyngby.dao.IDAO;
import dk.lyngby.dto.PlantDTO;
import dk.lyngby.model.Plant;
import dk.lyngby.model.Reseller;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class PlantDAO implements IDAO<Plant, PlantDTO> {

    //Singleton
    private static PlantDAO instance;
    public static PlantDAO getInstance(){
        if(instance == null){
            instance = new PlantDAO();
        }
        return instance;
    }
    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
    @Override
    public List<Plant> getAll() {
        try(EntityManager em = emf.createEntityManager()){
            return em.createQuery("SELECT p FROM Plant p", Plant.class).getResultList();
        }
    }

    @Override
    public Plant getById(int id) {
        try (EntityManager em = emf.createEntityManager()){
            return em.find(Plant.class, id);
        }

    }

    @Override
    public List<Plant> getByType(String type) {
        try(EntityManager em = emf.createEntityManager()){
            return em.createQuery("SELECT p FROM Plant p WHERE p.type = :type", Plant.class)
                    .setParameter("type", type)
                    .getResultList();
        }
    }

    @Override
    public Plant add(PlantDTO plantDTO) {
        try (EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
                Plant plant = new Plant(plantDTO);
                em.persist(plant);
            em.getTransaction().commit();
            return plant;
        }
    }
    public Plant deletePlant(int id){
        try (EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
                Plant plant = em.find(Plant.class, id);
                em.remove(plant);
            em.getTransaction().commit();
            return plant;
        }
    }
    public Reseller addPlantToReseller(int resellerId, int plantId){
        try (EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
                Reseller reseller = em.find(Reseller.class, resellerId);
                Plant plant = em.find(Plant.class, plantId);
                reseller.addPlant(plant);
                em.merge(reseller);
            em.getTransaction().commit();
            return reseller;
        }
    }
    public List<Plant> getPlantsByReseller(int resellerId){
        try (EntityManager em = emf.createEntityManager()){
            return em.createQuery("SELECT p FROM Plant p WHERE p.reseller.id = :resellerId", Plant.class)
                    .setParameter("resellerId", resellerId)
                    .getResultList();

        }
    }
}
