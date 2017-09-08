package dbService;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class EntityManagerProducer {

    @PersistenceContext(unitName = "dbConnection")
    private EntityManager entityManager;

    @Produces
    public EntityManager createEntityManager(){
        return entityManager;
    }

}
