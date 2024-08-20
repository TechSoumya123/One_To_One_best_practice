package com.developer.soumya.One_To_One.repository;

import com.developer.soumya.One_To_One.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    void deleteByMobileId(Long mobileId);

}