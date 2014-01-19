package it.polimi.traveldream.ejb.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Asus on 19/01/14.
 */
public class EscursioneDTO {


    @NotNull
    @Size(min=1)

    private String nome;

    @NotNull
    @Size(min=1)
    private String descrizione;


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

}
