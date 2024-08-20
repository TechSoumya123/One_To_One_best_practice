package com.developer.soumya.One_To_One.service.ServiceImpl;

import com.developer.soumya.One_To_One.domain.Employee;
import com.developer.soumya.One_To_One.domain.Mobile;
import com.developer.soumya.One_To_One.dto.requestDTO.EmpReqDTO;
import com.developer.soumya.One_To_One.dto.requestDTO.EmployeeRequestDTO;
import com.developer.soumya.One_To_One.dto.responseDTO.EmployeeResponseDTO;
import com.developer.soumya.One_To_One.repository.EmployeeRepository;
import com.developer.soumya.One_To_One.service.IService.EmployeeService;
import com.developer.soumya.One_To_One.service.IService.MobileService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static com.developer.soumya.One_To_One.Util.EmployeeMapper.*;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;


@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final MobileService mobileService;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, @Lazy MobileService mobileService) {
        this.employeeRepository = employeeRepository;
        this.mobileService = mobileService;
    }

    @Transactional
    @Override
    public Employee saveNewEmployee(Long mobileId, Employee employee) {
        Optional<Mobile> mobile = mobileService.getMobileOrNull(mobileId);
        if (mobile.isEmpty() && isNull(employee.getMobile())) {
            return employeeRepository.save(employee);
        } else if (mobile.isPresent() || nonNull(employee.getMobile())) {
            mobile.ifPresent(employee::setMobile);
            employeeRepository.save(employee);
        }
        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long empId) {
        return getEmployee(empId).get();
    }

    @Override
    public Optional<Employee> getEmployee(Long empId) {
        return Optional.of(employeeRepository.findById(empId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sorry, Employee not found")));
    }

    @Transactional
    @Override
    public Employee updatEmployee(Long empId, Employee employee) {
        return getEmployee(empId).map(existingEmp -> {
            return existingEmp.setName(employee.getName())
                    .setAddress(employee.getAddress());
        }).get();
    }

    @Override
    public Employee deleteEmployee(Long empId) {
        return getEmployee(empId).map(emp -> {
            Optional.of(emp).ifPresent(employeeRepository::delete);
            return emp;
        }).get();
    }

    @Transactional
    @Override
    public Employee addMobileToEmployee(Long empId, Long mobileId) {
        return employeeRepository.findById(empId).map(employee -> {
            if (!isNull(employee.getMobile())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Employee already assigned in a mobile");
            }
            mobileService.getMobileOrNull(mobileId)
                    .ifPresent(employee::setMobile);
            return employee;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
    }

    @Transactional
    @Override
    public Employee removeMobileFromEmployee(Long empId) {
        return employeeRepository.findById(empId).map(employee -> {
            if (isNull(employee.getMobile())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Employee does not contain any mobile.");
            }
            employee.setMobile(null);
            return employee;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
    }

    // OPTIMIZE -2

    @Override
    public EmployeeResponseDTO saveEmployeeWithMobile(EmployeeRequestDTO employeeRequestDTO) {
        var employee = empRequestDTO_To_Employee(employeeRequestDTO);
        Optional.of(employee).ifPresent(employeeRepository::save);
        return employee_To_EmployeeResponseDTO(employee);

    }


    @Override
    public Employee saveEmployee(Employee employee) {
        var mobile = mobileService.getMobileOrNull(employee.getMobile().getId());
        if (isNull(mobile)) {
            return employeeRepository.save(employee);
        } else mobile.ifPresent(employee::setMobile);
        return employeeRepository.save(employee);
    }

    @Override
    public Object saveEmpAnotherWay(EmpReqDTO empReqDTO) {
        var emp = empReqDTO_To_Emp(empReqDTO);
        if (isNull(empReqDTO.mobileId())) {
            employeeRepository.save(emp);
        }
        var mobile = mobileService.getMobileOrNull(empReqDTO.mobileId());
        mobile.ifPresent(emp::setMobile);
        employeeRepository.save(emp);
        return emp;
    }

}
