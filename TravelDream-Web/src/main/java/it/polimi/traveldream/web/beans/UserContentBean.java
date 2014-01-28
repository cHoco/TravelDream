package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.dtos.PacchettoSalvatoDTO;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;

/**
 * Created by cHoco on 27/01/14.
 */

@ManagedBean(name="userContentBean")
@RequestScoped
public class UserContentBean {

    private List<PacchettoSalvatoDTO> pacchettiPersonali;

    private List<PacchettoSalvatoDTO> pacchettiPartecipati;

    public List<PacchettoSalvatoDTO> getPacchettiPersonali() {
        return pacchettiPersonali;
    }

    public void setPacchettiPersonali(List<PacchettoSalvatoDTO> pacchettiPersonali) {
        this.pacchettiPersonali = pacchettiPersonali;
    }

    public List<PacchettoSalvatoDTO> getPacchettiPartecipati() {
        return pacchettiPartecipati;
    }

    public void setPacchettiPartecipati(List<PacchettoSalvatoDTO> pacchettiPartecipati) {
        this.pacchettiPartecipati = pacchettiPartecipati;
    }
}
