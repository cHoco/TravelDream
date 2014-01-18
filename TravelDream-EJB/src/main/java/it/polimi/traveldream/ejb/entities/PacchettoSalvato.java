package it.polimi.traveldream.ejb.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Created by cHoco on 17/01/14.
 *
 * Questa entity corrisponde ai pacchetti personalizzati
 * creati dagli User
 *
 */

@Entity
@Table(name = "PACCHETTI_SALVATI")
public class PacchettoSalvato {

    @Id
    @GeneratedValue
    private long id_pacchettoSalvato;

    @NotNull
    @OneToOne(optional = false)
    @JoinColumn(name="id_userCreatore")
    private User userCreatore;

    @NotNull
    @OneToOne(optional = false)
    @JoinColumn(name="id_pacchettoOriginale")
    private Pacchetto pacchettoOriginale;

    @NotNull
    @Temporal(TemporalType.TIME)
    private Date dataPartenza;

    @NotNull
    @Temporal(TemporalType.TIME)
    private Date dataRitorno;

    @NotNull
    private boolean prenotato;

    @ManyToMany
    @JoinTable(name="TRASPORTI_PACCHETTO_SALVATO",
            joinColumns = { @JoinColumn(name="id_pacchettoSalvato")},
            inverseJoinColumns = { @JoinColumn(name="id_mezzoTrasporto")}
    )
    private List<Trasporto> trasportiScelti;

    @ManyToMany
    @JoinTable(name="HOTELS_PACCHETTO_SALVATO",
            joinColumns = { @JoinColumn(name="id_pacchettoSalvato")},
            inverseJoinColumns = { @JoinColumn(name="id_hotel")}
    )
    private List<Hotel> hotelsScelti;

    @ManyToMany
    @JoinTable(name="ESCURSIONI_PACCHETTO_SALVATO",
            joinColumns = { @JoinColumn(name="id_pacchettoSalvato")},
            inverseJoinColumns = { @JoinColumn(name="id_escursione")}
    )
    private List<Escursione> escursioni;

    public long getId_pacchettoSalvato() {
        return id_pacchettoSalvato;
    }

    public void setId_pacchettoSalvato(long id_pacchettoSalvato) {
        this.id_pacchettoSalvato = id_pacchettoSalvato;
    }

    public User getUserCreatore() {
        return userCreatore;
    }

    public void setUserCreatore(User userCreatore) {
        this.userCreatore = userCreatore;
    }

    public Pacchetto getPacchettoOriginale() {
        return pacchettoOriginale;
    }

    public void setPacchettoOriginale(Pacchetto pacchettoOriginale) {
        this.pacchettoOriginale = pacchettoOriginale;
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

    public List<Trasporto> getTrasportiScelti() {
        return trasportiScelti;
    }

    public void setTrasportiScelti(List<Trasporto> trasportiScelti) {
        this.trasportiScelti = trasportiScelti;
    }

    public List<Hotel> getHotelsScelti() {
        return hotelsScelti;
    }

    public void setHotelsScelti(List<Hotel> hotelsScelti) {
        this.hotelsScelti = hotelsScelti;
    }

    public List<Escursione> getEscursioni() {
        return escursioni;
    }

    public void setEscursioni(List<Escursione> escursioni) {
        this.escursioni = escursioni;
    }
}
