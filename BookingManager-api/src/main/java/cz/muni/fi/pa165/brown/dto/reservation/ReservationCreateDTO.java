package cz.muni.fi.pa165.brown.dto.reservation;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author Peter Hutta
 */
public class ReservationCreateDTO {

    private Long id;

    @NotNull
    private Date reservedFrom;

    @NotNull
    private Date reservedTo;

    private Long roomId;


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

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ReservationCreateDTO that = (ReservationCreateDTO) o;

        if (getReservedFrom() != null ? !getReservedFrom().equals(that.getReservedFrom()) : that.getReservedFrom() != null) {
            return false;
        }
        return getReservedTo() != null ? getReservedTo().equals(that.getReservedTo()) : that.getReservedTo() == null;
    }

    @Override
    public int hashCode() {
        int result = getReservedFrom() != null ? getReservedFrom().hashCode() : 0;
        result = 31 * result + (getReservedTo() != null ? getReservedTo().hashCode() : 0);
        return result;
    }
}
