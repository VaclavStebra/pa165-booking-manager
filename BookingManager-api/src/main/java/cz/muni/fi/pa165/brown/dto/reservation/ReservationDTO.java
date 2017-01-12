package cz.muni.fi.pa165.brown.dto.reservation;

import java.util.Date;

import javax.validation.constraints.NotNull;

import cz.muni.fi.pa165.brown.dto.room.RoomDTO;
import cz.muni.fi.pa165.brown.dto.user.UserDTO;

/**
 * @author Peter Hutta
 */
public class ReservationDTO {

    private Long id;

    @NotNull
    private Date reservedFrom;

    @NotNull
    private Date reservedTo;

    @NotNull
    private UserDTO user;

    @NotNull
    private RoomDTO room;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getReservedFrom() {
        return reservedFrom;
    }

    public void setReservedFrom(Date reservedFrom) {
        this.reservedFrom = reservedFrom;
    }

    public Date getReservedTo() {
        return reservedTo;
    }

    public void setReservedTo(Date reservedTo) {
        this.reservedTo = reservedTo;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public RoomDTO getRoom() {
        return room;
    }

    public void setRoom(RoomDTO room) {
        this.room = room;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ReservationDTO that = (ReservationDTO) o;

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
        int result = getReservedFrom() != null ? getReservedFrom().hashCode() : 0;
        result = 31 * result + (getReservedTo() != null ? getReservedTo().hashCode() : 0);
        result = 31 * result + (getUser() != null ? getUser().hashCode() : 0);
        result = 31 * result + (getRoom() != null ? getRoom().hashCode() : 0);
        return result;
    }
}
