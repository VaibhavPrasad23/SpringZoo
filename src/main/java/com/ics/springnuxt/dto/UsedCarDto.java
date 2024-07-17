package com.ics.springnuxt.dto;

import com.ics.springnuxt.entity.UsedCar;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsedCarDto {
    private Integer id;
    private String name;
    private String brand;
    private String description;
    private Integer price;
    private String type;
    private Integer year;
    private String pic;
    private String ownername;
    private String ownernum;
    private String address;

    private Boolean fuel;

    public UsedCarDto(UsedCar entity) 
    {
        this.id = entity.getId();
        this.name = entity.getName();
        this.brand = entity.getBrand();
        this.description = entity.getDescription();
        this.price = entity.getPrice();
        this.type = entity.getType();
        this.year = entity.getYear();
        this.pic = entity.getPic();
        this.ownername = entity.getOwnername();
        this.ownernum = entity.getOwnernum();
        this.address = entity.getAddress();

    }
}
