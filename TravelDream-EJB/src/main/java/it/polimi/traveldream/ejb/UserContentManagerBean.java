package it.polimi.traveldream.ejb;

import it.polimi.traveldream.ejb.dtos.PacchettoDTO;
import it.polimi.traveldream.ejb.dtos.PacchettoSalvatoDTO;
import it.polimi.traveldream.ejb.entities.*;
import org.apache.commons.lang3.RandomStringUtils;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by cHoco on 27/01/14.
 */
@Stateless
public class UserContentManagerBean implements UserContentManager {

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserManager userManagerBean;

    @Inject
    ContentRepository contentRepository;

    @Inject
    DTOsConverter dtOsConverter;

    @Inject
    UserRepository userRepository;

    @Override
    public void prenotaPacchetto(PacchettoSalvatoDTO pacchettoSalvatoDTO) {
        PacchettoSalvato pacchettoSalvato = contentRepository.findPacchettoSalvatoByCodice(pacchettoSalvatoDTO.getCodice_pacchettoSalvato());
        pacchettoSalvato.setPrenotato(true);
        em.persist(pacchettoSalvato);
    }

    @Override
    public void salvaPacchetto(PacchettoSalvatoDTO pacchettoSalvatoDTO) {
        PacchettoSalvato pacchettoSalvato = new PacchettoSalvato(pacchettoSalvatoDTO);

        String codice_pacchetto = null;
        do {
            codice_pacchetto = RandomStringUtils.randomAlphanumeric(5);
        } while(codicePacchettoSalvatoAlreadyUsed(codice_pacchetto));

        pacchettoSalvato.setCodice_pacchettoSalvato(codice_pacchetto);
        pacchettoSalvato.setUserCreatore(userRepository.findUserByEmail(pacchettoSalvatoDTO.getEmailUserCreatore()));
        pacchettoSalvato.setPacchettoOriginale(contentRepository.findPacchettoByCodice(pacchettoSalvatoDTO.getCodicePacchettoOriginale()));

        for(String codice_trasporto : pacchettoSalvatoDTO.getCodiciTrasporti()) {
            pacchettoSalvato.getTrasportiScelti().add(contentRepository.findTrasportoByCodice(codice_trasporto));
        }

        for(String codice_hotel : pacchettoSalvatoDTO.getCodiciHotels()) {
            pacchettoSalvato.getHotelsScelti().add(contentRepository.findHotelByCodice(codice_hotel));
        }

        for(String codice_escursione : pacchettoSalvatoDTO.getCodiciEscursioni()) {
            pacchettoSalvato.getEscursioni().add(contentRepository.findEscursioneByCodice(codice_escursione));
        }

        em.persist(pacchettoSalvato);
    }

    @Override
    public void salvaPacchettoPredefinito(PacchettoSalvatoDTO pacchettoSalvatoDTO, String localitaPartenza) {
        Pacchetto originale = contentRepository.findPacchettoByCodice(pacchettoSalvatoDTO.getCodicePacchettoOriginale());
        List<TrasportiPacchetto> trasportiPacchetto = originale.getMezziTrasporto();
        for(TrasportiPacchetto trasporto : trasportiPacchetto) {
            if(trasporto.isPredefinito() && trasporto.getTrasporto().getLocalitaPartenza().equals(localitaPartenza)) {
                pacchettoSalvatoDTO.addTrasporto(trasporto.getTrasporto().getCodice_trasporto());
            }
        }

        List<HotelsPacchetto> hotelsPacchetto = originale.getHotels();
        for(HotelsPacchetto hotel : hotelsPacchetto) {
            if(hotel.isPredefinito()) {
                pacchettoSalvatoDTO.addHotel(hotel.getHotel().getCodice_hotel());
            }
        }

        List<EscursioniPacchetto> escursioniPacchetto = originale.getEscursioni();
        for(EscursioniPacchetto escursione : escursioniPacchetto) {
            if(escursione.isPredefinito()) {
                pacchettoSalvatoDTO.addEscursione(escursione.getEscursione().getCodice_escursione());
            }
        }

        salvaPacchetto(pacchettoSalvatoDTO);
    }

    private boolean codicePacchettoSalvatoAlreadyUsed(String codice_pacchetto) {

        PacchettoSalvato pacchettoSalvato = null;

        try {
            pacchettoSalvato = contentRepository.findPacchettoSalvatoByCodice(codice_pacchetto);
        }
        catch (NoResultException e) {

        }

        return pacchettoSalvato!=null;
    }

    @Override
    public void modificaPacchettoSalvato(PacchettoSalvatoDTO pacchettoSalvatoDTO) {
        PacchettoSalvato modifiedPacchetto = contentRepository.findPacchettoSalvatoByCodice(pacchettoSalvatoDTO.getCodice_pacchettoSalvato());

        modifiedPacchetto.setDataPartenza(pacchettoSalvatoDTO.getDataPartenza());
        modifiedPacchetto.setDataRitorno(pacchettoSalvatoDTO.getDataRitorno());
        modifiedPacchetto.getTrasportiScelti().clear();
        modifiedPacchetto.getHotelsScelti().clear();
        modifiedPacchetto.getEscursioni().clear();

        for(String codice_trasporto : pacchettoSalvatoDTO.getCodiciTrasporti()) {
            modifiedPacchetto.getTrasportiScelti().add(contentRepository.findTrasportoByCodice(codice_trasporto));
        }

        for(String codice_hotel : pacchettoSalvatoDTO.getCodiciHotels()) {
            modifiedPacchetto.getHotelsScelti().add(contentRepository.findHotelByCodice(codice_hotel));
        }

        for(String codice_escursione : pacchettoSalvatoDTO.getCodiciEscursioni()) {
            modifiedPacchetto.getEscursioni().add(contentRepository.findEscursioneByCodice(codice_escursione));
        }

        em.merge(modifiedPacchetto);

    }

