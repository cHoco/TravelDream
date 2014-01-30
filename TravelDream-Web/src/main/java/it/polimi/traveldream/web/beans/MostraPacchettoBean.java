package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.UserContentManager;
import it.polimi.traveldream.ejb.UserManager;
import it.polimi.traveldream.ejb.dtos.PacchettoDTO;
import it.polimi.traveldream.ejb.dtos.PacchettoSalvatoDTO;
import it.polimi.traveldream.ejb.dtos.TrasportoDTO;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by cHoco on 29/01/14.
 */
@ManagedBean(name = "mostraPacchettoBean")
@ViewScoped
public class MostraPacchettoBean {

    @EJB
    UserContentManager userContentManager;

    @EJB
    UserManager userManager;

    String codicePacchetto;

    String codicePacchettoSalvato;

    PacchettoDTO pacchettoDTO;

    PacchettoSalvatoDTO pacchettoSalvatoDTO;

    PacchettoDTO pacchettoSalvatoOriginaleDTO;

    Date dataPartenza;

    Date dataRitorno;

    String localitaPartenza;

    boolean cercato;

    boolean salvato;

    boolean invitato;

    boolean prenotato;

    public MostraPacchettoBean() {
        pacchettoDTO = new PacchettoDTO();
        pacchettoSalvatoDTO = new PacchettoSalvatoDTO();
        cercato = false;
        salvato = false;
        invitato = false;
        prenotato = false;
    }

    public String getCodicePacchetto() {
        return codicePacchetto;
    }

    public void setCodicePacchetto(String codicePacchetto) {
        this.codicePacchetto = codicePacchetto;
    }

    public String getCodicePacchettoSalvato() {
        return codicePacchettoSalvato;
    }

    public void setCodicePacchettoSalvato(String codicePacchettoSalvato) {
        this.codicePacchettoSalvato = codicePacchettoSalvato;
    }

    public PacchettoDTO getPacchettoDTO() {
        return pacchettoDTO;
    }

    public void setPacchettoDTO(PacchettoDTO pacchettoDTO) {
        this.pacchettoDTO = pacchettoDTO;
    }

    public PacchettoSalvatoDTO getPacchettoSalvatoDTO() {
        return pacchettoSalvatoDTO;
    }

    public void setPacchettoSalvatoDTO(PacchettoSalvatoDTO pacchettoSalvatoDTO) {
        this.pacchettoSalvatoDTO = pacchettoSalvatoDTO;
    }

    public boolean isCercato() {
        return cercato;
    }

    public void setCercato(boolean cercato) {
        this.cercato = cercato;
    }

    public boolean isSalvato() {
        return salvato;
    }

    public void setSalvato(boolean salvato) {
        this.salvato = salvato;
    }

    public boolean isInvitato() {
        return invitato;
    }

    public void setInvitato(boolean invitato) {
        this.invitato = invitato;
    }

    public Date getDataPartenza() {
        return dataPartenza;
    }

    public void setDataPartenza(Date dataPartenza) {
        this.dataPartenza = dataPartenza;
    }

    public Date getDataRitorno() {
        return dataRitorno;
    }

    public void setDataRitorno(Date dataRitorno) {
        this.dataRitorno = dataRitorno;
    }

    public boolean isPrenotato() {
        return prenotato;
    }

    public void setPrenotato(boolean prenotato) {
        this.prenotato = prenotato;
    }

    public PacchettoDTO getPacchettoSalvatoOriginaleDTO() {
        return pacchettoSalvatoOriginaleDTO;
    }

    public void setPacchettoSalvatoOriginaleDTO(PacchettoDTO pacchettoSalvatoOriginaleDTO) {
        this.pacchettoSalvatoOriginaleDTO = pacchettoSalvatoOriginaleDTO;
    }

    public String getLocalitaPartenza() {
        return localitaPartenza;
    }

    public void setLocalitaPartenza(String localitaPartenza) {
        this.localitaPartenza = localitaPartenza;
    }

    public boolean isLoggedIn(){
        if(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser()!=null) {
            return true;
        }

        return false;
    }

    public void loadPacchetto() {
        if(codicePacchettoSalvato!=null) {
            pacchettoSalvatoDTO = userContentManager.getPacchettoSalvato(codicePacchettoSalvato);
            pacchettoSalvatoOriginaleDTO = userContentManager.getPacchetto(pacchettoSalvatoDTO.getCodicePacchettoOriginale());

            if(isLoggedIn()) {
                if(pacchettoSalvatoDTO.getEmailUserCreatore().equals(userManager.getUserDTO().getEmail())) {
                    salvato = true;
                    if(pacchettoSalvatoDTO.isPrenotato()) {
                        prenotato = true;
                    }
                }
            }
            else {
                invitato = true;
            }
        } else if(codicePacchetto!=null) {
            pacchettoDTO = userContentManager.getPacchetto(codicePacchetto);
            cercato = true;
        }
    }

    public String salvaPacchettoPredefinito() {
        PacchettoSalvatoDTO newPacchetto = new PacchettoSalvatoDTO();

        newPacchetto.setDataPartenza(dataPartenza);
        newPacchetto.setDataRitorno(dataRitorno);
        newPacchetto.setCodicePacchettoOriginale(pacchettoDTO.getCodice_pacchetto());
        newPacchetto.setEmailUserCreatore(userManager.getUserDTO().getEmail());

        userContentManager.salvaPacchettoPredefinito(newPacchetto, localitaPartenza);

        return "user/salvaPacchettoSuccess?faces-redirect=true";
    }
}
