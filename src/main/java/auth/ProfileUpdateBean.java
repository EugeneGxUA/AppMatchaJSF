package auth;


import domain.UserEntity;
import service.UploadService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Stateless
public class ProfileUpdateBean {

    @Inject
    private EntityManager entityManager;

    public boolean doProfileUpdate(UserBean user) {
        if (!user.getEmail().isEmpty() &&
                !user.getAvatarFile().getName().isEmpty() &&
                !user.getBio().isEmpty() &&
                !user.getFirstName().isEmpty() &&
                !user.getLastName().isEmpty() &&
                !user.getCity().isEmpty() &&
                !user.getCountry().isEmpty() &&
                !user.getSexOrientation().isEmpty() &&
                !user.getBirth().isEmpty() &&
                !user.getTags().isEmpty()) {

            UserEntity existUser = entityManager.find(UserEntity.class, user.getEmail());
            if (existUser == null) {
                return false;
            }
            UploadService uploadService = new UploadService();

            uploadService.uploadByForm(user, user.getAvatarFile());
            user.setActive(true);
            existUser.fromDto(user);
            entityManager.merge(existUser);
            return true;
        }
        return false;
    }
}
