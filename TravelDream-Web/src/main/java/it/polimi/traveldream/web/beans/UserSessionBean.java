package it.polimi.traveldream.web.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import java.io.IOException;

/**
 * Created by cHoco on 20/01/14.
 */
@ManagedBean(name = "userSessionBean")
@RequestScoped
public class UserSessionBean {

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index?faces-redirect=true";
    }

    public void checkAlreadyLoggedin(ComponentSystemEvent event) throws IOException {
        if (isLoggedIn()) {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(redirectPerRole());
        }
    }

    public boolean isLoggedIn(){
        if(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser()!=null) {
          return true;
        }

        return false;
    }

    public boolean isUser() {
        if(FacesContext.getCurrentInstance().getExternalContext().isUserInRole("USER")) {
            return true;
        }
        return false;
    }

    public boolean isAdvancedUser() {
        if(FacesContext.getCurrentInstance().getExternalContext().isUserInRole("ADVANCED_USER")) {
            return true;
        }
        return false;
    }

    public boolean isAdmin() {
        if(FacesContext.getCurrentInstance().getExternalContext().isUserInRole("ADMIN")) {
            return true;
        }
        return false;
    }

    private String redirectPerRole() {
        if(isUser()) {
            return "user/index.xhtml";
        }
        else if(isAdvancedUser()) {
            return "auser/index.xhtml";
        }
        else if(isAdmin()) {
            return "admin/index.xhtml";
        }

        return null;
    }


}
