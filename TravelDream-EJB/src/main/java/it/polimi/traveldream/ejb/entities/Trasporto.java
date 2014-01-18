package it.polimi.traveldream.ejb.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by cHoco on 17/01/14.
 */

enum TipoTrasporto {
    AEREO, TRENO, BUS
}

@Entity
@Table(name = "TRASPORTI")
public class Trasporto implements Serializable{

    @Id
    @GeneratedValue
    private long id_mezzoTrasporto;

    @NotNull
    private TipoTrasporto tipoTrasporto;

    @NotNull
    @Size(min=1)
    private String societa;

    @NotNull
    @Size(min=1)
    private String localitaPartenza;

    @NotNull
    @Size(min=1)
    private String localitaArrivo;

    public long getId_mezzoTrasporto() {
        return id_mezzoTrasporto;
    }

    public void setId_mezzoTrasporto(long id_mezzoTrasporto) {
        this.id_mezzoTrasporto = id_mezzoTrasporto;
    }

    public TipoTrasporto getTipoTrasporto() {
        return tipoTrasporto;
    }

    public void setTipoTrasporto(TipoTrasporto tipoTrasporto) {
        this.tipoTrasporto = tipoTrasporto;
    }

    public String getSocieta() {
        return societa;
    }

    public void setSocieta(String societa) {
        this.societa = societa;
    }

    public String getLocalitaPartenza() {
        return localitaPartenza;
    }

    public void setLocalitaPartenza(String localitaPartenza) {
        this.localitaPartenza = localitaPartenza;
    }

    public String getLocalitaArrivo() {
        return localitaArrivo;
    }

    public void setLocalitaArrivo(String localitaArrivo) {
        this.localitaArrivo = localitaArrivo;
    }
}
