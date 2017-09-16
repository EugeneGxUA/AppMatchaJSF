package dbService;


import auth.UserBean;
import domain.UserEntity;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.spi.Bean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@LocalBean
@Stateless
public class CrudManager {

    @Inject
    private EntityManager entityManager;

    @Inject
    private UserBean userBean;

        public boolean create(UserBean user) {
        if (!checkValid(user)) {
            return false;
        }

        UserEntity existingUserProfile = entityManager.find(UserEntity.class, user.getEmail());
        if (existingUserProfile == null) {
            existingUserProfile = new UserEntity();
            existingUserProfile.fromDto(user);
            entityManager.persist(existingUserProfile);
            return true;
        }


        return false;
    }


    public UserBean read (UserBean user) {
        UserEntity userEntity = entityManager.find(UserEntity.class, user.getEmail());
        if (userEntity == null) {
            return null;
        }

        if (user.getEmail().isEmpty()) {
            return null;
        }

        return userEntity.toDto(user);
    }

    public boolean update(UserBean user) {
        if (!checkValid(user)) {
            return false;
        }

        UserEntity existingUserProfile = entityManager.find(UserEntity.class, user.getEmail());
        if (existingUserProfile == null) {
            return false;
        }

        existingUserProfile.fromDto(user);
        entityManager.merge(existingUserProfile);
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

    public List<UserBean> readList(int offset, int limit ){
        if (offset < 0 || limit < 1) {
            return Collections.emptyList();
        }
        TypedQuery<UserEntity> query = entityManager.createQuery(
                "SELECT entity FROM UserEntity entity ",
                UserEntity.class);
        query.setFirstResult(offset);
        query.setMaxResults(limit);

        List<UserEntity> userEntities = query.getResultList();
        if (userEntities == null || userEntities.isEmpty()) {
            return Collections.emptyList();
        }




        List<UserBean> result = new ArrayList<UserBean>(userEntities.size());
        for (UserEntity userEntity : userEntities) {
            if (userEntity.getEmail().equals(userBean.getEmail())) {
                continue;
            }
            result.add(userEntity.toDto(new UserBean()));
        }


        return result;
    }

    private boolean checkValid(UserBean user) {
        return user != null || user.getEmail().isEmpty();
    }
}
