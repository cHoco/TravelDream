package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.UserManager;
import it.polimi.traveldream.ejb.dtos.UserDTO;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Created by cHoco on 14/01/14.
 */
@ManagedBean(name="registerBean")
@RequestScoped
public class RegisterBean {

    @EJB
    private UserManager userMgr;

    private UserDTO user;

    public RegisterBean() {
        user = new UserDTO();
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String register() {
        if(userMgr.emailAlreadyUsed(user.getEmail())){

        }
        else {
            userMgr.save(user);
        }
        return "home?faces-redirect=true";
    }
}