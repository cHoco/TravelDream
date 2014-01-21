package it.polimi.traveldream.ejb;

import it.polimi.traveldream.ejb.dtos.UserDTO;
import it.polimi.traveldream.ejb.entities.Group;
import it.polimi.traveldream.ejb.entities.User;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cHoco on 10/01/14.
 */

@Stateless
public class UserManagerBean implements UserManager {

    @PersistenceContext
    private EntityManager em;

    @Resource
    private EJBContext context;

    @Override
    public void saveUser(UserDTO user) {

        save(user, Group.USER);

    }

    @Override
    public void saveAdmin(UserDTO admin) {

        save(admin, Group.ADMIN);

    }

    @RolesAllowed({"ADMIN"})
    @Override
    public void saveAu(UserDTO au) {

        save(au, Group.ADVANCED_USER);

    }

    private void save(UserDTO user, Group group){

        User newUser = new User(user);
        List<Group> groups = new ArrayList<Group>();
        groups.add(group);
        newUser.setGroups(groups);
        em.persist(newUser);

    }


    /**
    * Controlla se la e-mail è già presente nel database
    */
   @Override
    public boolean emailAlreadyUsed(String email) {

        User user = null;

        try {
            user = findByEmail(email);
        }
        catch (NoResultException e) {

        }

        return user!=null;
    }


    /**
     * Trova lo user specifico nel database usando l'e-mail come chiave
     */
     private User findByEmail(String email) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> c = cb.createQuery(User.class);
        Root<User> member = c.from(User.class);
        c.select(member).where(cb.equal(member.get("email"), email));
        return em.createQuery(c).getSingleResult();
    }

    @Override
    public UserDTO getUserDTO() {
        UserDTO userDTO = convertToDTO(getPrincipalUser());
        return userDTO;
    }


    public User findByID(long id) {
        return em.find(User.class, id);
    }

    /**
     * Restituisce una lista di tutti gli utenti presenti nel database sfruttando la query definita nella classe User
     * */
    public List<User> getAllUsers() {
        return em.createNamedQuery(User.FIND_ALL, User.class)
                .getResultList();
    }

    public void remove(long id) {
        User user = findByID(id);
        em.remove(user);
    }

    public void remove(User user) {
        em.remove(user);
    }

    /**
     * Ritorna lo user che sta conversando con l' EJB
     * */
    public User getPrincipalUser() {
        return findByID(getPrincipalEmail());
    }

    public long getPrincipalEmail() {
        return Long.parseLong(context.getCallerPrincipal().getName());
    }

    /**
     * Realizza il Data Transfer Object estraendo le informazioni dal database
     * */
    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        return userDTO;
    }

}
