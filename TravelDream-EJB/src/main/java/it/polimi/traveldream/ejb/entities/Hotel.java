package it.polimi.traveldream.ejb.entities;

import it.polimi.traveldream.ejb.dtos.HotelDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * Created by cHoco on 17/01/14.
 */

@Entity
@Table(name = "HOTELS")
public class Hotel implements Serializable{

    @Id
    @GeneratedValue
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

    public Hotel(){
        super();
    }

    public Hotel(HotelDTO hotelDTO) {
        this.nome = hotelDTO.getNome();
        this.descrizione = hotelDTO.getDescrizione();
        this.stelle = hotelDTO.getStelle();
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
}
