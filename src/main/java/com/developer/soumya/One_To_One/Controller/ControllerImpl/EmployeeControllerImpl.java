package com.developer.soumya.One_To_One.Controller.ControllerImpl;

import com.developer.soumya.One_To_One.Controller.IController.EmployeeController;
import com.developer.soumya.One_To_One.domain.Employee;
import com.developer.soumya.One_To_One.dto.requestDTO.EmpReqDTO;
import com.developer.soumya.One_To_One.dto.requestDTO.EmployeeRequestDTO;
import com.developer.soumya.One_To_One.repository.EmployeeRepository;
import com.developer.soumya.One_To_One.service.IService.EmployeeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class EmployeeControllerImpl implements EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeRepository employeeRepository;

    @Override
    public ResponseEntity<?> createNewEmployee(Long empId, Employee employee) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.saveNewEmployee(empId, employee));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> getAllEmployees() {
        try {
            var allEmployees = employeeService.getAllEmployees();
            return Optional.of(allEmployees).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> getEmployeeById(Long empId) {
        try {
            return ResponseEntity.ok(employeeService.getEmployeeById(empId));
        } catch (ResponseStatusException exception) {
            return ResponseEntity.status(exception.getStatusCode()).body(exception.getReason());
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> updateEmployee(Long empId, Employee employee) {
        try {
            return ResponseEntity.ok(employeeService.updatEmployee(empId, employee));
        } catch (ResponseStatusException exception) {
            return ResponseEntity.status(exception.getStatusCode()).body(exception.getReason());
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> deleteEmployee(Long empId) {
        try {
            return ResponseEntity.ok(employeeService.deleteEmployee(empId));
        } catch (ResponseStatusException exception) {
            return ResponseEntity.status(exception.getStatusCode()).body(exception.getReason());
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> addMobileToEmployee(Long empId, Long mobileId) {
        try {
            return ResponseEntity.ok(employeeService.addMobileToEmployee(empId, mobileId));
        } catch (ResponseStatusException exception) {
            return ResponseEntity.status(exception.getStatusCode()).body(exception.getReason());
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> removeMobileFromEmployee(Long empId) {
        try {
            return ResponseEntity.ok(employeeService.removeMobileFromEmployee(empId));
        } catch (ResponseStatusException exception) {
            return ResponseEntity.status(exception.getStatusCode()).body(exception.getReason());
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }

    // OPTIMIZE - 2

    @Override
    public ResponseEntity<Object> createEmployeeWithMobile(EmployeeRequestDTO employeeRequestDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.saveEmployeeWithMobile(employeeRequestDTO));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }

    @Override
    public ResponseEntity<Object> createEmployee(EmployeeRequestDTO employeeRequestDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.saveEmployeeWithMobile(employeeRequestDTO));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }

    @Override
    public ResponseEntity<Object> saveEmployee(Employee employee) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.saveEmployee(employee));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }

    @Override
    public ResponseEntity<Object> saveNewEmployee(EmpReqDTO empReqDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.saveEmpAnotherWay(empReqDTO));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }

    @Transactional
    @Override
    public Object deleteMobile(Long empId) {
        return employeeRepository.findById(empId).map(employee -> {
            employeeRepository.deleteByMobileId(
                    employee.getMobile().getId()
            );
            return employee;
        }).orElseThrow();
    }
}
