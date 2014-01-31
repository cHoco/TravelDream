package it.polimi.traveldream.ejb.entities;

import it.polimi.traveldream.ejb.dtos.EscursioneDTO;
import it.polimi.traveldream.ejb.dtos.HotelDTO;
import it.polimi.traveldream.ejb.dtos.PacchettoDTO;
import it.polimi.traveldream.ejb.dtos.TrasportoDTO;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_pacchetto;

    @NotNull
    @Size(min=1)
    private String nome;

    @NotNull
    @Size(min=1)
    @Column(length = 1000)
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

    @OneToMany(mappedBy = "pacchetto", cascade = CascadeType.PERSIST)
    private List<TrasportiPacchetto> mezziTrasporto;

    @OneToMany(mappedBy = "pacchetto", cascade = CascadeType.PERSIST)
    private List<HotelsPacchetto> hotels;

    @OneToMany(mappedBy = "pacchetto", cascade = CascadeType.PERSIST)
    private List<EscursioniPacchetto> escursioni;

    @NotNull
    @Size(min=5, max = 5)
    @Column(unique = true)
    private String codice_pacchetto;

    public Pacchetto(){
        super();
    }

    public Pacchetto(PacchettoDTO pacchettoDTO) {
        this.nome = pacchettoDTO.getNome();
        this.descrizione = pacchettoDTO.getDescrizione();
        this.localita = pacchettoDTO.getLocalita();
        this.inizioValidita = pacchettoDTO.getInizioValidita();
        this.fineValidita = pacchettoDTO.getFineValidita();
        this.mezziTrasporto = new ArrayList<>();
        this.hotels = new ArrayList<>();
        this.escursioni = new ArrayList<>();
        this.codice_pacchetto = pacchettoDTO.getCodice_pacchetto();
        /*
        for(String trasportoDTO : pacchettoDTO.getTrasporti().keySet()) {
            this.mezziTrasporto.add(new TrasportiPacchetto(this, , pacchettoDTO.getTrasporti().get(trasportoDTO)));
        }

        for(String hotelDTO : pacchettoDTO.getHotels().keySet()) {
            this.hotels.add(new HotelsPacchetto(this, new Hotel(hotelDTO), pacchettoDTO.getHotels().get(hotelDTO)));
        }

        for(String escursioneDTO : pacchettoDTO.getEscursioni().keySet()) {
            this.escursioni.add(new EscursioniPacchetto(this, new Escursione(escursioneDTO), pacchettoDTO.getEscursioni().get(escursioneDTO)));
        }*/
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

    public List<HotelsPacchetto> getHotels() {
        return hotels;
    }

    public List<EscursioniPacchetto> getEscursioni() {
        return escursioni;
    }

    public void setEscursioni(List<EscursioniPacchetto> escursioni) {
        this.escursioni = escursioni;
    }

    public String getCodice_pacchetto() {
        return codice_pacchetto;
    }

    public void setCodice_pacchetto(String codice_pacchetto) {
        this.codice_pacchetto = codice_pacchetto;
    }

    /*
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
    }*/
}
