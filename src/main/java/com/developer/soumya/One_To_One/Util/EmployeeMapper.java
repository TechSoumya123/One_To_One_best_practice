package com.developer.soumya.One_To_One.Util;

import com.developer.soumya.One_To_One.domain.Employee;
import com.developer.soumya.One_To_One.domain.Mobile;
import com.developer.soumya.One_To_One.dto.requestDTO.EmpReqDTO;
import com.developer.soumya.One_To_One.dto.requestDTO.EmployeeRequestDTO;
import com.developer.soumya.One_To_One.dto.requestDTO.MobileRequestDTO;
import com.developer.soumya.One_To_One.dto.responseDTO.EmployeeResponseDTO;
import com.developer.soumya.One_To_One.dto.responseDTO.MobileResponseDTO;

public class EmployeeMapper {

    private EmployeeMapper(){}

    public static Employee empRequestDTO_To_Employee(EmployeeRequestDTO employeeRequestDTO) {
        return new Employee().setName(employeeRequestDTO.name())
                .setAddress(employeeRequestDTO.address())
                .setMobile(mobileRequestDTO_To_Mobile(employeeRequestDTO.mobileRequestDTO()));
    }

    public static Mobile mobileRequestDTO_To_Mobile(MobileRequestDTO mobileRequestDTO) {
        return new Mobile().setMobileName(mobileRequestDTO.mobileName())
                .setMobileBrand(mobileRequestDTO.mobileBrand());
    }

    public static EmployeeResponseDTO employee_To_EmployeeResponseDTO(Employee employee) {
        return new EmployeeResponseDTO(employee.getId(), employee.getName(), employee.getAddress(), mobile_To_MobileResponseDTO(employee.getMobile()));
    }

    public static MobileResponseDTO mobile_To_MobileResponseDTO(Mobile mobile) {
        return new MobileResponseDTO(mobile.getId(), mobile.getMobileName(), mobile.getMobileBrand());
    }

    public static Employee empReqDTO_To_Emp(EmpReqDTO empReqDTO){
        return new Employee().setName(empReqDTO.name())
                .setAddress(empReqDTO.address());
    }
}
