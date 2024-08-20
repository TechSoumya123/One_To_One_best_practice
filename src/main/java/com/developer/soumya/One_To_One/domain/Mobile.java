package com.developer.soumya.One_To_One.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@NoArgsConstructor
@Entity
@Table(name = "tbl_mobile")
public class Mobile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "mobile_name", length = 50)
    private String mobileName;

    @Column(name = "mobile_brand", length = 20, nullable = false)
    private String mobileBrand;

}
