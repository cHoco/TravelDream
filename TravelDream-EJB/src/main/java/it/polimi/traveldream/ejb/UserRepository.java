package it.polimi.traveldream.ejb;

import it.polimi.traveldream.ejb.dtos.UserDTO;
import it.polimi.traveldream.ejb.entities.User;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by cHoco on 30/01/14.
 */
@ApplicationScoped
public class UserRepository {

    @PersistenceContext
    private EntityManager em;

    /**
     * Trova lo user specifico nel database usando l'e-mail come chiave
     */
    private User findUserByEmail(String email) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> c = cb.createQuery(User.class);
        Root<User> member = c.from(User.class);
        c.select(member).where(cb.equal(member.get("email"), email));
        return em.createQuery(c).getSingleResult();
    }

    public User findUserByID(long id) {
        return em.find(User.class, id);
    }

    public List<User> getAllUsers() {
        return em.createNamedQuery(User.FIND_ALL, User.class)
                .getResultList();
    }

}
