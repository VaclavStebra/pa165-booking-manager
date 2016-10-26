package cz.muni.fi.pa165.brown.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Class for user Entity
 *
 * @author Vaclav Stebra
 */
@Entity
@Table(name="Users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    private String address;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private boolean admin;

    public User() {
    }

    /**
     *
     * @return id of user
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id id of user
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return name of user
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name name of user
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return surname of user
     */
    public String getSurname() {
        return surname;
    }

    /**
     *
     * @param surname surname of user
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     *
     * @return address of user
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @param address address of user
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * @return email of user
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email email of user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return hashed password of user
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password hashed password of user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return true if user is System Administrator, false otherwise
     */
    public boolean isAdmin() {
        return admin;
    }

    /**
     *
     * @param admin flag whether user is System Administrator
     */
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if ( ! (o instanceof User)) {
            return false;
        }
        User user = (User) o;
        if (email == null) {
            if (user.email != null) {
                return false;
            }
        }
        return email.equals(user.email);

    }

    @Override
    public int hashCode() {
        return 31 + ((email == null) ? 0 : email.hashCode());
    }
}
