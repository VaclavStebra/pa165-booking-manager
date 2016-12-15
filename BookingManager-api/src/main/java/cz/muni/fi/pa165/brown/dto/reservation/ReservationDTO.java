package cz.muni.fi.pa165.brown.dto.reservation;

import java.util.Date;

import cz.muni.fi.pa165.brown.dto.room.RoomDTO;
import cz.muni.fi.pa165.brown.dto.user.UserDTO;

/**
 * @author Peter Hutta
 */
public class ReservationDTO {

    private Long id;

    private Date reservedFrom;

    private Date reservedTo;

    private UserDTO user;

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

        if (user != null ? !user.equals(that.user) : that.user != null) {
            return false;
        }
        if (room != null ? !room.equals(that.room) : that.room != null) {
            return false;
        }
        if (reservedFrom != null ? !reservedFrom.equals(that.reservedFrom) : that.reservedFrom != null) {
            return false;
        }
        return reservedTo != null ? reservedTo.equals(that.reservedTo) : that.reservedTo == null;
    }

    @Override
    public int hashCode() {
        int result = reservedFrom != null ? reservedFrom.hashCode() : 0;
        result = 31 * result + (reservedTo != null ? reservedTo.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (room != null ? room.hashCode() : 0);
        return result;
    }
}
