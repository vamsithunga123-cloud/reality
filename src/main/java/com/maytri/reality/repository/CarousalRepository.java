package com.maytri.reality.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maytri.reality.model.Carousal;

@Repository
public interface CarousalRepository extends JpaRepository<Carousal,Long>{

}
