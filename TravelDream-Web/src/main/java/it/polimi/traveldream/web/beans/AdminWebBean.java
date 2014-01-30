package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.UserManager;
import it.polimi.traveldream.ejb.dtos.UserDTO;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus on 21/01/14.
 */
@ManagedBean(name = "adminWebBean")
@RequestScoped
public class AdminWebBean {

    @EJB
    private UserManager userMgr;

    private UIComponent email;

    private UserDTO au;

    private List<UserDTO> listaAU;

    public AdminWebBean() {
        au = new UserDTO();
        listaAU = new ArrayList<>();
    }

    public UserDTO getAu() {
        return au;
    }

    public void setAu(UserDTO user) {
        this.au = user;
    }

    public UIComponent getEmail() {
        return email;
    }

    public void setEmail(UIComponent email) {
        this.email = email;
    }

    public List<UserDTO> getListaAU() {
        return listaAU;
    }

    public void setListaAU(List<UserDTO> listaAU) {
        this.listaAU = listaAU;
    }

    public String aggiungiAu() {
        if(userMgr.emailAlreadyUsed(au.getEmail())){
            FacesMessage message = new FacesMessage("Questa mail è già stata utilizzata");
            FacesContext context = FacesContext.getCurrentInstance();

            context.addMessage(email.getClientId(), message);

            return "?faces-redirect=false";
        }
        else {
            userMgr.saveAu(au);
        }

        return "insertSuccess?faces-redirect=true";
    }

    public void showAuList(){

        listaAU = userMgr.getAdvanceUsers();

    }
}
