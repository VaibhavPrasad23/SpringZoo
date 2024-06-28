package com.ics.springnuxt.dto;

import lombok.Data;

@Data
public class UpdateCarDto {
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
}
