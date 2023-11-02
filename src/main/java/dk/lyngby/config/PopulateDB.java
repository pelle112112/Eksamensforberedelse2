package dk.lyngby.config;

import dk.lyngby.model.Plant;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class PopulateDB {
    //singleton
    private static PopulateDB instance;
    public static PopulateDB getInstance(){
        if(instance == null){
            instance = new PopulateDB();
        }
        return instance;
    }
    public void populate(EntityManagerFactory emf){
        try (EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.createNativeQuery("INSERT INTO resellers (id, name, address, phone) VALUES (1, 'Lyngby Plancecenter', 'Firskovvej 18','33212334')").executeUpdate();
            em.createNativeQuery("INSERT INTO resellers (id, name, address, phone) VALUES (2, 'Glostrup Planter', 'Tværvej 35','32233232')").executeUpdate();
            em.createNativeQuery("INSERT INTO resellers (id, name, address, phone) VALUES (3, 'Holbæk Planteskole', 'Stenhusvej 49','59430945')").executeUpdate();
            em.createNativeQuery("INSERT INTO plants (id, type, name, maxheight ,price, reseller_id) VALUES (1, 'Rose', 'Albertine',400,199.50, 1)").executeUpdate();
            em.createNativeQuery("INSERT INTO plants (id, type, name, maxheight ,price, reseller_id) VALUES (2, 'Bush', 'Aronia',200,169.50, 1)").executeUpdate();
            em.createNativeQuery("INSERT INTO plants (id, type, name, maxheight ,price, reseller_id) VALUES (3, 'FruitAndBerries', 'AromaApple',350,399.50, 2)").executeUpdate();
            em.createNativeQuery("INSERT INTO plants (id, type, name, maxheight ,price, reseller_id) VALUES (4, 'Rhododendron', 'Astrid',40,269.50, 2)").executeUpdate();
            em.createNativeQuery("INSERT INTO plants (id, type, name, maxheight ,price, reseller_id) VALUES (5, 'Rose', 'The DarkLady',100,199.50, 3)").executeUpdate();
            em.getTransaction().commit();
        }
    }
    public void truncateDB(EntityManagerFactory emf){
        try (EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            //Truncate with cascade
            //check if DB is empty
            if(em.createQuery("SELECT p FROM Plant p", Plant.class).getResultList().isEmpty()){
                return;
            }
            em.createNativeQuery("TRUNCATE TABLE resellers CASCADE").executeUpdate();
            em.createNativeQuery("TRUNCATE TABLE plants CASCADE").executeUpdate();
            em.getTransaction().commit();

        }
    }
}
