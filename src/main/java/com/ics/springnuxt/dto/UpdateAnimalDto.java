package com.ics.springnuxt.dto;

import lombok.Data;

@Data
public class UpdateAnimalDto {
    private String animal;
    private String transfer;
    private Integer quantity;
    private String food;

    private Boolean gender;
}
