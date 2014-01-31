package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.UserContentManager;
import it.polimi.traveldream.ejb.dtos.PacchettoDTO;
import it.polimi.traveldream.ejb.dtos.PacchettoSalvatoDTO;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cHoco on 27/01/14.
 */

@ManagedBean(name="userContentBean")
@RequestScoped
public class UserContentBean {

    @EJB
    private UserContentManager userContentManager;

    private List<PacchettoSalvatoDTO> pacchettiPersonali;

    private List<PacchettoSalvatoDTO> pacchettiPartecipati;

    private Map<PacchettoSalvatoDTO, PacchettoDTO> pacchettiOriginali;

    public List<PacchettoSalvatoDTO> getPacchettiPersonali() {
        return pacchettiPersonali;
    }

    public void setPacchettiPersonali(List<PacchettoSalvatoDTO> pacchettiPersonali) {
        this.pacchettiPersonali = pacchettiPersonali;
    }

    public List<PacchettoSalvatoDTO> getPacchettiPartecipati() {
        return pacchettiPartecipati;
    }

    public Map<PacchettoSalvatoDTO, PacchettoDTO> getPacchettiOriginali() {
        return pacchettiOriginali;
    }

    public void setPacchettiOriginali(Map<PacchettoSalvatoDTO, PacchettoDTO> pacchettiOriginali) {
        this.pacchettiOriginali = pacchettiOriginali;
    }

    public void setPacchettiPartecipati(List<PacchettoSalvatoDTO> pacchettiPartecipati) {
        this.pacchettiPartecipati = pacchettiPartecipati;
    }

    public void loadPacchettiPersonaliPartecipati() {
        pacchettiPersonali = userContentManager.getPacchettiPersonali();
        pacchettiPartecipati = userContentManager.getPacchettiPartecipati();
        pacchettiOriginali = new HashMap<>();
        for(PacchettoSalvatoDTO pacchettoSalvatoDTO : pacchettiPersonali) {
            pacchettiOriginali.put(pacchettoSalvatoDTO, getPacchettoOriginale(pacchettoSalvatoDTO));
        }
        for(PacchettoSalvatoDTO pacchettoSalvatoDTO : pacchettiPartecipati) {
            pacchettiOriginali.put(pacchettoSalvatoDTO, getPacchettoOriginale(pacchettoSalvatoDTO));
        }
    }

    public PacchettoDTO getPacchettoOriginale(PacchettoSalvatoDTO pacchettoSalvatoDTO) {
        return userContentManager.getPacchetto(pacchettoSalvatoDTO.getCodicePacchettoOriginale());
    }

    public void test() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        List<PacchettoDTO> pacchettoDTOs = null;
        try {
            pacchettoDTOs = userContentManager.searchPacchetti("a", "sdfghj", sdf.parse("21/01/2014"), sdf.parse("24/01/2014"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            pacchettoDTOs = userContentManager.searchPacchetti("a", "sdfghj", sdf.parse("21/01/2014"), sdf.parse("31/01/2014"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
