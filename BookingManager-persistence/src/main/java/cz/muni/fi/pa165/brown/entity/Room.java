package cz.muni.fi.pa165.brown.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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

    /** Hotel in which the room is situated */
    @NotNull
    @ManyToOne
    private Hotel hotel;

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
     * Getter for {@link #hotel}
     * @return hotel in which the room is situated
     */
    public Hotel getHotel() {
        return hotel;
    }

    /**
     * Setter for {@link #hotel}
     * @param hotel hotel in which the room is situated
     */
    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Room)) {
            return false;
        }

        Room room = (Room) o;

        if (getId() != null ? !getId().equals(room.getId()) : room.getId() != null) {
            return false;
        }
        if (getCapacity() != null ? !getCapacity().equals(room.getCapacity()) : room.getCapacity() != null) {
            return false;
        }
        if (getPricePerNightPerPerson() != null ? !getPricePerNightPerPerson().equals(room.getPricePerNightPerPerson()) : room
                .getPricePerNightPerPerson() != null) {
            return false;
        }
        return getHotel() != null ? getHotel().equals(room.getHotel()) : room.getHotel() == null;

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getCapacity() != null ? getCapacity().hashCode() : 0);
        result = 31 * result + (getPricePerNightPerPerson() != null ? getPricePerNightPerPerson().hashCode() : 0);
        result = 31 * result + (getHotel() != null ? getHotel().hashCode() : 0);
        return result;
    }
}