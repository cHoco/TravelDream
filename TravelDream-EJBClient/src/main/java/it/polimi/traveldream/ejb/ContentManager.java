package it.polimi.traveldream.ejb;

import it.polimi.traveldream.ejb.dtos.EscursioneDTO;
import it.polimi.traveldream.ejb.dtos.HotelDTO;
import it.polimi.traveldream.ejb.dtos.PacchettoDTO;
import it.polimi.traveldream.ejb.dtos.TrasportoDTO;

import javax.ejb.Local;

/**
 * Created by cHoco on 22/01/14.
 */
@Local
public interface ContentManager {
    public void aggiungiPacchetto(PacchettoDTO pacchetto);


    public void aggiungiTrasporto(TrasportoDTO trasporto);

    public void aggiungiHotel(HotelDTO hotel);

    public void aggiungiEscursione(EscursioneDTO escursione);


    public void modificaTrasporto(String codiceTrasporto, TrasportoDTO trasporto);

    public void modificaHotel(String codiceHotel, HotelDTO hotel);

    public void modificaEscursione(String codiceEscursione, EscursioneDTO escursione);


    public boolean isTrasportoUsed(TrasportoDTO trasporto);

    public boolean isHotelUsed(HotelDTO hotel);

    public boolean isEscursioneUsed(EscursioneDTO escursione);


    public void eliminaTrasporto(TrasportoDTO trasporto);

    public void eliminaHotel(HotelDTO hotel);

    public void eliminaEscursione(EscursioneDTO escursione);


    public TrasportoDTO getTrasporto(String codice_trasporto);

    public HotelDTO getHotel(String codice_hotel);

    public EscursioneDTO getEscursione(String codice_escursione);
}
