package com.developer.soumya.One_To_One.service.IService;

import com.developer.soumya.One_To_One.domain.Mobile;

import java.util.List;
import java.util.Optional;

public interface MobileService {

    Mobile saveMobile(Mobile mobile);

    Mobile updateMobile(Long mobileId, Mobile mobile);

    List<Mobile> fetchAllMobiles();

    Mobile findMobileById(Long mobileId);

    Optional<Mobile> getMobileOrNull(Long mobileId);

    Mobile deleteMobileByIdAfterAssignInEmployee(Long empId,Long  mobileId);

    Mobile deleteBeforeAssignEmployee(Long mobileId);
}
