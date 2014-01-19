package it.polimi.traveldream.ejb.dtos;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Asus on 19/01/14.
 */
public class PacchettoSalvatoDTO {

    @NotNull
    private UserDTO userCreatore;

    @NotNull
    private PacchettoDTO pacchettoOriginale;

    @NotNull
    private Date dataPartenza;

    @NotNull
    private Date dataRitorno;

    @NotNull
    private boolean prenotato;

    public UserDTO getUserCreatore() {
        return userCreatore;
    }

    public void setUserCreatore(UserDTO userCreatore) {
        this.userCreatore = userCreatore;
    }

    public PacchettoDTO getPacchettoOriginale() {
        return pacchettoOriginale;
    }

    public void setPacchettoOriginale(PacchettoDTO pacchettoOriginale) {
        this.pacchettoOriginale = pacchettoOriginale;
    }

    public Date getDataPartenza() {
        return dataPartenza;
    }

    public void setDataPartenza(Date dataPartenza) {
        this.dataPartenza = dataPartenza;
    }

    public Date getDataRitorno() {
        return dataRitorno;
    }

    public void setDataRitorno(Date dataRitorno) {
        this.dataRitorno = dataRitorno;
    }

    public boolean isPrenotato() {
        return prenotato;
    }

    public void setPrenotato(boolean prenotato) {
        this.prenotato = prenotato;
    }
}
