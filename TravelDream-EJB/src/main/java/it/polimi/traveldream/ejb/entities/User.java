package it.polimi.traveldream.ejb.entities;

import it.polimi.traveldream.ejb.dtos.UserDTO;
import org.apache.commons.codec.digest.DigestUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by cHoco on 06/01/14.
 */

@Entity
@Table(name = "USERS")
@NamedQueries({
        @NamedQuery(name=User.FIND_ALL,
                query="SELECT u FROM User u ORDER BY u.registrationDate ASC")
})

public class User implements Serializable{
    private static final long serialVersionUID = 1L;
    public static final String FIND_ALL = "User.findAll";

    @Id
    private String email;

    private String firstName;

    private String lastName;

    private String password; //sha-512 + hex

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date registrationDate;

    @ElementCollection(targetClass = Group.class)
    @CollectionTable(name = "USERS_GROUPS",
            joinColumns = @JoinColumn(name = "email"))
    @Enumerated(EnumType.STRING)
    @Column(name="group_name")
    private List<Group> groups;

    public User() {
        super();
    }

    public User(UserDTO user){

        this.email        = user.getEmail();
        this.firstName    = user.getFirstName();
        this.lastName     = user.getLastName();
        this.password     = DigestUtils.sha512Hex(user.getPassword());
        this.registrationDate = new Date();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password in SHA512 HEX representation
     */
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return "User [email=" + email + ", firstName=" + firstName
                + ", lastName=" + lastName + ", password=" + password
                + ", registeredOn=" + registrationDate + ", groups=" + groups + "]";
    }
}
