package it.polimi.traveldream.web.servlets;

import it.polimi.traveldream.ejb.UserManager;
import it.polimi.traveldream.ejb.dtos.UserDTO;

import javax.ejb.EJB;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by Asus on 21/01/14.
 */
@WebListener
public class InitServlet implements ServletContextListener {

    private UserDTO admin;

    @EJB
    private UserManager userManager;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        if(!userManager.emailAlreadyUsed("admin@td.it")) {
            admin = new UserDTO();

            admin.setEmail("admin@td.it");
            admin.setFirstName("Mino");
            admin.setLastName("Raiola");
            admin.setPassword("balotelli");


            userManager.saveAdmin(admin);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
