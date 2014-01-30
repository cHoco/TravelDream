package it.polimi.traveldream.ejb;

import it.polimi.traveldream.ejb.dtos.UserDTO;
import it.polimi.traveldream.ejb.entities.Group;
import it.polimi.traveldream.ejb.entities.User;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
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

    @Inject
    private UserRepository userRepository;

    @Inject
    private DTOsConverter dtOsConverter;

    @Override
    public void saveUser(UserDTO user) {

        save(user, Group.USER);

    }

    @Override
    public void saveAdmin(UserDTO admin) {

        save(admin, Group.ADMIN);

    }

    //@RolesAllowed({"ADMIN"})
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
            user = userRepository.findUserByEmail(email);
        }
        catch (NoResultException e) {

        }

        return user!=null;
    }

    @Override
    public UserDTO getUserDTO() {
        UserDTO userDTO = dtOsConverter.convertToDTO(getPrincipalUser());
        return userDTO;
    }

    @Override
    public List<UserDTO> getAdvanceUsers() {

        List<UserDTO> list = new ArrayList<>();
        List<User> users = userRepository.getAllUsersByGroup(Group.ADVANCED_USER);

        for (User user: users){
            UserDTO userDTO = dtOsConverter.convertToDTO(user);
            list.add(userDTO);
        }

        return list;
    }

    /**
     * Restituisce una lista di tutti gli utenti presenti nel database sfruttando la query definita nella classe User
     * */
    public List<User> getAllUsers() {
        return em.createNamedQuery(User.FIND_ALL, User.class)
                .getResultList();
    }

    public void remove(long id) {
        User user = userRepository.findUserByID(id);
        em.remove(user);
    }

    public void remove(User user) {
        em.remove(user);
    }

    /**
     * Ritorna lo user che sta conversando con l' EJB
     * */
    private User getPrincipalUser() {
        return  userRepository.findUserByEmail(getPrincipalId());
        //return userRepository.findUserByID(getPrincipalId());
    }


    private String getPrincipalId() {
        return context.getCallerPrincipal().getName();
        //return Long.parseLong(context.getCallerPrincipal().getName());
    }


}
