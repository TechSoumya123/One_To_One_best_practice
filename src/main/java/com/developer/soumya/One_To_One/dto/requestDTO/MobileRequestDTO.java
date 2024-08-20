package com.developer.soumya.One_To_One.dto.requestDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MobileRequestDTO(

        @JsonProperty(value = "mobile_name")
        String mobileName,
        @JsonProperty(value = "mobile_brand")
        String mobileBrand
) {
}
