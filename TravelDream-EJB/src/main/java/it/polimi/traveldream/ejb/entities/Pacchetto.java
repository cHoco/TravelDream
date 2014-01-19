package it.polimi.traveldream.ejb.entities;

import it.polimi.traveldream.ejb.dtos.PacchettoDTO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by cHoco on 17/01/14.
 */

@Entity
@Table(name = "PACCHETTI")
public class Pacchetto implements Serializable {

    @Id
    @GeneratedValue
    private long id_pacchetto;

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
    @Temporal(TemporalType.DATE)
    private Date inizioValidita;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date fineValidita;

    @OneToMany(mappedBy = "pacchetto")
    private List<TrasportiPacchetto> mezziTrasporto;

    @OneToMany(mappedBy = "pacchetto")
    private List<HotelsPacchetto> hotels;

    @OneToMany(mappedBy = "pacchetto")
    private List<EscursioniPacchetto> escursioni;

    public Pacchetto(){
        super();
    }

    public Pacchetto(PacchettoDTO pacchettoDTO) {
        this.nome = pacchettoDTO.getNome();
        this.descrizione = pacchettoDTO.getDescrizione();
        this.localita = pacchettoDTO.getLocalita();
        this.inizioValidita = pacchettoDTO.getInizioValidita();
        this.fineValidita = pacchettoDTO.getFineValidita();
      /*  this.mezziTrasporto = pacchettoDTO.mezz;
        this.hotels = hotels;
        this.escursioni = escursioni;    */
    }

    public long getId_pacchetto() {
        return id_pacchetto;
    }

    public void setId_pacchetto(long id_pacchetto) {
        this.id_pacchetto = id_pacchetto;
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

    public List<TrasportiPacchetto> getMezziTrasporto() {
        return mezziTrasporto;
    }

    public void setMezziTrasporto(List<TrasportiPacchetto> mezziTrasporto) {
        this.mezziTrasporto = mezziTrasporto;
    }

    public void setHotels(List<HotelsPacchetto> hotels) {
        this.hotels = hotels;
    }

    public void setEscursioni(List<EscursioniPacchetto> escursioni) {
        this.escursioni = escursioni;
    }

    public List<Trasporto> getTrasporti() {
        List<Trasporto> trasporti = new ArrayList<>();
        for(TrasportiPacchetto trasportoPacchetto : this.mezziTrasporto) {
            trasporti.add(trasportoPacchetto.getTrasporto());
        }
        return trasporti;
    }

    public List<Hotel> getHotels() {
        List<Hotel> hotels = new ArrayList<>();
        for(HotelsPacchetto hotelPacchetto : this.hotels) {
            hotels.add(hotelPacchetto.getHotel());
        }
        return hotels;
    }

    public List<Escursione> getEscursioni() {
        List<Escursione> escursioni = new ArrayList<>();
        for(EscursioniPacchetto escursionePacchetto : this.escursioni) {
            escursioni.add(escursionePacchetto.getEscursione());
        }
        return escursioni;
    }
}