    @Override
    public void aggiungiPartecipazione(PacchettoSalvatoDTO pacchettoSalvatoDTO) {
        PacchettoSalvato pacchettoSalvato = contentRepository.findPacchettoSalvatoByCodice(pacchettoSalvatoDTO.getCodice_pacchettoSalvato());
        pacchettoSalvato.getUsersPartecipanti().add(userRepository.findUserByEmail(userManagerBean.getUserDTO().getEmail()));
        em.persist(pacchettoSalvato);
    }

    @Override
    public PacchettoDTO getPacchetto(String codice_pacchetto) {
        return dtOsConverter.convertPacchettoToDTO(contentRepository.findPacchettoByCodice(codice_pacchetto));
    }

    @Override
    public PacchettoSalvatoDTO getPacchettoSalvato(String codice_pacchettoSalvato) {
        return dtOsConverter.convertPacchettoSalvatoToDTO(contentRepository.findPacchettoSalvatoByCodice(codice_pacchettoSalvato));
    }

    @Override
    public List<PacchettoSalvatoDTO> getPacchettiPersonali() {
        long idUserCreatore = userRepository.findUserByEmail(userManagerBean.getUserDTO().getEmail()).getId_user();
        /*CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<PacchettoSalvato> c = cb.createQuery(PacchettoSalvato.class);
        Root<PacchettoSalvato> member = c.from(PacchettoSalvato.class);
        c.select(member).where(cb.equal(member.get("id_userCreatore"), idUserCreatore));
        List<PacchettoSalvato> pacchettiPersonali = em.createQuery(c).getResultList();*/

        TypedQuery<PacchettoSalvato> query = em.createQuery("select p from PacchettoSalvato p where p.userCreatore.id_user = :id", PacchettoSalvato.class).setParameter("id", idUserCreatore);
        List<PacchettoSalvato> pacchettiPersonali = query.getResultList();
        List<PacchettoSalvatoDTO> pacchettiPersonaliDTO = new ArrayList<>();
        for(PacchettoSalvato pacchettoSalvato : pacchettiPersonali) {
            pacchettiPersonaliDTO.add(dtOsConverter.convertPacchettoSalvatoToDTO(pacchettoSalvato));
        }
        return pacchettiPersonaliDTO;
    }

    @Override
    public List<PacchettoSalvatoDTO> getPacchettiPartecipati() {
        long idUser = userRepository.findUserByEmail(userManagerBean.getUserDTO().getEmail()).getId_user();
        /*CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<PacchettoSalvato> c = cb.createQuery(PacchettoSalvato.class);
        Root<PacchettoSalvato> member = c.from(PacchettoSalvato.class);
        Join<>
        c.select(member).where(cb.equal(member. ));
        List<PacchettoSalvato> pacchettiPersonali = em.createQuery(c).getResultList();
        List<PacchettoSalvatoDTO> pacchettiPersonaliDTO = new ArrayList<>();
        for(PacchettoSalvato pacchettoSalvato : pacchettiPersonali) {
            pacchettiPersonaliDTO.add(convertPacchettoSalvatoToDTO(pacchettoSalvato));
        }*/

        TypedQuery<PacchettoSalvato> query = em.createQuery("select r from PacchettoSalvato r join r.usersPartecipanti p where p.id_user = :idUser", PacchettoSalvato.class).setParameter("idUser", idUser);
        List<PacchettoSalvato> pacchettiPartecipati = query.getResultList();
        List<PacchettoSalvatoDTO> pacchettiPartecipatiDTO = new ArrayList<>();
        for(PacchettoSalvato pacchettoSalvato : pacchettiPartecipati) {
            pacchettiPartecipatiDTO.add(dtOsConverter.convertPacchettoSalvatoToDTO(pacchettoSalvato));
        }

        return pacchettiPartecipatiDTO;
    }

    @Override
    public List<PacchettoDTO> searchPacchetti(String partenza, String localita, Date dataPartenza, Date dateRitorno) {
        TypedQuery<Pacchetto> query = em.createQuery("select distinct p from Pacchetto p join p.mezziTrasporto m " +
                "where p.localita = :localita " +
                "and p.inizioValidita <= :dataPartenza " +
                "and p.fineValidita >= :dataRitorno " +
                "and m.trasporto.localitaPartenza = :partenza", Pacchetto.class).setParameter("localita", localita).
                                                                                    setParameter("partenza", partenza).
                                                                                    setParameter("dataPartenza", dataPartenza, TemporalType.DATE).
                                                                                    setParameter("dataRitorno", dateRitorno, TemporalType.DATE);
        List<Pacchetto> pacchettiCercati = query.getResultList();
        List<PacchettoDTO> pacchettiCercatiDTO = new ArrayList<>();
        for(Pacchetto pacchetto : pacchettiCercati) {
            pacchettiCercatiDTO.add(dtOsConverter.convertPacchettoToDTO(pacchetto));
        }
        return pacchettiCercatiDTO;
    }
}
