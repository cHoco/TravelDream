package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.UserContentManager;
import it.polimi.traveldream.ejb.dtos.PacchettoDTO;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by cHoco on 28/01/14.
 */
@ManagedBean(name="userRicercaPacchettoBean")
@ViewScoped
public class UserRicercaPacchettoBean {

    @EJB
    UserContentManager userContentManager;

    private String partenzaCercata;

    private String localitaCercata;

    private Date dataPartenza;

    private Date dataRitorno;

    private Date oraPartenza;

    private Date oraRitorno;

    private String partenza;

    private String ritorno;

    private List<PacchettoDTO> pacchettiTrovati;

    private boolean cercato;

    public UserRicercaPacchettoBean() {
        pacchettiTrovati = new ArrayList<>();
        cercato = false;
    }

    public String getPartenzaCercata() {
        return partenzaCercata;
    }

    public void setPartenzaCercata(String partenzaCercata) {
        this.partenzaCercata = partenzaCercata;
    }

    public String getLocalitaCercata() {
        return localitaCercata;
    }

    public void setLocalitaCercata(String localitaCercata) {
        this.localitaCercata = localitaCercata;
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

    public Date getOraPartenza() {
        return oraPartenza;
    }

    public void setOraPartenza(Date oraPartenza) {
        this.oraPartenza = oraPartenza;
    }

    public Date getOraRitorno() {
        return oraRitorno;
    }

    public void setOraRitorno(Date oraRitorno) {
        this.oraRitorno = oraRitorno;
    }

    public List<PacchettoDTO> getPacchettiTrovati() {
        return pacchettiTrovati;
    }

    public void setPacchettiTrovati(List<PacchettoDTO> pacchettiTrovati) {
        this.pacchettiTrovati = pacchettiTrovati;
    }

    public void setCercato(boolean cercato) {
        this.cercato = cercato;
    }

    public boolean isCercato() {
        return cercato;
    }

    public String getPartenza() {
        String tempDataPartenza = new SimpleDateFormat("yyyyMMdd").format(dataPartenza);
        String tempOraPartenza = new SimpleDateFormat("HHmmzzz").format(oraPartenza);


        return tempDataPartenza+tempOraPartenza;
    }

    public void setPartenza(String partenza) {
        this.partenza = partenza;
    }

    public String getRitorno() {
        String tempDataRitorno = new SimpleDateFormat("yyyyMMdd").format(dataRitorno);
        String tempOraRitorno = new SimpleDateFormat("HHmmzzz").format(oraRitorno);


        return tempDataRitorno+tempOraRitorno;
    }

    public void setRitorno(String ritorno) {
        this.ritorno = ritorno;
    }

    public void cercaPacchetti(){
        pacchettiTrovati = userContentManager.searchPacchetti(partenzaCercata, localitaCercata, dataPartenza, dataRitorno);
        System.out.println("Nome : " + pacchettiTrovati.get(0).getNome());
        cercato = true;
    }
}
