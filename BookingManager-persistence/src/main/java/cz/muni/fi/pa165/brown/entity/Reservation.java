package cz.muni.fi.pa165.brown.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Class for Reservation entity
 *
 * @author Peter Hutta
 * @version 1.0  25.10.2016
 */
@Entity
@Table(name = "Reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    private User user;

    @NotNull
    @ManyToOne
    private Room room;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date reservedFrom;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date reservedTo;

    public Reservation() {
    }

    /**
     * Returns id of the reservation
     * @return id of the reservation
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id of the reservations
     * @param id id of the reservation
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns user of the reservation
     * @return user of the reservation
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user of the reservations
     * @param user of the reservation
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Returns room of the reservation
     * @return room of the reservation
     */
    public Room getRoom() {
        return room;
    }

    /**
     * Sets room of the reservation
     * @param room room of the reservation
     */
    public void setRoom(Room room) {
        this.room = room;
    }

    /**
     * Returns date from which the reservation is valid
     * @return date from which the reservation is valid
     */
    public Date getReservedFrom() {
        return reservedFrom;
    }

    /**
     * Sets date from which the reservation is valid
     * @param reservedFrom date from which the reservation is valid
     */
    public void setReservedFrom(Date reservedFrom) {
        this.reservedFrom = reservedFrom;
    }

    /**
     * Returns date to which the reservation is valid
     * @return date to which the reservation is valid
     */
    public Date getReservedTo() {
        return reservedTo;
    }

    /**
     * Sets date to which the reservation is valid
     * @param reservedTo date to which the reservation is valid
     */
    public void setReservedTo(Date reservedTo) {
        this.reservedTo = reservedTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof Reservation)) {
            return false;
        }

        Reservation that = (Reservation) o;

        if (getUser() != null ? !getUser().equals(that.getUser()) : that.getUser() != null) {
            return false;
        }
        if (getRoom() != null ? !getRoom().equals(that.getRoom()) : that.getRoom() != null) {
            return false;
        }
        if (getReservedFrom() != null ? !getReservedFrom().equals(that.getReservedFrom()) : that.getReservedFrom() != null) {
            return false;
        }
        return getReservedTo() != null ? getReservedTo().equals(that.getReservedTo()) : that.getReservedTo() == null;

    }

    @Override
    public int hashCode() {
        int result = getUser() != null ? getUser().hashCode() : 0;
        result = 31 * result + (getRoom() != null ? getRoom().hashCode() : 0);
        result = 31 * result + (getReservedFrom() != null ? getReservedFrom().hashCode() : 0);
        result = 31 * result + (getReservedTo() != null ? getReservedTo().hashCode() : 0);
        return result;
    }
}
