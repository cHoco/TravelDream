package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.UserManager;
import it.polimi.traveldream.ejb.dtos.UserDTO;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.print.attribute.standard.Severity;
import javax.xml.bind.ValidationException;

/**
 * Created by cHoco on 14/01/14.
 */
@ManagedBean(name="registerBean")
@RequestScoped
public class RegisterBean {

    @EJB
    private UserManager userMgr;

    private UIComponent email;

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

    public UIComponent getEmail() {
        return email;
    }

    public void setEmail(UIComponent email) {
        this.email = email;
    }

    public String register() {
        if(userMgr.emailAlreadyUsed(user.getEmail())){
            FacesMessage message = new FacesMessage("Questa mail è già stata utilizzata");
            FacesContext context = FacesContext.getCurrentInstance();

            context.addMessage(email.getClientId(), message);

            return "?faces-redirect=false";
        }
        else {
            userMgr.save(user);
        }
        return "registerSuccess?faces-redirect=true";
    }
}