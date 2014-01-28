package it.polimi.traveldream.ejb.dtos;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Asus on 19/01/14.
 */
public class PacchettoSalvatoDTO {

    @NotNull
    private long idUserCreatore;

    @NotNull
    private String codicePacchettoOriginale;

    @NotNull
    private Date dataPartenza;

    @NotNull
    private Date dataRitorno;

    @NotNull
    private boolean prenotato;

    @NotNull
    private List<String> codiciTrasporti;

    @NotNull
    private List<String> codiciHotels;

    @NotNull
    private  List<String> codiciEscursioni;

    private List<UserDTO> usersPartecipanti;

    @NotNull
    private String codice_pacchettoSalvato;

    public PacchettoSalvatoDTO() {
        codiciTrasporti = new ArrayList<>();
        codiciHotels = new ArrayList<>();
        codiciEscursioni = new ArrayList<>();
        usersPartecipanti = new ArrayList<>();
        prenotato = false;
    }

    public long getIdUserCreatore() {
        return idUserCreatore;
    }

    public void setIdUserCreatore(long userCreatore) {
        this.idUserCreatore = userCreatore;
    }

    public String getCodicePacchettoOriginale() {
        return codicePacchettoOriginale;
    }

    public void setCodicePacchettoOriginale(String pacchettoOriginale) {
        this.codicePacchettoOriginale = pacchettoOriginale;
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

    public List<String> getCodiciTrasporti() {
        return codiciTrasporti;
    }

    public void setCodiciTrasporti(List<String> codiciTrasporti) {
        this.codiciTrasporti = codiciTrasporti;
    }

    public List<String> getCodiciHotels() {
        return codiciHotels;
    }

    public void setCodiciHotels(List<String> codiciHotels) {
        this.codiciHotels = codiciHotels;
    }

    public List<String> getCodiciEscursioni() {
        return codiciEscursioni;
    }

    public void setCodiciEscursioni(List<String> codiciEscursioni) {
        this.codiciEscursioni = codiciEscursioni;
    }

    public String getCodice_pacchettoSalvato() {
        return codice_pacchettoSalvato;
    }

    public void setCodice_pacchettoSalvato(String codice_pacchettoSalvato) {
        this.codice_pacchettoSalvato = codice_pacchettoSalvato;
    }

    public void addTrasporto(String trasporto) {
        codiciTrasporti.add(trasporto);
    }

    public void addHotel(String hotel) {
        codiciHotels.add(hotel);
    }

    public void addEscursione(String escursione) {
        codiciEscursioni.add(escursione);
    }

    public void addUserPartecipante(UserDTO userDTO) {
        usersPartecipanti.add(userDTO);
    }
}
