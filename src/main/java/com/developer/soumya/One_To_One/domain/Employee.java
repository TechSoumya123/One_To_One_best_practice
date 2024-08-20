package com.developer.soumya.One_To_One.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@NoArgsConstructor
@Entity
@Table(name = "tbl_employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long id;

    @Column(name = "employee_name", length = 20, nullable = false)
    private String name;

    private String address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "mobile_id")
    private Mobile mobile;
}
