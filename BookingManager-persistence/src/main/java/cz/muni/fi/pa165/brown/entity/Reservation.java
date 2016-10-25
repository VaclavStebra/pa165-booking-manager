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
    private Date from;

    @NotNull
    private Date to;

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
     * Returns date from of the reservation
     * @return date from of the reservation
     */
    public Date getFrom() {
        return from;
    }

    /**
     * Sets date from of the reservation
     * @param from date from of the reservation
     */
    public void setFrom(Date from) {
        this.from = from;
    }

    /**
     * Returns date to of the reservation
     * @return date to of the reservation
     */
    public Date getTo() {
        return to;
    }

    /**
     * Sets date to of the reservation
     * @param to date to of the reservation
     */
    public void setTo(Date to) {
        this.to = to;
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

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) {
            return false;
        }
        if (getUser() != null ? !getUser().equals(that.getUser()) : that.getUser() != null) {
            return false;
        }
        if (getRoom() != null ? !getRoom().equals(that.getRoom()) : that.getRoom() != null) {
            return false;
        }
        if (getFrom() != null ? !getFrom().equals(that.getFrom()) : that.getFrom() != null) {
            return false;
        }
        return getTo() != null ? getTo().equals(that.getTo()) : that.getTo() == null;

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getUser() != null ? getUser().hashCode() : 0);
        result = 31 * result + (getRoom() != null ? getRoom().hashCode() : 0);
        result = 31 * result + (getFrom() != null ? getFrom().hashCode() : 0);
        result = 31 * result + (getTo() != null ? getTo().hashCode() : 0);
        return result;
    }
}
