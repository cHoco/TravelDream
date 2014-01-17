package it.polimi.traveldream.ejb;

import it.polimi.traveldream.ejb.dtos.UserDTO;

import javax.ejb.Local;

/**
 * Created by cHoco on 06/01/14.
 */

@Local
public interface UserManager {

    public void save(UserDTO user);

    public boolean emailAlreadyUsed(String email);

    public UserDTO getUserDTO();

}