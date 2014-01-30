package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.UserContentManager;
import it.polimi.traveldream.ejb.UserManager;
import it.polimi.traveldream.ejb.dtos.PacchettoDTO;
import it.polimi.traveldream.ejb.dtos.PacchettoSalvatoDTO;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.Date;

/**
 * Created by cHoco on 29/01/14.
 */
@ManagedBean(name = "mostraPacchettoBean")
@RequestScoped
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

    public void loadPacchetto() {
        if(codicePacchettoSalvato!=null) {
            pacchettoSalvatoDTO = userContentManager.getPacchettoSalvato(codicePacchettoSalvato);
            pacchettoSalvatoOriginaleDTO = userContentManager.getPacchetto(pacchettoSalvatoDTO.getCodicePacchettoOriginale());
            if(pacchettoSalvatoDTO.getIdUserCreatore()==userManager.getPrincipalEmail()) {
                salvato = true;
                if(pacchettoSalvatoDTO.isPrenotato()) {
                    prenotato = true;
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

    public void salvaPacchetto() {
        PacchettoSalvatoDTO newPacchetto = new PacchettoSalvatoDTO();

        newPacchetto.setDataPartenza(dataPartenza);
        newPacchetto.setDataRitorno(dataRitorno);
        newPacchetto.setCodicePacchettoOriginale(pacchettoDTO.getCodice_pacchetto());
    }
}
