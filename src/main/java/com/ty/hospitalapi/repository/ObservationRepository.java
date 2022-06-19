package com.ty.hospitalapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.hospitalapi.dto.Observation;

public interface ObservationRepository extends JpaRepository<Observation, Integer>{

}
