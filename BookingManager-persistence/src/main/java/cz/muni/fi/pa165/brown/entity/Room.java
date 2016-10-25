package cz.muni.fi.pa165.brown.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Room representation
 *
 * @author Dominik Labuda
 */
@Entity
@Table(name="Rooms")
public class Room implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Room capacity */
    @NotNull
    private Integer capacity;

    /** Room price per night */
    @NotNull
    private BigDecimal pricePerNightPerPerson;

    /** ID of the hotel in which the room is situated */
    @NotNull
    private Long hotelId;

    /** ID of the client currently using the room */
    private Long clientId;

    /** Indicates whether the room is free */
    @NotNull
    private Boolean free;

    /**
     * Constructor
     */
    public Room() {

    }

    /**
     * Getter for {@link #id}
     * @return id of the room
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter for {@link #id}
     * @param id id of the room
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter for {@link #capacity}
     * @return capacity of the room
     */
    public Integer getCapacity() {
        return capacity;
    }

    /**
     * Setter for {@link #capacity}
     * @param capacity capacity of the room
     */
    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    /**
     * Getter for {@link #pricePerNightPerPerson}
     * @return price of room night/person
     */
    public BigDecimal getPricePerNightPerPerson() {
        return pricePerNightPerPerson;
    }

    /**
     * Setter for {@link #pricePerNightPerPerson}
     * @param pricePerNightPerPerson price of room night/person
     */
    public void setPricePerNightPerPerson(BigDecimal pricePerNightPerPerson) {
        this.pricePerNightPerPerson = pricePerNightPerPerson;
    }

    /**
     * Getter for {@link #hotelId}
     * @return id of the hotel in which the room is situated
     */
    public Long getHotelId() {
        return hotelId;
    }

    /**
     * Setter for {@link #hotelId}
     * @param hotelId id of the hotel in which the room is situated
     */
    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    /**
     * Getter for {@link #clientId}
     * @return clients id
     */
    public Long getClientId() {
        return clientId;
    }

    /**
     * Setter for {@link #clientId}
     * @param clientId clients id
     */
    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    /**
     * Getter for {@link #free}
     * @return true if the room is free, false otherwise
     */
    public Boolean isFree() {
        return free;
    }

    /**
     * Setter for {@link #free}
     * @param free true if room is free, false if the room is occupied
     */
    public void setFree(Boolean free) {
        this.free = free;
    }
}