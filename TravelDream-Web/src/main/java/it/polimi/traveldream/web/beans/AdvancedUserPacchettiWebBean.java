package it.polimi.traveldream.web.beans;


import it.polimi.traveldream.ejb.ContentManager;
import it.polimi.traveldream.ejb.dtos.EscursioneDTO;
import it.polimi.traveldream.ejb.dtos.HotelDTO;
import it.polimi.traveldream.ejb.dtos.PacchettoDTO;
import it.polimi.traveldream.ejb.dtos.TrasportoDTO;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by cHoco on 23/01/14.
 */

@ManagedBean(name = "advancedUserPacchettiWebBean")
@ViewScoped

public class AdvancedUserPacchettiWebBean {

    @EJB
    ContentManager contentManager;

    private PacchettoDTO pacchettoDTO;

    private String codiceTrasportoPredefinito;

    private String codiceHotelPredefinito;

    private String codiceEscursionePredefinita;

    private List<TrasportoDTO> listaTrasportiPredefiniti;

    private List<HotelDTO> listaHotelsPredefiniti;

    private List<EscursioneDTO> listaEscursioniPredefinite;

    private String codiceTrasporto;

    private String codiceHotel;

    private String codiceEscursione;

    private List<TrasportoDTO> listaTrasporti;

    private List<HotelDTO> listaHotels;

    private List<EscursioneDTO> listaEscursioni;

    public AdvancedUserPacchettiWebBean() {
        pacchettoDTO = new PacchettoDTO();
        listaTrasporti = new ArrayList<>();
        listaHotels = new ArrayList<>();
        listaEscursioni = new ArrayList<>();
        listaEscursioniPredefinite = new ArrayList<>();
        listaTrasportiPredefiniti = new ArrayList<>();
        listaHotelsPredefiniti = new ArrayList<>();
    }

    public PacchettoDTO getPacchettoDTO() {
        return pacchettoDTO;
    }

    public void setPacchettoDTO(PacchettoDTO pacchettoDTO) {
        this.pacchettoDTO = pacchettoDTO;
    }

    public String getCodiceTrasportoPredefinito() {
        return codiceTrasportoPredefinito;
    }

    public void setCodiceTrasportoPredefinito(String codiceTrasportoPredefinito) {
        this.codiceTrasportoPredefinito = codiceTrasportoPredefinito;
    }

    public String getCodiceHotelPredefinito() {
        return codiceHotelPredefinito;
    }

    public void setCodiceHotelPredefinito(String codiceHotelPredefinito) {
        this.codiceHotelPredefinito = codiceHotelPredefinito;
    }

    public String getCodiceEscursionePredefinita() {
        return codiceEscursionePredefinita;
    }

    public void setCodiceEscursionePredefinita(String codiceEscursionePredefinita) {
        this.codiceEscursionePredefinita = codiceEscursionePredefinita;
    }

    public List<TrasportoDTO> getListaTrasportiPredefiniti() {
        return listaTrasportiPredefiniti;
    }

    public void setListaTrasportiPredefiniti(List<TrasportoDTO> listaTrasportiPredefiniti) {
        this.listaTrasportiPredefiniti = listaTrasportiPredefiniti;
    }

    public List<HotelDTO> getListaHotelsPredefiniti() {
        return listaHotelsPredefiniti;
    }

    public void setListaHotelsPredefiniti(List<HotelDTO> listaHotelsPredefiniti) {
        this.listaHotelsPredefiniti = listaHotelsPredefiniti;
    }

    public List<EscursioneDTO> getListaEscursioniPredefinite() {
        return listaEscursioniPredefinite;
    }

    public void setListaEscursioniPredefinite(List<EscursioneDTO> listaEscursioniPredefinite) {
        this.listaEscursioniPredefinite = listaEscursioniPredefinite;
    }

    public String getCodiceHotel() {
        return codiceHotel;
    }

    public void setCodiceHotel(String codiceHotel) {
        this.codiceHotel = codiceHotel;
    }

    public String getCodiceEscursione() {
        return codiceEscursione;
    }

