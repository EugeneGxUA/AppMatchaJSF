package auth;

import com.sun.imageio.plugins.jpeg.JPEGImageWriterResources;
import dbService.UserManagerBean;
import domain.UserEntity;
import userProfile.User;

import javax.ejb.Stateless;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

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
//        ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
//        String path = extContext.getRealPath("/WEB-INF/users/" + user.getEmail());
//
//        if (!new File(extContext.getRealPath("/WEB-INF/users/")).exists()) {
//            new File(extContext.getRealPath("/WEB-INF/users/")).mkdir();
//        }
//        if (!new File(path).exists()) {
//            new File(path).mkdir();
//        }

        Path path = Paths.get("/private/tmp/library.egaragul/Containers/users/" + user.getEmail());
        if (!new File("/private/tmp/library.egaragul/Containers/users/").exists()){
            new File("/private/tmp/library.egaragul/Containers/users/").mkdir();
        }
        if (!new File(path.toString()).exists()) {
            new File(path.toString()).mkdir();
        }

        entityManager.persist(existingUserProfile);

        return true;
    }
}
