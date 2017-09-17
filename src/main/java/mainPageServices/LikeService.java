package mainPageServices;

import auth.UserBean;
import auth.domain.LikesEntity;
import domain.UserEntity;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import java.io.Serializable;

@Named
@SessionScoped
public class LikeService implements Serializable{


    @Inject
    private EntityManager entityManager;

    @Inject
    private UserBean user;

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean doLike(String mail) {

        UserEntity userEntity = entityManager.find(UserEntity.class, mail);

        if (!userEntity.getLikesEntities().isEmpty()) {
            for (LikesEntity likeEntity : userEntity.getLikesEntities()) {
                if (likeEntity.getWhoLike().equals(user.getEmail())) return false;
            }
        }
        LikesEntity like = new LikesEntity();
        like.setWhoLike(user.getEmail());
        userEntity.getLikesEntities().add(like);
        entityManager.merge(userEntity);
        return true;
    }

    public boolean doDislike(String mail) {

        UserEntity userEntity = entityManager.find(UserEntity.class, mail);

        if (!userEntity.getLikesEntities().isEmpty()) {
            for (LikesEntity likeEntity : userEntity.getLikesEntities()) {
                if (likeEntity.getWhoLike().equals(user.getEmail())) return false;
            }
        }
        LikesEntity like = new LikesEntity();
        like.setWhoLike(user.getEmail());
        userEntity.getLikesEntities().add(like);
        entityManager.merge(userEntity);
        return true;
    }
}
