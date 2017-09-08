package services;


import dbService.UserManagerBean;
import userProfile.User;

import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class UsersListBean implements Serializable {

    @EJB
    private UserManagerBean userManagerBean;

    public List<User> getUsers() {
        return userManagerBean.readList(0, 100);
    }
}
