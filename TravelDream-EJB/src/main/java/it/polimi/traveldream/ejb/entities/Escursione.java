package it.polimi.traveldream.ejb.entities;

import it.polimi.traveldream.ejb.dtos.EscursioneDTO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * Created by cHoco on 17/01/14.
 */

@Entity
@Table(name = "ESCURSIONI")
public class Escursione implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_escursione;

    @NotNull
    @Size(min=1)
    private String nome;

    @NotNull
    @Size(min=1)
    private String descrizione;

    @NotNull
    @Size(min=5, max = 5)
    @Column(unique = true)
    private String codice_escursione;


    @OneToMany(mappedBy = "escursione")
    private List<EscursioniPacchetto> pacchetti;

    public Escursione (){
        super();
    }

    public Escursione (EscursioneDTO escursioneDTO){

        this.nome = escursioneDTO.getNome();
        this.descrizione = escursioneDTO.getDescrizione();
        this.codice_escursione = escursioneDTO.getCodice_escursione();

    }

    public long getId_escursione() {
        return id_escursione;
    }

    public void setId_escursione(long id_escursione) {
        this.id_escursione = id_escursione;
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

    public String getCodice_escursione() {
        return codice_escursione;
    }

    public void setCodice_escursione(String codice_escursione) {
        this.codice_escursione = codice_escursione;
    }
}
