package com.maytri.reality.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maytri.reality.model.Enquiry;
@Repository
public interface EnquiryRepository extends JpaRepository<Enquiry,Long> {
    List<Enquiry> findByCreatedDateBetween(LocalDate startDate, LocalDate endDate);
}
