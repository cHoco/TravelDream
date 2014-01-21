package it.polimi.traveldream.ejb;

import it.polimi.traveldream.ejb.dtos.UserDTO;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Asus on 21/01/14.
 */

@Stateless
public class AdminManagerBean implements UserManager {

    @PersistenceContext
    EntityManager em;

    @Resource
    EJBContext context;

    @Override
    public void saveUser(UserDTO user) {

    }

    @Override
    public void saveAdmin(UserDTO admin) {

    }

    @Override
    public void saveAu(UserDTO au) {


    }

    @Override
    public boolean emailAlreadyUsed(String email) {
        return false;
    }

    @Override
    public UserDTO getUserDTO() {
        return null;
    }
}
