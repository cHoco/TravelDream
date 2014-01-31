package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.ContentManager;
import it.polimi.traveldream.ejb.UserContentManager;
import it.polimi.traveldream.ejb.UserManager;
import it.polimi.traveldream.ejb.dtos.*;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import java.util.*;

/**
 * Created by cHoco on 31/01/14.
 */
@ManagedBean(name="personalizzaPacchettoBean")
@ViewScoped

public class PersonalizzaPacchettoBean {

    @EJB
    private UserContentManager userContentManager;

    @EJB
    private UserManager userManager;

    @EJB
    private ContentManager contentManager;

    private List<TrasportoDTO> listaTrasporti;

    private List<HotelDTO> listaHotels;

    private List<EscursioneDTO> listaEscursioni;

    private PacchettoDTO pacchetto;

    private String codicePacchetto;

    private String localitaPartenza;

    private Date dataPartenza;

    private Date dataRitorno;

    private Date oraPartenza;

    private Date oraRitorno;

    private TrasportoDTO trasportoScelto;

    private HotelDTO hotelScelto;

    private Map<String, Boolean> escursioniScelte;

    public PersonalizzaPacchettoBean() {
        listaTrasporti = new ArrayList<>();
        listaHotels = new ArrayList<>();
        listaEscursioni = new ArrayList<>();
        escursioniScelte = new HashMap<>();
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

    public PacchettoDTO getPacchetto() {
        return pacchetto;
    }

    public void setPacchetto(PacchettoDTO pacchetto) {
        this.pacchetto = pacchetto;
    }

    public String getCodicePacchetto() {
        return codicePacchetto;
    }

    public void setCodicePacchetto(String codicePacchetto) {
        this.codicePacchetto = codicePacchetto;
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

    public String getLocalitaPartenza() {
        return localitaPartenza;
    }

    public void setLocalitaPartenza(String localitaPartenza) {
        this.localitaPartenza = localitaPartenza;
    }

    public Date getOraPartenza() {
        return oraPartenza;
    }

    public void setOraPartenza(Date oraPartenza) {
        this.oraPartenza = oraPartenza;
    }

    public Date getOraRitorno() {
        return oraRitorno;
    }

    public void setOraRitorno(Date oraRitorno) {
        this.oraRitorno = oraRitorno;
    }

    public TrasportoDTO getTrasportoScelto() {
        return trasportoScelto;
    }

    public void setTrasportoScelto(TrasportoDTO trasportoScelto) {
        this.trasportoScelto = trasportoScelto;
    }

    public HotelDTO getHotelScelto() {
        return hotelScelto;
    }

    public void setHotelScelto(HotelDTO hotelScelto) {
        this.hotelScelto = hotelScelto;
    }

    public Map<String, Boolean> getEscursioniScelte() {
        return escursioniScelte;
    }

    public void setEscursioniScelte(Map<String, Boolean> escursioniScelte) {
        this.escursioniScelte = escursioniScelte;
    }

    public void loadPacchetto() {
        pacchetto = userContentManager.getPacchetto(codicePacchetto);

        for(String codice : pacchetto.getTrasporti().keySet()) {
            TrasportoDTO temp = contentManager.getTrasporto(codice);
            if(temp.getLocalitaPartenza().equals(localitaPartenza)) {
                listaTrasporti.add(temp);
            }
        }

        for(String codice : pacchetto.getHotels().keySet()) {
            HotelDTO temp = contentManager.getHotel(codice);
            listaHotels.add(temp);
        }

        for(String codice : pacchetto.getEscursioni().keySet()) {
            EscursioneDTO temp = contentManager.getEscursione(codice);
            listaEscursioni.add(temp);
            escursioniScelte.put(temp.getCodice_escursione(), false);
        }

        oraPartenza = (Date)dataPartenza.clone();
        oraRitorno = (Date)dataRitorno.clone();

    }

    public void setSelectedTrasporto(ValueChangeEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        trasportoScelto = context.getApplication().evaluateExpressionGet(context, "#{trasporto}", TrasportoDTO.class);
    }

    public void setSelectedHotel(ValueChangeEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        hotelScelto = context.getApplication().evaluateExpressionGet(context, "#{hotel}", HotelDTO.class);
    }

    public String salvaPacchetto() {
        PacchettoSalvatoDTO newPacchetto = new PacchettoSalvatoDTO();

        newPacchetto.setDataPartenza(dataPartenza);
        newPacchetto.setDataRitorno(dataRitorno);
        newPacchetto.setCodicePacchettoOriginale(pacchetto.getCodice_pacchetto());
        newPacchetto.setEmailUserCreatore(userManager.getUserDTO().getEmail());
        newPacchetto.getCodiciTrasporti().add(trasportoScelto.getCodice_trasporto());
        newPacchetto.getCodiciHotels().add(hotelScelto.getCodice_hotel());
        for(EscursioneDTO escursione : listaEscursioni) {
            if(escursioniScelte.get(escursione.getCodice_escursione())) {
                newPacchetto.getCodiciEscursioni().add(escursione.getCodice_escursione());
            }
        }

        userContentManager.salvaPacchetto(newPacchetto);

        return "salvaPacchettoSuccess?faces-redirect=true";
    }
}
