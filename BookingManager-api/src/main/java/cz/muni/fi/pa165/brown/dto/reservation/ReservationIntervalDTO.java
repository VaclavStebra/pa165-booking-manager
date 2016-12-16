package cz.muni.fi.pa165.brown.dto.reservation;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author Vaclav Stebra
 */
public class ReservationIntervalDTO {

    @NotNull
    private Date from;

    @NotNull
    private Date to;

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof ReservationIntervalDTO)) return false;

        ReservationIntervalDTO that = (ReservationIntervalDTO) o;

        if (!this.getFrom().equals(that.getFrom())) return false;
        return this.getTo().equals(that.getTo());

    }

    @Override
    public int hashCode() {
        int result = (this.getFrom() == null) ? 1 : this.getFrom().hashCode();
        result = 31 * result + ((this.getTo() == null) ? 1 :this.getTo().hashCode());
        return result;
    }
}
