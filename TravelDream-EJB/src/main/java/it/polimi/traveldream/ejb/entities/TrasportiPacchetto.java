package it.polimi.traveldream.ejb.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by cHoco on 17/01/14.
 */

@Entity
@Table(name = "TRASPORTI_PACCHETTO")
public class TrasportiPacchetto implements Serializable {

    @Id
    @ManyToOne
    //@PrimaryKeyJoinColumn(name = "id_pacchetto", referencedColumnName = "id_pacchetto")
    @JoinColumn(name = "id_pacchetto")
    private Pacchetto pacchetto;

    @Id
    @ManyToOne
    //@PrimaryKeyJoinColumn(name = "id_mezzoTrasporto", referencedColumnName = "id_mezzoTrasporto")
    @JoinColumn(name = "id_mezzoTrasporto")
    private Trasporto trasporto;

    @NotNull
    private boolean predefinito;

    public Pacchetto getPacchetto() {
        return pacchetto;
    }

    public void setPacchetto(Pacchetto pacchetto) {
        this.pacchetto = pacchetto;
    }

    public Trasporto getTrasporto() {
        return trasporto;
    }

    public void setTrasporto(Trasporto trasporto) {
        this.trasporto = trasporto;
    }

    public boolean isPredefinito() {
        return predefinito;
    }

    public void setPredefinito(boolean predefinito) {
        this.predefinito = predefinito;
    }
}
