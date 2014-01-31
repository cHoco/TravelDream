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

/*
 * Created by cHoco on 31/01/14.
 */
@ManagedBean(name="modificaPacchettoBean")
@ViewScoped
public class ModificaPacchettoBean {

    @EJB
    private UserContentManager userContentManager;

    @EJB
    private UserManager userManager;

    @EJB
    private ContentManager contentManager;

    private String codicePacchettoSalvato;

    private PacchettoDTO pacchettoOriginale;

    private PacchettoSalvatoDTO pacchettoSalvato;

    private Date dataPartenza;

    private Date oraPartenza;

    private Date dataRitorno;

    private Date oraRitorno;

    private List<TrasportoDTO> listaTrasporti;

    private List<HotelDTO> listaHotels;

    private List<EscursioneDTO> listaEscursioni;

    private TrasportoDTO trasportoScelto;

    private HotelDTO hotelScelto;

    private Map<String, Boolean> escursioniScelte;

    public Map<String, Boolean> getEscursioniScelte() {
        return escursioniScelte;
    }

    public void setEscursioniScelte(Map<String, Boolean> escursioniScelte) {
        this.escursioniScelte = escursioniScelte;
    }

    public HotelDTO getHotelScelto() {
        return hotelScelto;
    }

    public void setHotelScelto(HotelDTO hotelScelto) {
        this.hotelScelto = hotelScelto;
    }

    public TrasportoDTO getTrasportoScelto() {
        return trasportoScelto;
    }

    public void setTrasportoScelto(TrasportoDTO trasportoScelto) {
        this.trasportoScelto = trasportoScelto;
    }

    public List<HotelDTO> getListaHotels() {
        return listaHotels;
    }

    public void setListaHotels(List<HotelDTO> listaHotels) {
        this.listaHotels = listaHotels;
    }

    public Date getOraRitorno() {
        return oraRitorno;
    }

    public void setOraRitorno(Date oraRitorno) {
        this.oraRitorno = oraRitorno;
    }

    public List<TrasportoDTO> getListaTrasporti() {
        return listaTrasporti;
    }

    public void setListaTrasporti(List<TrasportoDTO> listaTrasporti) {
        this.listaTrasporti = listaTrasporti;
    }

    public List<EscursioneDTO> getListaEscursioni() {
        return listaEscursioni;
    }

    public void setListaEscursioni(List<EscursioneDTO> listaEscursioni) {
        this.listaEscursioni = listaEscursioni;
    }

    public Date getDataPartenza() {
        return dataPartenza;
    }

    public void setDataPartenza(Date dataPartenza) {
        this.dataPartenza = dataPartenza;
    }

    public Date getOraPartenza() {
        return oraPartenza;
    }

    public void setOraPartenza(Date oraPartenza) {
        this.oraPartenza = oraPartenza;
    }

    public Date getDataRitorno() {
        return dataRitorno;
    }

    public void setDataRitorno(Date dataRitorno) {
        this.dataRitorno = dataRitorno;
    }

    public PacchettoDTO getPacchettoOriginale() {
        return pacchettoOriginale;
    }

    public void setPacchettoOriginale(PacchettoDTO pacchettoOriginale) {
        this.pacchettoOriginale = pacchettoOriginale;
    }

    public PacchettoSalvatoDTO getPacchettoSalvato() {
        return pacchettoSalvato;
    }

    public void setPacchettoSalvato(PacchettoSalvatoDTO pacchettoSalvato) {
        this.pacchettoSalvato = pacchettoSalvato;
    }

    public String getCodicePacchettoSalvato() {
        return codicePacchettoSalvato;
    }

    public void setCodicePacchettoSalvato(String codicePacchettoSalvato) {
        this.codicePacchettoSalvato = codicePacchettoSalvato;
    }

    public ModificaPacchettoBean() {
        listaTrasporti = new ArrayList<>();
        listaHotels = new ArrayList<>();
        listaEscursioni = new ArrayList<>();
        escursioniScelte = new HashMap<>();
    }

    public void loadPacchetto() {
        pacchettoSalvato = userContentManager.getPacchettoSalvato(codicePacchettoSalvato);
        pacchettoOriginale = userContentManager.getPacchetto(pacchettoSalvato.getCodicePacchettoOriginale());

        for(String codice : pacchettoOriginale.getTrasporti().keySet()) {
            TrasportoDTO temp = contentManager.getTrasporto(codice);
            listaTrasporti.add(temp);
        }

        for(String codice : pacchettoOriginale.getHotels().keySet()) {
            HotelDTO temp = contentManager.getHotel(codice);
            listaHotels.add(temp);
        }

        for(String codice : pacchettoOriginale.getEscursioni().keySet()) {
            EscursioneDTO temp = contentManager.getEscursione(codice);
            listaEscursioni.add(temp);
            escursioniScelte.put(temp.getCodice_escursione(), false);
        }

        dataPartenza = pacchettoSalvato.getDataPartenza();
        dataRitorno = pacchettoSalvato.getDataRitorno();

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

    public String salvaModifichePacchetto() {

        PacchettoSalvatoDTO newPacchetto = new PacchettoSalvatoDTO();

        newPacchetto.setDataPartenza(dataPartenza);
        newPacchetto.setDataRitorno(dataRitorno);
        newPacchetto.setCodice_pacchettoSalvato(pacchettoSalvato.getCodice_pacchettoSalvato());
        newPacchetto.setCodicePacchettoOriginale(pacchettoSalvato.getCodicePacchettoOriginale());
        newPacchetto.setEmailUserCreatore(userManager.getUserDTO().getEmail());
        newPacchetto.getCodiciTrasporti().add(trasportoScelto.getCodice_trasporto());
        newPacchetto.getCodiciHotels().add(hotelScelto.getCodice_hotel());
        for(EscursioneDTO escursione : listaEscursioni) {
            if(escursioniScelte.get(escursione.getCodice_escursione())) {
                newPacchetto.getCodiciEscursioni().add(escursione.getCodice_escursione());
            }
        }

        userContentManager.modificaPacchettoSalvato(newPacchetto);

        return "salvaPacchettoSuccess?faces-redirect=true";
    }


}
