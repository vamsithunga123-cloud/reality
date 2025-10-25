package com.maytri.reality.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maytri.reality.model.Enquiry;
@Repository
public interface EnquiryRepository extends JpaRepository<Enquiry,Long> {

}
