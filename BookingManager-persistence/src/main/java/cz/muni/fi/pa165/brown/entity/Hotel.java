/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.brown.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
/**
 *
 * @author Michal Hagara
 */
@Entity
@Table(name="Hotels")
public class Hotel implements Serializable {

    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Column(nullable = false)
    private String name;
    
    @NotNull
    @Column(nullable = false)
    private String address;
    
    
    @NotNull
    @Column(nullable = false)
    private String phone;
    
    
    public Hotel()
    {
    }
    
     /**
     *
     * @return id of hotel
     */
    public Long getId()
    {
        return id;
    }
    
     /**
     *
     * @param id id of hotel
     */
    public void setId(Long id)
    {
        this.id = id;
    }
    
    /**
     *
     * @return name of hotel
     */
    public String getName()
    {
        return name;
    }
            
    /**
     *
     * @param name name of hotel
     */
    public void setName(String name)
    {
        this.name = name;
    }
            
    /**
     *
     * @return address of hotel
     */
    public String getAddress()
    {
        return address;
    }
    
    /**
     *
     * @param address address of hotel
     */
    public void setAddress(String address)
    {
        this.address = address;
    }
            
    /**
     *
     * @return phone no. of hotel
     */
    public String getPhone()
    {
        return phone;
    }
            
    /**
     *
     * @param phone phone no. of hotel
     */
    public void setPhone(String phone)
    {
        this.phone = phone;
    }
                    
              
    
    
}
