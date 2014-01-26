package it.polimi.traveldream.ejb.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by cHoco on 17/01/14.
 */

@Entity
@Table(name = "HOTELS_PACCHETTO")
public class HotelsPacchetto implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_pacchetto", referencedColumnName = "id_pacchetto")
    private Pacchetto pacchetto;

    @ManyToOne
    @JoinColumn(name = "id_hotel", referencedColumnName = "id_hotel")
    private Hotel hotel;

    @NotNull
    private boolean predefinito;

    public HotelsPacchetto() {
        super();
    }

    public HotelsPacchetto(Pacchetto pacchetto, Hotel hotel, boolean predefinito) {
        this.pacchetto = pacchetto;
        this.hotel = hotel;
        this.predefinito = predefinito;
    }

    public Pacchetto getPacchetto() {
        return pacchetto;
    }

    public void setPacchetto(Pacchetto pacchetto) {
        this.pacchetto = pacchetto;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public boolean isPredefinito() {
        return predefinito;
    }

    public void setPredefinito(boolean predefinito) {
        this.predefinito = predefinito;
    }
}
