package com.example.assign1.dto;

import lombok.Data;

@Data //Generates getters for all fields, a useful toString method, and hashCode and equals implementations that check all non-transient fields

public class SupplierDto {
    private Long id;
    private String name;


}
