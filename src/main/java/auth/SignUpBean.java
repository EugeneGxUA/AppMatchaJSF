package auth;

import com.sun.imageio.plugins.jpeg.JPEGImageWriterResources;
import dbService.UserManagerBean;
import domain.UserEntity;
import userProfile.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.File;

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
        if (!new File(getClass().getClassLoader().getResource("users") + user.getEmail()).exists()) {
            new File(getClass().getClassLoader().getResource("users") + user.getEmail()).mkdir();
        }
        entityManager.persist(existingUserProfile);

        return true;
    }
}
