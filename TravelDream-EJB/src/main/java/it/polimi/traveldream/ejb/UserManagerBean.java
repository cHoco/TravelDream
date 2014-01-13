package it.polimi.traveldream.ejb;

import it.polimi.traveldream.ejb.dtos.UserDTO;
import it.polimi.traveldream.ejb.entities.Group;
import it.polimi.traveldream.ejb.entities.User;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    public void save(UserDTO user) {
        User newUser = new User(user);
        List<Group> groups = new ArrayList<Group>();
        groups.add(Group.USER);
        newUser.setGroups(groups);
        em.persist(newUser);
    }

    @Override
    public UserDTO getUserDTO() {
        UserDTO userDTO = convertToDTO(getPrincipalUser());
        return userDTO;
    }


    public User find(String email) {
        return em.find(User.class, email);
    }

    public List<User> getAllUsers() {
        return em.createNamedQuery(User.FIND_ALL, User.class)
                .getResultList();
    }

    public void remove(String email) {
        User user = find(email);
        em.remove(user);
    }

    public void remove(User user) {
        em.remove(user);
    }


    public User getPrincipalUser() {
        return find(getPrincipalEmail());
    }


    public String getPrincipalEmail() {
        return context.getCallerPrincipal().getName();
    }

    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        return userDTO;
    }
}
