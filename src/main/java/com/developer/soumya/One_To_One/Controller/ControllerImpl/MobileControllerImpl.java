package com.developer.soumya.One_To_One.Controller.ControllerImpl;

import com.developer.soumya.One_To_One.Controller.IController.MobileController;
import com.developer.soumya.One_To_One.domain.Mobile;
import com.developer.soumya.One_To_One.service.IService.MobileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
public class MobileControllerImpl implements MobileController {

    private final MobileService mobileService;

    @Override
    public ResponseEntity<?> saveMobile(Mobile mobile) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(mobileService.saveMobile(mobile));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> updateMobile(Long mobileId, Mobile mobile) {
        try {
            return ResponseEntity.ok(mobileService.updateMobile(mobileId, mobile));
        } catch (ResponseStatusException exception) {
            return ResponseEntity.status(exception.getStatusCode()).body(exception.getReason());
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }



    @Override
    public ResponseEntity<?> getAllMobiles() {
        try {
            var mobile = mobileService.fetchAllMobiles();
            return mobile.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(mobile);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> getMobileById(Long mobileId) {
        try {
            var mobile = mobileService.findMobileById(mobileId);
            return ResponseEntity.ok(mobile);
        } catch (ResponseStatusException exception) {
            return ResponseEntity.status(exception.getStatusCode()).body(exception.getReason());
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }

    @Override
    public ResponseEntity<Object> deleteBeforeAssignEmployee(Long mobileId) {
       try {
           return ResponseEntity.status(HttpStatus.OK).body(mobileService.deleteBeforeAssignEmployee(mobileId));
       }catch(ResponseStatusException exception){
           return ResponseEntity.status(exception.getStatusCode()).body(exception.getReason());
       }
       catch (Exception exception){
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
       }
    }


    @Override
    public ResponseEntity<?> deleteMobileByIdAfterAssignInEmployee(Long empId , Long mobileId) {
        try {
            var mobile = mobileService.deleteMobileByIdAfterAssignInEmployee(empId,mobileId);
            return ResponseEntity.ok(mobile);
        } catch (ResponseStatusException exception) {
            return ResponseEntity.status(exception.getStatusCode()).body(exception.getReason());
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }


}
