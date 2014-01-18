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
    @ManyToOne
    @JoinColumn(name = "id_pacchetto")
    private Pacchetto pacchetto;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_escursione")
    private Escursione escursione;

    @NotNull
    private boolean predefinito;

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
