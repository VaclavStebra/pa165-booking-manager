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
    @Column(nullable=false)
    private String name;

    @NotNull
    @Column(nullable=false)
    private String surname;

    @NotNull
    @Column(nullable=false)
    private String address;

    @NotNull
    @Column(nullable=false, unique=true)
    private String email;

    @NotNull
    @Column(nullable=false)
    private String password;

    @NotNull
    @Column(nullable=false)
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
        if (getEmail() == null) {
            if (user.getEmail() != null) {
                return false;
            }
        }
        return getEmail().equals(user.getEmail());

    }

    @Override
    public int hashCode() {
        return 31 + ((getEmail() == null) ? 0 : getEmail().hashCode());
    }
}
