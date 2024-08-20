package com.developer.soumya.One_To_One.repository;

import com.developer.soumya.One_To_One.domain.Mobile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MobileRepository extends JpaRepository<Mobile, Long> {
    @Modifying
    @Query("delete from Mobile r where r.id = :mobileId")
    void deleteMobileId(@Param("mobileId") Long mobileId);

//    void deleteMobileById(Long mobileId);
}