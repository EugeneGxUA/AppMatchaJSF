package auth;

import auth.domain.*;
import domain.UserEntity;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
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

        visit = user.getLastVisit();
        user = userEntity.toDto(user);
        user.setLastVisit(visit);
        user.setAuthenticate(true);

        return true;
    }

    public boolean doMatcha(UserBean user) {
        if (user.isActive()) {
            return true;
        }
        return false;
    }


}
