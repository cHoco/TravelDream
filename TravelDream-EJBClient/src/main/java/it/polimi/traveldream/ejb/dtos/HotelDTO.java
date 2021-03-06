package it.polimi.traveldream.ejb.dtos;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Asus on 19/01/14.
 */
public class HotelDTO {

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
    private String codice_hotel;

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
