package it.polimi.traveldream.ejb;

import it.polimi.traveldream.ejb.dtos.*;
import it.polimi.traveldream.ejb.entities.*;

import javax.enterprise.context.ApplicationScoped;

/**
 * Created by cHoco on 30/01/14.
 */
@ApplicationScoped
public class DTOsConverter {
    public EscursioneDTO convertEscursioneToDTO(Escursione escursione) {
        EscursioneDTO escursioneDTO = new EscursioneDTO();
        escursioneDTO.setCodice_escursione(escursione.getCodice_escursione());
        escursioneDTO.setDescrizione(escursione.getDescrizione());
        escursioneDTO.setNome(escursione.getNome());
        return escursioneDTO;
    }

    public TrasportoDTO convertTrasportoToDTO(Trasporto trasporto) {
        TrasportoDTO trasportoDTO = new TrasportoDTO();
        trasportoDTO.setCodice_trasporto(trasporto.getCodice_trasporto());
        trasportoDTO.setLocalitaArrivo(trasporto.getLocalitaArrivo());
        trasportoDTO.setLocalitaPartenza(trasporto.getLocalitaPartenza());
        trasportoDTO.setSocieta(trasporto.getSocieta());
        trasportoDTO.setTipoTrasporto(trasporto.getTipoTrasporto());
        return trasportoDTO;
    }

    public HotelDTO convertHotelToDTO(Hotel hotel) {
        HotelDTO hotelDTO = new HotelDTO();
        hotelDTO.setNome(hotel.getNome());
        hotelDTO.setCodice_hotel(hotel.getCodice_hotel());
        hotelDTO.setDescrizione(hotel.getDescrizione());
        hotelDTO.setStelle(hotel.getStelle());
        return hotelDTO;
    }

    /**
     * Realizza il Data Transfer Object estraendo le informazioni dal database
     * */
    public UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        return userDTO;
    }

    public PacchettoDTO convertPacchettoToDTO(Pacchetto pacchetto) {
        PacchettoDTO pacchettoDTO = new PacchettoDTO();
        pacchettoDTO.setNome(pacchetto.getNome());
        pacchettoDTO.setDescrizione(pacchetto.getDescrizione());
        pacchettoDTO.setLocalita(pacchetto.getLocalita());
        pacchettoDTO.setCodice_pacchetto(pacchetto.getCodice_pacchetto());
        pacchettoDTO.setInizioValidita(pacchetto.getInizioValidita());
        pacchettoDTO.setFineValidita(pacchetto.getFineValidita());
        for(TrasportiPacchetto trasportiPacchetto : pacchetto.getMezziTrasporto()) {
            if(trasportiPacchetto.isPredefinito()) {
                pacchettoDTO.addTrasporto(trasportiPacchetto.getTrasporto().getCodice_trasporto(), true);
            }
            else if(!trasportiPacchetto.isPredefinito()) {
                pacchettoDTO.addTrasporto(trasportiPacchetto.getTrasporto().getCodice_trasporto(), false);
            }
        }
        for(HotelsPacchetto hotelsPacchetto : pacchetto.getHotels()) {
            if(hotelsPacchetto.isPredefinito()) {
                pacchettoDTO.addHotel(hotelsPacchetto.getHotel().getCodice_hotel(), true);
            }
            else if(!hotelsPacchetto.isPredefinito()) {
                pacchettoDTO.addHotel(hotelsPacchetto.getHotel().getCodice_hotel(), false);
            }
        }
        for(EscursioniPacchetto escursioniPacchetto : pacchetto.getEscursioni()) {
            if(escursioniPacchetto.isPredefinito()) {
                pacchettoDTO.addEscursione(escursioniPacchetto.getEscursione().getCodice_escursione(), true);
            }
            else if(!escursioniPacchetto.isPredefinito()) {
                pacchettoDTO.addEscursione(escursioniPacchetto.getEscursione().getCodice_escursione(), false);
            }
        }

        return pacchettoDTO;
    }

    public PacchettoSalvatoDTO convertPacchettoSalvatoToDTO(PacchettoSalvato pacchettoSalvato) {
        PacchettoSalvatoDTO pacchettoSalvatoDTO = new PacchettoSalvatoDTO();
        pacchettoSalvatoDTO.setCodicePacchettoOriginale(pacchettoSalvato.getPacchettoOriginale().getCodice_pacchetto());
        pacchettoSalvatoDTO.setEmailUserCreatore(pacchettoSalvato.getUserCreatore().getEmail());
        pacchettoSalvatoDTO.setDataPartenza(pacchettoSalvato.getDataPartenza());
        pacchettoSalvatoDTO.setDataRitorno(pacchettoSalvato.getDataRitorno());
        pacchettoSalvatoDTO.setPrenotato(pacchettoSalvato.isPrenotato());
        for(Trasporto trasporto : pacchettoSalvato.getTrasportiScelti()) {
            pacchettoSalvatoDTO.addTrasporto(trasporto.getCodice_trasporto());
        }
        for(Hotel hotel : pacchettoSalvato.getHotelsScelti()) {
            pacchettoSalvatoDTO.addHotel(hotel.getCodice_hotel());
        }
        for(Escursione escursione : pacchettoSalvato.getEscursioni()) {
            pacchettoSalvatoDTO.addEscursione(escursione.getCodice_escursione());
        }

        return pacchettoSalvatoDTO;
    }
}
