package com.ics.springnuxt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "zoo_db")
public class Zoo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private String animal;
    
    @Column
    private String transfer;
    
    @Column
    private Integer quantity;
    
    @Column
    private String food;

    @Column
    private Boolean gender;
    
    @Column
    private String image;
}
