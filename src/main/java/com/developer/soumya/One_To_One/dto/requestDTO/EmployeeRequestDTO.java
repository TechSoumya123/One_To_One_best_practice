package com.developer.soumya.One_To_One.dto.requestDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;


public record EmployeeRequestDTO(

        @NotBlank(message = "Employee name must be required")
        @JsonProperty(value = "emp_name")
        String name,

        @NotBlank(message = "Employee address must not be empty")
        @JsonProperty(value = "emp_address")
        String address,

        @JsonProperty(value = "emp_mobile")
        MobileRequestDTO mobileRequestDTO

)implements Serializable {
}
