package it.polimi.traveldream.ejb.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by cHoco on 17/01/14.
 */

@Entity
@Table(name = "ESCURSIONI_PACCHETTO")
public class EscursioniPacchetto implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_pacchetto", referencedColumnName = "id_pacchetto")
    private Pacchetto pacchetto;

    @ManyToOne
    @JoinColumn(name = "id_escursione", referencedColumnName = "id_escursione")
    private Escursione escursione;

    @NotNull
    private boolean predefinito;

    public EscursioniPacchetto() {
        super();
    }

    public EscursioniPacchetto(Pacchetto pacchetto, Escursione escursione, boolean predefinito) {
        this.pacchetto = pacchetto;
        this.escursione = escursione;
        this.predefinito = predefinito;
    }

    public Pacchetto getPacchetto() {
        return pacchetto;
    }

    public void setPacchetto(Pacchetto pacchetto) {
        this.pacchetto = pacchetto;
    }

    public Escursione getEscursione() {
        return escursione;
    }

    public void setEscursione(Escursione escursione) {
        this.escursione = escursione;
    }

    public boolean isPredefinito() {
        return predefinito;
    }

    public void setPredefinito(boolean predefinito) {
        this.predefinito = predefinito;
    }
}
