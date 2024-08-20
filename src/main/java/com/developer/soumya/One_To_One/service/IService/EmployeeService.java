package com.developer.soumya.One_To_One.service.IService;

import com.developer.soumya.One_To_One.domain.Employee;
import com.developer.soumya.One_To_One.dto.requestDTO.EmpReqDTO;
import com.developer.soumya.One_To_One.dto.requestDTO.EmployeeRequestDTO;
import com.developer.soumya.One_To_One.dto.responseDTO.EmployeeResponseDTO;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Employee saveNewEmployee(Long mobileId, Employee employee);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long empId);

    Optional<Employee> getEmployee(Long empId);

    Employee updatEmployee(Long empId, Employee employee);

    Employee deleteEmployee(Long empId);

    Employee addMobileToEmployee(Long empId, Long mobileId);

    Employee removeMobileFromEmployee(Long empId);

    // OPTIMIZE -  2
    EmployeeResponseDTO saveEmployeeWithMobile(EmployeeRequestDTO employeeRequestDTO);

    Employee saveEmployee(Employee employee);

    Object saveEmpAnotherWay(EmpReqDTO empReqDTO);
}
