package dbService;


import auth.UserBean;
import org.apache.commons.lang3.StringUtils;
import domain.UserEntity;
import userProfile.User;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@LocalBean
@Stateless
public class UserManagerBean {


    @Inject
    EntityManager entityManager;

    // CRUD

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


    public UserBean read (String email) {
        UserEntity userEntity = entityManager.find(UserEntity.class, email);
        if (userEntity == null) {
            return null;
        }

        if (StringUtils.isEmpty(email)) {
            return null;
        }

        return userEntity.toDto();
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
            result.add(userEntity.toDto());
        }
        return result;
    }

    private boolean checkValid(UserBean user) {
        return user != null || !StringUtils.isEmpty(user.getEmail());
    }
}
