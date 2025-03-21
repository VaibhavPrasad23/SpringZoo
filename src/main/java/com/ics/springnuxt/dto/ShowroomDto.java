package com.ics.springnuxt.dto;

import com.ics.springnuxt.entity.Car;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShowroomDto {
    private Integer id;
    private String name;
    private String brand;
    private String description;
    private Integer price;
    private String type;
    private Integer year;
    private String pic;

    private Boolean fuel;

    public ShowroomDto(Car entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.brand = entity.getBrand();
        this.description = entity.getDescription();
        this.price = entity.getPrice();
        this.type = entity.getType();
        this.year = entity.getYear();
        this.pic = entity.getPic();
    }
}
