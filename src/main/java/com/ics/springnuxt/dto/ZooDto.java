package com.ics.springnuxt.dto;

import com.ics.springnuxt.model.Zoo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ZooDto {
    private Long id;
    private String animal;
    private String transfer;
    private Integer quantity;
    private String image;
    private String food;

    private Boolean gender;

    public ZooDto(Zoo entity) {
        this.id = entity.getId();
        this.animal = entity.getAnimal();
        this.transfer = entity.getTransfer();
        this.quantity = entity.getQuantity();
        this.food = entity.getFood();
        this.image = entity.getImage();
        this.gender = entity.getGender();
    }
}
