package cz.muni.fi.pa165.brown.dto;

import java.math.BigDecimal;

/**
 * DTO for Room entity
 *
 * @author Dominik Labuda
 */
public class RoomDTO {

    /** Internal generated ID */
    private Long id;

    /** Room capacity */
    private Integer capacity;

    /** Room price per night */
    private BigDecimal pricePerNightPerPerson;

    /** Hotel in which the room is situated */
    private HotelDTO hotel;

    /** Room identifier, such as simple room number or something more complicated like 'D-1-10' */
    private String roomIdentifier;

    /**
     * Constructor
     */
    public RoomDTO() {

    }

    /**
     * Constructor
     * @param capacity capacity
     * @param pricePerNightPerPerson room price per night for one person
     * @param hotel hotel containing this room
     * @param roomIdentifier room identifier
     */
    public RoomDTO(Integer capacity, BigDecimal pricePerNightPerPerson, HotelDTO hotel, String roomIdentifier) {
        this.capacity = capacity;
        this.pricePerNightPerPerson = pricePerNightPerPerson;
        this.hotel = hotel;
        this.roomIdentifier = roomIdentifier;
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
    public HotelDTO getHotel() {
        return hotel;
    }

    /**
     * Setter for {@link #hotel}
     * @param hotel hotel in which the room is situated
     */
    public void setHotel(HotelDTO hotel) {
        this.hotel = hotel;
    }

    /**
     * Getter for {@link #roomIdentifier}
     * @return identifier of the room
     */
    public String getRoomIdentifier() {
        return roomIdentifier;
    }

    /**
     * Setter for {@link #roomIdentifier}
     * @param roomIdentifier identifier of the room
     */
    public void setRoomIdentifier(String roomIdentifier) {
        this.roomIdentifier = roomIdentifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RoomDTO)) {
            return false;
        }

        RoomDTO roomDTO = (RoomDTO) o;

        if (getHotel() != null ? !getHotel().equals(roomDTO.getHotel()) : roomDTO.getHotel() != null) {
            return false;
        }
        return getRoomIdentifier() != null ? getRoomIdentifier().equals(roomDTO.getRoomIdentifier()) : roomDTO.getRoomIdentifier() == null;

    }

    @Override
    public int hashCode() {
        int result = getHotel() != null ? getHotel().hashCode() : 0;
        result = 31 * result + (getRoomIdentifier() != null ? getRoomIdentifier().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RoomDTO{" +
                "id=" + id +
                ", capacity=" + capacity +
                ", pricePerNightPerPerson=" + pricePerNightPerPerson +
                ", hotel=" + hotel +
                ", roomIdentifier='" + roomIdentifier + '\'' +
                '}';
    }
}
