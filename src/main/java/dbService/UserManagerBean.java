package dbService;


import org.apache.commons.lang3.StringUtils;
import auth.domain.UserEntity;
import userProfile.User;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@LocalBean
@Stateless
public class UserManagerBean {


    @Inject
    private EntityManager entityManager;

    // CRUD

    public boolean create(User user) {
        if (!checkValid(user)) {
            return false;
        }

        UserEntity existingUserProfile = entityManager.find(UserEntity.class, user.getEmail());
        if (existingUserProfile != null) {
            return false;
        }

        existingUserProfile = new UserEntity();
        existingUserProfile.fromDto(user);
        entityManager.persist(existingUserProfile);
        return true;
    }


    public User read (String email) {
        UserEntity userEntity = entityManager.find(UserEntity.class, email);
        if (userEntity == null) {
            return null;
        }

        if (StringUtils.isEmpty(email)) {
            return null;
        }

        return userEntity.toDto();
    }

    public boolean update(User user) {
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

    public List<User> readList(int offset, int limit ){
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

        List<User> result = new ArrayList<User>(userEntities.size());
        for (UserEntity userEntity : userEntities) {
            result.add(userEntity.toDto());
        }
        return result;
    }

    private boolean checkValid(User user) {
        return user != null || !StringUtils.isEmpty(user.getEmail());
    }
}
