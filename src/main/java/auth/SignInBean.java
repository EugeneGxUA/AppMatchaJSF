package auth;

import auth.domain.*;
import dbService.UserManagerBean;
import domain.UserEntity;
import userProfile.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Stateless
public class SignInBean {

    public enum SignInResult {
        INCORECT_EMAIL,
        INCORECT_PASSWORD,
        SUCCESS
    }

    @Inject
    private EntityManager entityManager;


    public  boolean doSignIn(UserBean user) {

        LocalDateTime visit;
        if (user.getEmail().isEmpty()){
            return false;
        }
        if (user.getPassword().isEmpty()) {
            return false;
        }

        UserEntity userEntity = entityManager.find(UserEntity.class, user.getEmail());
        if (userEntity == null) {
            return false;
        }

        if (!user.getPassword().equals(userEntity.getPassword())) {
            return false;
        }

        visit = userEntity.getLastVisit();
        user = userEntity.toDto();
        user.setLastVisit(visit);

        return true;
    }


    public boolean isGranted(String email, String resource) {

        if (email.isEmpty() || resource.isEmpty()){
            return false;
        }

        ResourceEntity resourceEntity = entityManager.find(ResourceEntity.class, resource);
        if (resourceEntity == null) {
            return false;
        }

        UserEntity userEntity = entityManager.find(UserEntity.class, email);
        if (userEntity == null) {
            return false;
        }

        List<PersonRoleEntity> personRole = userEntity.getPersonRoleEntities();
        if (personRole == null || personRole.isEmpty()) {
            return false;
        }

        for (PersonRoleEntity pRole: personRole) {
            Role role = pRole.getRole();
            if (role == null) {
                continue;
            }

            List<Rights> rightsEntities =  role.getRights();
            for (Rights right: rightsEntities) {
                if (right == null) {
                    continue;
                }
                ResourceEntity rightResource = right.getResourceEntity();
                if (rightResource.getId().equalsIgnoreCase(resource)) {
                    return true;
                }
            }

        }

        return false;
    }

}
