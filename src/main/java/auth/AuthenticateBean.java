package auth;

import auth.domain.*;
import dbService.UserManagerBean;
import domain.UserEntity;
import org.apache.commons.lang3.StringUtils;
import userProfile.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@Stateless
public class AuthenticateBean {

    public enum SignInResult {
        INCORECT_EMAIL,
        INCORECT_PASSWORD,
        SUCCESS
    }

    @Inject
    private EntityManager entityManager;


    public  SignInResult doSignIn(String email, String password) {
        if (email == null || email.isEmpty()){
            return SignInResult.INCORECT_EMAIL;
        }
        if (password.isEmpty()) {
            return SignInResult.INCORECT_PASSWORD;
        }

        UserEntity userEntity = entityManager.find(UserEntity.class, email);
        if (userEntity == null) {
            return SignInResult.INCORECT_EMAIL;
        }

        if (!password.equals(userEntity.getPassword())) {
            return SignInResult.INCORECT_PASSWORD;
        }

        return SignInResult.SUCCESS;
    }

    public void doSingUp(String email, String pass, String firstName, String lastName, int gender) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(pass);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setGender(gender);

        UserManagerBean userManagerBean = new UserManagerBean();
        userManagerBean.create(user);
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
            RoleEntity role = pRole.getRoleEntity();
            if (role == null) {
                continue;
            }

            List<RightsEntity> rightsEntities =  role.getRights();
            for (RightsEntity right: rightsEntities) {
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
