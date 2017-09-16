package auth;

import domain.UserEntity;

import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Stateless
public class LogOutBean {

    @Inject
    private EntityManager entityManager;

    public boolean doLogOut(UserBean user) {

        user.setAuthenticate(false);

        UserEntity existUser = entityManager.find(UserEntity.class, user.getEmail());
        if (existUser != null) {
            existUser.fromDto(user);
            entityManager.merge(existUser);
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            return true;
        }

        return false;
    }
}
