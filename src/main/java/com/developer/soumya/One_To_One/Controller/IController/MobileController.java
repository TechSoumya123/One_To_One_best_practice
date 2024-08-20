package com.developer.soumya.One_To_One.Controller.IController;

import com.developer.soumya.One_To_One.domain.Mobile;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@CrossOrigin(origins = {"*"})
@RequestMapping(path = {"/api/mobile"})
public interface MobileController<T> {

    @PostMapping(path = {"/create"})
    ResponseEntity<?> saveMobile(@RequestBody @Valid final Mobile mobile);

    @GetMapping(path = {"/getAll"})
    ResponseEntity<?> getAllMobiles();

    @GetMapping(path = {"/get/{id}"})
    ResponseEntity<?> getMobileById(@PathVariable("id") @Min(value = 1, message = "Mobile id should be grater than 0") final Long mobileId);

    @PutMapping(path = {"/update/{id}"})
    ResponseEntity<?> updateMobile(@PathVariable("id") @Min(value = 1, message = "Mobile id should be grater than 0") final Long mobileId, @RequestBody @Valid Mobile mobile);

    @DeleteMapping(path = {"/delete/{id}"})
    ResponseEntity<Object> deleteBeforeAssignEmployee(@PathVariable("id") @Min(value = 1, message = "Mobile id should be grater than 0") final Long mobileId);

    @DeleteMapping(path = {"/emp/{eId}/mobile/{mId}"})
    ResponseEntity<T> deleteMobileByIdAfterAssignInEmployee(@PathVariable("eId") @Min(value = 1, message = "Employee id should be grater than 0") final Long empId, @PathVariable("mId") @Min(value = 1, message = "Employee id should be grater than 0") final Long mobileId);
}
