package it.polimi.traveldream.ejb.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Asus on 19/01/14.
 */
public class PacchettoDTO {

    @NotNull
    @Size(min=1)
    private String nome;

    @NotNull
    @Size(min=1)
    private String descrizione;

    @NotNull
    @Size(min=1)
    private String localita;

    @NotNull
    private Date inizioValidita;

    @NotNull
    private Date fineValidita;

    @NotNull
    private Map<String, Boolean> trasporti;

    @NotNull
    private Map<String, Boolean> hotels;

    @NotNull
    private Map<String, Boolean> escursioni;

    @NotNull
    @Size(min=5, max = 5)
    private String codice_pacchetto;

    public PacchettoDTO() {
        trasporti = new LinkedHashMap<>();
        hotels = new LinkedHashMap<>();
        escursioni = new LinkedHashMap<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getLocalita() {
        return localita;
    }

    public void setLocalita(String localita) {
        this.localita = localita;
    }

    public Date getInizioValidita() {
        return inizioValidita;
    }

    public void setInizioValidita(Date inizioValidita) {
        this.inizioValidita = inizioValidita;
    }

    public Date getFineValidita() {
        return fineValidita;
    }

    public void setFineValidita(Date fineValidita) {
        this.fineValidita = fineValidita;
    }

    public Map<String, Boolean> getTrasporti() {
        return trasporti;
    }

    public void setTrasporti(Map<String, Boolean> trasporti) {
        this.trasporti = trasporti;
    }

    public Map<String, Boolean> getHotels() {
        return hotels;
    }

    public void setHotels(Map<String, Boolean> hotels) {
        this.hotels = hotels;
    }

    public Map<String, Boolean> getEscursioni() {
        return escursioni;
    }

    public void setEscursioni(Map<String, Boolean> escursioni) {
        this.escursioni = escursioni;
    }

    public String getCodice_pacchetto() {
        return codice_pacchetto;
    }

    public void setCodice_pacchetto(String codice_pacchetto) {
        this.codice_pacchetto = codice_pacchetto;
    }

    public void addTrasporto(String trasporto, boolean predefinito) {
        this.trasporti.put(trasporto, predefinito);
    }

    public void addHotel(String hotel, boolean predefinito) {
        this.hotels.put(hotel, predefinito);
    }

    public void addEscursione(String escursione, boolean predefinito) {
        this.escursioni.put(escursione, predefinito);
    }
}
