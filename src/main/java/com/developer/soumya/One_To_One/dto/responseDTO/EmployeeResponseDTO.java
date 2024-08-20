package com.developer.soumya.One_To_One.dto.responseDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;


public record EmployeeResponseDTO(

        @JsonProperty(value = "emp_id")
        Long id,
        @JsonProperty(value = "emp_name")
        String name,
        @JsonProperty(value = "emp_address")
        String address,
        @JsonProperty(value = "emp_mobile")
        MobileResponseDTO mobileResponseDTO
) implements Serializable {
}
