package it.polimi.traveldream.ejb;

import it.polimi.traveldream.ejb.dtos.EscursioneDTO;
import it.polimi.traveldream.ejb.dtos.HotelDTO;
import it.polimi.traveldream.ejb.dtos.PacchettoDTO;
import it.polimi.traveldream.ejb.dtos.TrasportoDTO;
import it.polimi.traveldream.ejb.entities.*;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cHoco on 22/01/14.
 */

@Stateless
public class ContentManagerBean implements ContentManager{

    @PersistenceContext
    private EntityManager em;

    @Resource
    private EJBContext context;

    @Override
    public void aggiungiPacchetto(PacchettoDTO pacchetto) {

        /*Pacchetto newPacchetto = new Pacchetto(pacchetto);
        em.persist(newPacchetto);
        for(EscursioniPacchetto escursioniPacchetto : newPacchetto.getEscursioni()) {
            em.merge(escursioniPacchetto);
        }
        Pacchetto newPacchetto = new Pacchetto();
        EscursioniPacchetto escursioniPacchetto = new EscursioniPacchetto();
        HotelsPacchetto hotelsPacchetto = new HotelsPacchetto();
        TrasportiPacchetto trasportiPacchetto = new TrasportiPacchetto();
        escursioniPacchetto.setEscursione(findEscursioneByCodice("12345"));
        hotelsPacchetto.setHotel(findHotelByCodice("12345"));
        trasportiPacchetto.setTrasporto(findTrasportoByCodice("12345"));
        escursioniPacchetto.setPredefinito(true);
        hotelsPacchetto.setPredefinito(true);
        trasportiPacchetto.setPredefinito(true);
        escursioniPacchetto.setPacchetto(newPacchetto);
        hotelsPacchetto.setPacchetto(newPacchetto);
        trasportiPacchetto.setPacchetto(newPacchetto);
        newPacchetto.setNome("Test");
        newPacchetto.setDescrizione("Prova");
        newPacchetto.setLocalita("lqtro");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {
            newPacchetto.setInizioValidita(sdf.parse("21/10/2012"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            newPacchetto.setFineValidita(sdf.parse("05/12/2012"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<HotelsPacchetto> hotels = new ArrayList<>();
        List<EscursioniPacchetto> escursioni = new ArrayList<>();
        List<TrasportiPacchetto> trasporti = new ArrayList<>();
        hotels.add(hotelsPacchetto);
        escursioni.add(escursioniPacchetto);
        trasporti.add(trasportiPacchetto);

        newPacchetto.setHotels(hotels);

        newPacchetto.setEscursioni(escursioni);

        newPacchetto.setMezziTrasporto(trasporti);
        em.persist(newPacchetto);*/

        Pacchetto newPacchetto = new Pacchetto(pacchetto);

        for(String codice_trasporto : pacchetto.getTrasporti().keySet()) {
            TrasportiPacchetto temp = new TrasportiPacchetto();
            temp.setPacchetto(newPacchetto);
            temp.setTrasporto(findTrasportoByCodice(codice_trasporto));
            temp.setPredefinito(pacchetto.getTrasporti().get(codice_trasporto));
            newPacchetto.getMezziTrasporto().add(temp);
        }

        for(String codice_hotel : pacchetto.getHotels().keySet()) {
            HotelsPacchetto temp = new HotelsPacchetto();
            temp.setPacchetto(newPacchetto);
            temp.setHotel(findHotelByCodice(codice_hotel));
            temp.setPredefinito(pacchetto.getHotels().get(codice_hotel));
            newPacchetto.getHotels().add(temp);
        }

        for(String codice_escursione : pacchetto.getEscursioni().keySet()) {
            EscursioniPacchetto temp = new EscursioniPacchetto();
            temp.setPacchetto(newPacchetto);
            temp.setEscursione(findEscursioneByCodice(codice_escursione));
            temp.setPredefinito(pacchetto.getEscursioni().get(codice_escursione));
            newPacchetto.getEscursioni().add(temp);
        }


        em.persist(newPacchetto);


    }

    @Override
    public void aggiungiTrasporto(TrasportoDTO trasporto) {
        Trasporto newTrasporto = new Trasporto(trasporto);
        em.persist(newTrasporto);
    }

    @Override
    public void aggiungiHotel(HotelDTO hotel) {
        Hotel newHotel = new Hotel(hotel);
        em.persist(newHotel);
    }

    @Override
    public void aggiungiEscursione(EscursioneDTO escursione) {
        Escursione newEscursione = new Escursione(escursione);
        em.persist(newEscursione);
    }

    @Override
    public void modificaTrasporto(String codiceTrasporto, TrasportoDTO trasporto) {
        Trasporto oldTrasporto = findTrasportoByCodice(codiceTrasporto);
        Trasporto newTrasporto = new Trasporto(trasporto);
        newTrasporto.setId_mezzoTrasporto(oldTrasporto.getId_mezzoTrasporto());
        em.merge(newTrasporto);
    }

    @Override
    public void modificaHotel(String codiceHotel, HotelDTO hotel) {
        Hotel oldHotel = findHotelByCodice(codiceHotel);
        Hotel newHotel = new Hotel(hotel);
        newHotel.setId_hotel(oldHotel.getId_hotel());
        em.merge(newHotel);
    }

    @Override
    public void modificaEscursione(String codiceEscursione, EscursioneDTO escursione) {
        Escursione oldEscursione = findEscursioneByCodice(codiceEscursione);
        Escursione newEscursione = new Escursione(escursione);
        newEscursione.setId_escursione(oldEscursione.getId_escursione());
        em.merge(newEscursione);
    }

    @Override
    public void eliminaTrasporto(TrasportoDTO trasporto) {

    }

    @Override
    public void eliminaHotel(HotelDTO hotel) {

    }

    @Override
    public void eliminaEscursione(EscursioneDTO escursione) {

    }

    @Override
    public TrasportoDTO getTrasporto(String codice_trasporto) {
        Trasporto trasporto = findTrasportoByCodice(codice_trasporto);
        return convertTrasportoToDTO(trasporto);
    }

    @Override
    public HotelDTO getHotel(String codice_hotel) {
        Hotel hotel = findHotelByCodice(codice_hotel);
        return convertHotelToDTO(hotel);
    }

    @Override
    public EscursioneDTO getEscursione(String codice_escursione) {
        Escursione escursione = findEscursioneByCodice(codice_escursione);
        return convertEscursioneToDTO(escursione);
    }


    private Escursione findEscursioneByCodice(String codice_escursione) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Escursione> c = cb.createQuery(Escursione.class);
        Root<Escursione> member = c.from(Escursione.class);
        c.select(member).where(cb.equal(member.get("codice_escursione"), codice_escursione));
        return em.createQuery(c).getSingleResult();
    }

    private Trasporto findTrasportoByCodice(String codice_trasporto) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Trasporto> c = cb.createQuery(Trasporto.class);
        Root<Trasporto> member = c.from(Trasporto.class);
        c.select(member).where(cb.equal(member.get("codice_trasporto"), codice_trasporto));
        return em.createQuery(c).getSingleResult();
    }

    private Hotel findHotelByCodice(String codice_hotel) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Hotel> c = cb.createQuery(Hotel.class);
        Root<Hotel> member = c.from(Hotel.class);
        c.select(member).where(cb.equal(member.get("codice_hotel"), codice_hotel));
        return em.createQuery(c).getSingleResult();
    }

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
}
