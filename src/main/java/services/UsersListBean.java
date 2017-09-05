package services;


import dbService.UserManagerBean;
import domain.UserEntity;
import userProfile.User;

import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class UsersListBean implements Serializable {

    @EJB
    private UserManagerBean usersManagerBean;

    private User user = new User();

    public List<User> getUsers() {
        List<User> result = new ArrayList<>();
        List<UserEntity> entities = usersManagerBean.readList(0, 100);

        for (UserEntity userEntity: entities) {
            result.add(userEntity.toDto());
        }

        return result;
    }



}
