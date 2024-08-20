package com.developer.soumya.One_To_One.dto.responseDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MobileResponseDTO(
        @JsonProperty(value = "mobile_id")
        Long id,
        @JsonProperty(value = "mobile_name")
        String mobileName,
        @JsonProperty(value = "mobile_brand")
        String mobileBrand

) {
}
