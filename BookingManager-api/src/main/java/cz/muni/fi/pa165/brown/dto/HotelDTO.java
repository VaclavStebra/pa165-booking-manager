package cz.muni.fi.pa165.brown.dto;

import javax.validation.constraints.NotNull;

/**
 *
 * @author Michal Hagara
 */

public class HotelDTO
{

    private Long id;

    private String name;

    private String address;

    private String phone;


    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + ((address == null) ? 0 : address.hashCode());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if ( ! (obj instanceof HotelDTO)) {
            return false;
        }
        HotelDTO other = (HotelDTO) obj;
        if (address == null) {
            if (other.address != null) {
                return false;
            }
        }
        return address.equals(other.address);

    }




}
