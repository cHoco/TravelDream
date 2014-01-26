package it.polimi.traveldream.ejb.entities;

import it.polimi.traveldream.ejb.dtos.HotelDTO;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by cHoco on 17/01/14.
 */

@Entity
@Table(name = "HOTELS")
public class Hotel implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_hotel;

    @NotNull
    @Size(min=1)
    private String nome;

    @NotNull
    @Size(min=1)
    private String descrizione;

    @NotNull
    @DecimalMin("1")
    @DecimalMax("5")
    private int stelle;

    @NotNull
    @Size(min=5, max = 5)
    @Column(unique = true)
    private String codice_hotel;

    @OneToMany(mappedBy = "hotel")
    private List<HotelsPacchetto> pacchetti;

    public Hotel(){
        super();
    }

    public Hotel(HotelDTO hotelDTO) {
        this.nome = hotelDTO.getNome();
        this.descrizione = hotelDTO.getDescrizione();
        this.stelle = hotelDTO.getStelle();
        this.codice_hotel = hotelDTO.getCodice_hotel();
    }

    public long getId_hotel() {
        return id_hotel;
    }

    public void setId_hotel(long id_hotel) {
        this.id_hotel = id_hotel;
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

    public int getStelle() {
        return stelle;
    }

    public void setStelle(int stelle) {
        this.stelle = stelle;
    }

    public String getCodice_hotel() {
        return codice_hotel;
    }

    public void setCodice_hotel(String codice_hotel) {
        this.codice_hotel = codice_hotel;
    }
}
