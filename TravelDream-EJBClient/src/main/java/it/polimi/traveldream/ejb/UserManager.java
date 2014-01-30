package it.polimi.traveldream.ejb;

import it.polimi.traveldream.ejb.dtos.UserDTO;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by cHoco on 06/01/14.
 */

@Local
public interface UserManager {

    public void saveUser(UserDTO user);

    public void saveAdmin(UserDTO admin);

    //@RolesAllowed({"ADMIN"})
    public void saveAu(UserDTO au);

    public boolean emailAlreadyUsed(String email);

    public UserDTO getUserDTO();

    public List<UserDTO> getAdvanceUsers();

}