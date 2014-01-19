package it.polimi.traveldream.ejb.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Asus on 19/01/14.
 */

public class TrasportoDTO {

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
