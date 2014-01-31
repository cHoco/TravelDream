package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.ContentManager;
import it.polimi.traveldream.ejb.UserContentManager;
import it.polimi.traveldream.ejb.UserManager;
import it.polimi.traveldream.ejb.dtos.*;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.text.SimpleDateFormat;
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

    @EJB
    ContentManager contentManager;

    private String codicePacchetto;

    private String codicePacchettoSalvato;

    private PacchettoDTO pacchettoDTO;

    private PacchettoSalvatoDTO pacchettoSalvatoDTO;

    private PacchettoDTO pacchettoSalvatoOriginaleDTO;

    private Date dataPartenza;

    private Date dataRitorno;

    private String stringDataPartenza;

    private String stringDataRitorno;

    private String localitaPartenza;

    private boolean cercato;

    private boolean salvato;

    private boolean invitato;

    private boolean prenotato;

    private boolean partecipato;

    private List<TrasportoDTO> listaTrasporti;

    private List<HotelDTO> listaHotels;

    private List<EscursioneDTO> listaEscursioni;

    public MostraPacchettoBean() {
        pacchettoDTO = new PacchettoDTO();
        pacchettoSalvatoDTO = new PacchettoSalvatoDTO();
        listaEscursioni = new ArrayList<>();
        listaHotels = new ArrayList<>();
        listaTrasporti = new ArrayList<>();
        cercato = false;
        salvato = false;
        invitato = false;
        prenotato = false;
        partecipato = false;
    }

    public boolean isPartecipato() {
        return partecipato;
    }

    public void setPartecipato(boolean partecipato) {
        this.partecipato = partecipato;
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

    public String getStringDataPartenza() {
        String tempDataPartenza = new SimpleDateFormat("yyyyMMddHHmmzzz").format(dataPartenza);
        return tempDataPartenza;
    }

    public String getStringDataRitorno() {
        String tempDataRitorno = new SimpleDateFormat("yyyyMMddHHmmzzz").format(dataRitorno);
        return tempDataRitorno;
    }

    public void setStringDataPartenza(String stringDataPartenza) {
        this.stringDataPartenza = stringDataPartenza;
    }

    public void setStringDataRitorno(String stringDataRitorno) {
        this.stringDataRitorno = stringDataRitorno;
    }

    public List<TrasportoDTO> getListaTrasporti() {
        return listaTrasporti;
    }

    public void setListaTrasporti(List<TrasportoDTO> listaTrasporti) {
        this.listaTrasporti = listaTrasporti;
    }

    public List<HotelDTO> getListaHotels() {
        return listaHotels;
    }

    public void setListaHotels(List<HotelDTO> listaHotels) {
        this.listaHotels = listaHotels;
    }

    public List<EscursioneDTO> getListaEscursioni() {
        return listaEscursioni;
    }

    public void setListaEscursioni(List<EscursioneDTO> listaEscursioni) {
        this.listaEscursioni = listaEscursioni;
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

            for(String codice : pacchettoSalvatoDTO.getCodiciTrasporti()) {
                TrasportoDTO temp = contentManager.getTrasporto(codice);
                listaTrasporti.add(temp);
            }

            for(String codice : pacchettoSalvatoDTO.getCodiciHotels()) {
                HotelDTO temp = contentManager.getHotel(codice);
                listaHotels.add(temp);
            }

            for(String codice : pacchettoSalvatoDTO.getCodiciEscursioni()) {
                EscursioneDTO temp = contentManager.getEscursione(codice);
                listaEscursioni.add(temp);
            }

            if(isLoggedIn()) {
                if(pacchettoSalvatoDTO.getEmailUserCreatore().equals(userManager.getUserDTO().getEmail())) {
                    salvato = true;
                    if(pacchettoSalvatoDTO.isPrenotato()) {
                        prenotato = true;
                    }
                }
                else {
                    for(UserDTO userDTO : pacchettoSalvatoDTO.getUsersPartecipanti()) {
                        System.out.println(userDTO);
                        if(userDTO.getEmail().equals(userManager.getUserDTO().getEmail())) {
                            partecipato = true;
                        }
                        else{
                            invitato = true;
                        }
                    }
                }
            }
            else {
                invitato = true;
            }
        } else if(codicePacchetto!=null) {
            pacchettoDTO = userContentManager.getPacchetto(codicePacchetto);
            for(String codice : pacchettoDTO.getTrasporti().keySet()) {
                TrasportoDTO temp = contentManager.getTrasporto(codice);
                if(temp.getLocalitaPartenza().equals(localitaPartenza)) {
                    listaTrasporti.add(temp);
                }
            }

            for(String codice : pacchettoDTO.getHotels().keySet()) {
                HotelDTO temp = contentManager.getHotel(codice);
                listaHotels.add(temp);
            }

            for(String codice : pacchettoDTO.getEscursioni().keySet()) {
                EscursioneDTO temp = contentManager.getEscursione(codice);
                listaEscursioni.add(temp);
            }
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

    public String prenotaPacchetto() {
        userContentManager.prenotaPacchetto(pacchettoSalvatoDTO);

        return "user/prenotaPacchettoSuccess?faces-redirect=true";
    }

    public String partecipaPacchetto() {
        userContentManager.aggiungiPartecipazione(pacchettoSalvatoDTO);

        return "user/partecipaPacchettoSuccess?faces-redirect=true";
    }
}
