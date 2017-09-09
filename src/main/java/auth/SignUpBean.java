package auth;

import dbService.UserManagerBean;
import domain.UserEntity;
import userProfile.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Stateless
public class SignUpBean {

    @Inject
    private EntityManager entityManager;

    public boolean doSignUp(UserBean user) {
        UserEntity existingUserProfile = entityManager.find(UserEntity.class, user.getEmail());

        if (existingUserProfile != null) {
            return false;
        }
        existingUserProfile = new UserEntity();
        existingUserProfile.fromDto(user);
        entityManager.persist(existingUserProfile);
        return true;
    }
}
