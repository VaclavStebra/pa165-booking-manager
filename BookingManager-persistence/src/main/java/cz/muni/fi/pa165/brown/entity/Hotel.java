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
                    
              
    
    
}
