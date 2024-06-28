package com.ics.springnuxt.entity;

import org.hibernate.envers.Audited;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Audited
@Table(name = "automobile2")
public class UsedCar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column
    private String name;
    
    @Column
    private String brand;
    
    @Column
    private String description;
    
    @Column
    private Integer price;
    
    @Column
    private String type;

    @Column
    private Integer year;
    
    @Column
    private String pic;
    
    @Column
    private Boolean fuel;
    
    @Column
    private String ownername;
    
    @Column
    private String ownernum;
    
    @Column
    private String address;
}
