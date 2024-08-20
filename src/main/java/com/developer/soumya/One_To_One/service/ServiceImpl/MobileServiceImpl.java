package com.developer.soumya.One_To_One.service.ServiceImpl;

import com.developer.soumya.One_To_One.domain.Employee;
import com.developer.soumya.One_To_One.domain.Mobile;
import com.developer.soumya.One_To_One.repository.MobileRepository;
import com.developer.soumya.One_To_One.service.IService.EmployeeService;
import com.developer.soumya.One_To_One.service.IService.MobileService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Slf4j
@Service
public class MobileServiceImpl implements MobileService {

    private final MobileRepository mobileRepository;
    private final EmployeeService employeeService;

    @Autowired
    public MobileServiceImpl(MobileRepository mobileRepository, @Lazy EmployeeService employeeService) {
        this.mobileRepository = mobileRepository;
        this.employeeService = employeeService;
    }

    @Override
    public Mobile saveMobile(Mobile mobile) {
        log.info("\n Request for save - Mobile \n {}", mobile);
        return mobileRepository.save(mobile);
    }

    @Transactional
    @Override
    public Mobile updateMobile(Long mobileId, Mobile mobile) {
        return findMobile(mobileId).map(existingMobile -> {
            log.info("\n Update mobile - Mobile \n {}", mobile);
            existingMobile.setMobileName(mobile.getMobileName())
                    .setMobileBrand(mobile.getMobileBrand());
            return existingMobile;
        }).get();
    }

    @Override
    public List<Mobile> fetchAllMobiles() {
        return mobileRepository.findAll();
    }

    @Override
    public Mobile findMobileById(Long mobileId) {
        return findMobile(mobileId).get();
    }

    @Override
    public Optional<Mobile> getMobileOrNull(Long mobileId) {
        return Optional.ofNullable(mobileRepository.findById(mobileId)
                .orElse(null));
    }

    @Transactional
    @Override
    public Mobile deleteMobileByIdAfterAssignInEmployee(Long empId, Long mobileId) {
        var employee = employeeService.getEmployee(empId).get();
        if (isNull(employee.getMobile())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Employee doesn't contain any mobile");
        }
        employee.setMobile(null);
       return findMobile(mobileId).map(mobile -> {
            mobileRepository.delete(mobile);
            return mobile;
        }).get();
    }

    @Transactional
    @Override
    public Mobile deleteBeforeAssignEmployee(Long mobileId) {
        return findMobile(mobileId).map(mobile -> {
            mobileRepository.deleteMobileId(mobileId);
            return mobile;
        }).get();
    }

    private Optional<Mobile> findMobile(Long mobileId) {
        return Optional.of(mobileRepository.findById(mobileId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Mobile could not be found " + mobileId)));
    }
}
