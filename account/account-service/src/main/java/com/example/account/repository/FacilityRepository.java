package com.example.account.repository;

import com.example.account.domain.Facility;
import com.example.account.domain.FacilityInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FacilityRepository extends JpaRepository<Facility, Integer> {
    @Query("select facility from Facility facility " +
            "join Person person on person.facility = facility.id " +
            "where person.nationalCode = :nationalCode")
    List<FacilityInfo> getFacilitiesByNationalCode(String nationalCode);
}
