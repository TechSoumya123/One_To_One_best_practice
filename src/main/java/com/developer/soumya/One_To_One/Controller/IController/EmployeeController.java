package com.developer.soumya.One_To_One.Controller.IController;

import com.developer.soumya.One_To_One.domain.Employee;
import com.developer.soumya.One_To_One.dto.requestDTO.EmpReqDTO;
import com.developer.soumya.One_To_One.dto.requestDTO.EmployeeRequestDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RequestMapping(path = {"/api/employee"})
public interface EmployeeController {

    //    NOTE - 1
    @PostMapping(path = {"/create/{id}"})
    ResponseEntity<?> createNewEmployee(@PathVariable("id") @Min(value = 1, message = "Mobile id must be grater than 0") final Long mobileId, @RequestBody @Valid final Employee employee);

    @GetMapping(path = {"/getAll"})
    ResponseEntity<?> getAllEmployees();

    @GetMapping(path = {"/getBY/{id}"})
    ResponseEntity<?> getEmployeeById(@PathVariable("id") @Min(value = 1, message = "Employee id must be grater than 0") final Long empId);

    @PutMapping(path = {"/update/{id}"})
    ResponseEntity<?> updateEmployee(@PathVariable("id") @Min(value = 1, message = "Employee id must be grater than 0") final Long empId, @RequestBody final Employee employee);

    @DeleteMapping(path = {"/delete/{id}"})
    ResponseEntity<?> deleteEmployee(@PathVariable("id") @Min(value = 1, message = "Employee id must be grater than 0") final Long empId);

    @PostMapping(path = {"/employee/{empId}/mobile/{mobileId}"})
    ResponseEntity<?> addMobileToEmployee(@PathVariable("empId") @Min(value = 1, message = "Employee id must be grater than 0") final Long empId, @PathVariable("mobileId") @Min(value = 1, message = "Mobile id must be grater than 0") final Long mobileId);

    @PostMapping(path = {"/removeMobileFromEmp/{empId}"})
    ResponseEntity<?> removeMobileFromEmployee(@PathVariable("empId") @Min(value = 1, message = "Employee id must be grater than 0") final Long empId);

    // NOTE - 2 USING CASCADE OPERATION

    @PostMapping(path = {"/save/emp-mobile"})
    ResponseEntity<Object> createEmployeeWithMobile(@Valid @RequestBody final EmployeeRequestDTO employeeRequestDTO);

    @PostMapping(path = {"/create-emp-mobile"})
    ResponseEntity<Object> createEmployee(@RequestBody final EmployeeRequestDTO employeeRequestDTO);

    @PostMapping(path = {"/emp/save"})
    ResponseEntity<Object> saveEmployee(@RequestBody final Employee employee);

    @PostMapping(path = {"/check/save"})
    ResponseEntity<Object> saveNewEmployee(@RequestBody final EmpReqDTO empReqDTO);

    @DeleteMapping("/emp/{id}")
    Object deleteMobile(@PathVariable("id")Long empId);
}
