package mainPageServices;


import auth.UserBean;
import dbService.CrudManager;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class AllUsersBean implements Serializable{


    @EJB
    private CrudManager crudManager;

    @Inject
    private UserBean userBean;

    public List<UserBean> getUsers() {
        return crudManager.readList(0, 100);
    }

    public boolean doLogOut(){
       return userBean.doLogOut();
    }
}