    public void setCodiceEscursione(String codiceEscursione) {
        this.codiceEscursione = codiceEscursione;
    }

    public String getCodiceTrasporto() {
        return codiceTrasporto;
    }

    public void setCodiceTrasporto(String codiceTrasporto) {
        this.codiceTrasporto = codiceTrasporto;
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

    public void aggiungiTraspostoPredefinito() {
        listaTrasportiPredefiniti.add(contentManager.getTrasporto(codiceTrasportoPredefinito));
        codiceTrasportoPredefinito = "";
    }

    public void aggiungiHotelPredefinito() {
        listaHotelsPredefiniti.add(contentManager.getHotel(codiceHotelPredefinito));
        codiceHotelPredefinito = "";
    }

    public void aggiungiEscursionePredefinita() {
        listaEscursioniPredefinite.add(contentManager.getEscursione(codiceEscursionePredefinita));
        codiceTrasportoPredefinito = "";
    }

    public void aggiungiTrasporto() {
        listaTrasporti.add(contentManager.getTrasporto(codiceTrasporto));
        codiceTrasporto = "";
    }

    public void rimuoviTrasporto(TrasportoDTO trasportoDTO) {
        listaEscursioni.remove(trasportoDTO);
    }

    public void aggiungiHotel() {
        listaHotels.add(contentManager.getHotel(codiceHotel));
        codiceHotel = "";
    }

    public void aggiungiEscursione() {
        listaEscursioni.add(contentManager.getEscursione(codiceEscursione));
        codiceEscursione = "";
    }



    public String aggiungiPacchetto() {
        /*pacchettoDTO.setNome("Pacchetto sogni");
        pacchettoDTO.setDescrizione("Questa è una prova");
        pacchettoDTO.setLocalita("Jamaica");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            pacchettoDTO.setInizioValidita(sdf.parse("21/10/2012"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            pacchettoDTO.setFineValidita(sdf.parse("05/12/2012"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        pacchettoDTO.addTrasporto("12345", true);
        pacchettoDTO.addTrasporto("nuovo", false);
        pacchettoDTO.addHotel("12345", true);
        pacchettoDTO.addEscursione("ionoi", false);
        pacchettoDTO.addEscursione("12345", true);*/

        for(TrasportoDTO trasporto : listaTrasportiPredefiniti) {
            pacchettoDTO.addTrasporto(trasporto.getCodice_trasporto(), true);
        }

        for(HotelDTO hotel : listaHotelsPredefiniti) {
            pacchettoDTO.addHotel(hotel.getCodice_hotel(), true);
        }

        for(EscursioneDTO escursione : listaEscursioniPredefinite) {
            pacchettoDTO.addEscursione(escursione.getCodice_escursione(), true);
        }

        for(TrasportoDTO trasporto : listaTrasporti) {
            pacchettoDTO.addTrasporto(trasporto.getCodice_trasporto(), false);
        }

        for(HotelDTO hotel : listaHotels) {
            pacchettoDTO.addHotel(hotel.getCodice_hotel(), false);
        }

        for(EscursioneDTO escursione : listaEscursioni) {
            pacchettoDTO.addEscursione(escursione.getCodice_escursione(), false);
        }

        System.out.println("Nome : " + pacchettoDTO.getNome());
        System.out.println("Codice : " + pacchettoDTO.getCodice_pacchetto());
        System.out.println("Descrizione : " + pacchettoDTO.getDescrizione());
        System.out.println("Localita : " + pacchettoDTO.getLocalita());
        System.out.println("Inizio : " + pacchettoDTO.getInizioValidita());
        System.out.println("Fine : " + pacchettoDTO.getFineValidita());
        System.out.println("Lista trasporti : " + pacchettoDTO.getTrasporti());
        System.out.println("Lista hotels : " + pacchettoDTO.getHotels());
        System.out.println("Lista escursioni : " + pacchettoDTO.getEscursioni());




        contentManager.aggiungiPacchetto(pacchettoDTO);

        return "aggiungiPacchettoSuccess?faces-redirect=true";
    }



}
