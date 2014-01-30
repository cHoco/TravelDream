package it.polimi.traveldream.ejb;

import it.polimi.traveldream.ejb.entities.*;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Created by cHoco on 30/01/14.
 */
@ApplicationScoped
public class ContentRepository {

    @PersistenceContext
    private EntityManager em;

    public Escursione findEscursioneByCodice(String codice_escursione) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Escursione> c = cb.createQuery(Escursione.class);
        Root<Escursione> member = c.from(Escursione.class);
        c.select(member).where(cb.equal(member.get("codice_escursione"), codice_escursione));
        return em.createQuery(c).getSingleResult();
    }

    public Trasporto findTrasportoByCodice(String codice_trasporto) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Trasporto> c = cb.createQuery(Trasporto.class);
        Root<Trasporto> member = c.from(Trasporto.class);
        c.select(member).where(cb.equal(member.get("codice_trasporto"), codice_trasporto));
        return em.createQuery(c).getSingleResult();
    }

    public Hotel findHotelByCodice(String codice_hotel) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Hotel> c = cb.createQuery(Hotel.class);
        Root<Hotel> member = c.from(Hotel.class);
        c.select(member).where(cb.equal(member.get("codice_hotel"), codice_hotel));
        return em.createQuery(c).getSingleResult();
    }

    public Pacchetto findPacchettoByCodice(String codice_pacchetto) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Pacchetto> c = cb.createQuery(Pacchetto.class);
        Root<Pacchetto> member = c.from(Pacchetto.class);
        c.select(member).where(cb.equal(member.get("codice_pacchetto"), codice_pacchetto));
        return em.createQuery(c).getSingleResult();
    }

    public PacchettoSalvato findPacchettoSalvatoByCodice(String codice_pacchettoSalvato) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<PacchettoSalvato> c = cb.createQuery(PacchettoSalvato.class);
        Root<PacchettoSalvato> member = c.from(PacchettoSalvato.class);
        c.select(member).where(cb.equal(member.get("codice_pacchettoSalvato"), codice_pacchettoSalvato));
        return em.createQuery(c).getSingleResult();
    }
}
