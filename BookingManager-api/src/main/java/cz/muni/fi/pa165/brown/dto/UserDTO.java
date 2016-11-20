package cz.muni.fi.pa165.brown.dto;

/**
 * @author Vaclav Stebra
 */
public class UserDTO {

    private Long id;
    private String name;
    private String surname;
    private String address;
    private String email;
    private String password;
    private boolean admin;

    public UserDTO() {

    }

    /**
     *
     * @return user id
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id user id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return user name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name user name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return user surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     *
     * @param surname user surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     *
     * @return user address
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @param address user address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * @return user email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email user email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return user password
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password user password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return true if user is admin
     */
    public boolean isAdmin() {
        return admin;
    }

    /**
     *
     * @param admin flag whether user is admin
     */
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Override
    public int hashCode() {
        return 31 + ((email == null) ? 0 : email.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if ( ! (obj instanceof UserDTO)) {
            return false;
        }
        UserDTO user = (UserDTO) obj;
        if (email == null) {
            if (user.email != null) {
                return false;
            }
        }
        return email.equals(user.email);
    }
}
