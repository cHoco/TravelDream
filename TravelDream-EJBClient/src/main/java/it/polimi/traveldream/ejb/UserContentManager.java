package it.polimi.traveldream.ejb;

import it.polimi.traveldream.ejb.dtos.PacchettoDTO;
import it.polimi.traveldream.ejb.dtos.PacchettoSalvatoDTO;

import javax.ejb.Local;
import java.util.Date;
import java.util.List;

/**
 * Created by cHoco on 27/01/14.
 */
@Local
public interface UserContentManager {
    public void prenotaPacchetto(PacchettoSalvatoDTO pacchettoSalvatoDTO);

    public void salvaPacchetto();

    public void personalizzaPacchetto();

    public void aggiungiPartecipazione(PacchettoSalvatoDTO pacchettoSalvatoDTO);

    public PacchettoDTO getPacchetto(PacchettoDTO pacchettoDTO);

    public PacchettoSalvatoDTO getPacchettoSalvato();

    public List<PacchettoSalvatoDTO> getPacchettiPersonali();

    public List<PacchettoSalvatoDTO> getPacchettiPartecipati();

    public List<PacchettoDTO> searchPacchetti(String partenza, String localita, Date dataPartenza, Date dateRitorno);

}
