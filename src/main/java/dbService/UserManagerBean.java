package dbService;


import org.apache.commons.lang3.StringUtils;
import domain.UserEntity;
import org.hibernate.SessionFactory;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

@LocalBean
@Stateless
public class UserManagerBean {


    @PersistenceContext(unitName = "dbConnection")
    private EntityManager entityManager;

    // CRUD

    public boolean create(UserEntity userProfile) {
        if (!checkValid(userProfile)) {
            return false;
        }

        UserEntity existingUserProfile = entityManager.find(UserEntity.class, userProfile.getEmail());
        if (existingUserProfile != null) {
            return false;
        }

        entityManager.persist(userProfile);
        return true;
    }


    public UserEntity read (String email) {
        if (StringUtils.isEmpty(email)) {
            return null;
        }

        return entityManager.find(UserEntity.class, email);
    }

    public boolean update(UserEntity userProfile) {
        if (!checkValid(userProfile)) {
            return false;
        }

        UserEntity existingUserProfile = entityManager.find(UserEntity.class, userProfile.getEmail());
        if (existingUserProfile == null) {
            return false;
        }

        entityManager.merge(userProfile);
        return true;
    }

    public boolean delete(String email) {
        UserEntity existUser = entityManager.find(UserEntity.class, email);
       if (existUser == null) {
           return false;
       }
        entityManager.remove(existUser);
        return true;
    }

    public List<UserEntity> readList(int offset, int limit ){
        if (offset < 0 || limit < 1) {
            return Collections.emptyList();
        }
        TypedQuery<UserEntity> query = entityManager.createQuery(
                "SELECT entity FROM UserEntity entity ",
                UserEntity.class);
        query.setFirstResult(offset);
        query.setMaxResults(limit);

        return query.getResultList();
    }

    private boolean checkValid(UserEntity userProfile) {
        return userProfile != null || !StringUtils.isEmpty(userProfile.getEmail());
    }
}
