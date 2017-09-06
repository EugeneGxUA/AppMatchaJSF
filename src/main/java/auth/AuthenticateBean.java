package auth;

import auth.domain.*;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@Stateless
public class AuthenticateBean {

    @Inject
    private EntityManager entityManager;

    public boolean isGranted(String email, String resource) {

        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(resource)){
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
