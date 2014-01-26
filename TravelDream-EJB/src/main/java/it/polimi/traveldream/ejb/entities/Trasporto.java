package it.polimi.traveldream.ejb.entities;

import it.polimi.traveldream.ejb.dtos.TipoTrasporto;
import it.polimi.traveldream.ejb.dtos.TrasportoDTO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * Created by cHoco on 17/01/14.
 */

@Entity
@Table(name = "TRASPORTI")
public class Trasporto implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @NotNull
    @Size(min=5, max = 5)
    @Column(unique = true)
    private String codice_trasporto;

    @OneToMany(mappedBy = "trasporto")
    private List<TrasportiPacchetto> pacchetti;

    public Trasporto(){
        super();
    }

    public Trasporto(TrasportoDTO trasportoDTO) {

        this.tipoTrasporto = trasportoDTO.getTipoTrasporto();
        this.societa = trasportoDTO.getSocieta();
        this.localitaPartenza = trasportoDTO.getLocalitaPartenza();
        this.localitaArrivo = trasportoDTO.getLocalitaArrivo();
        this.codice_trasporto = trasportoDTO.getCodice_trasporto();

    }

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

    public String getCodice_trasporto() {
        return codice_trasporto;
    }

    public void setCodice_trasporto(String codice_trasporto) {
        this.codice_trasporto = codice_trasporto;
    }
}
