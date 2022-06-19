package com.ty.hospitalapi.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.hospitalapi.dto.Branch;
import com.ty.hospitalapi.dto.Hospital;
import com.ty.hospitalapi.repository.BranchRepository;
import com.ty.hospitalapi.repository.HospitalRepository;

@Repository
public class HospitalDao {
	@Autowired
	HospitalRepository hospitalRepository;
	@Autowired
	BranchRepository branchRepository;
	
	public Hospital saveHospital(Hospital hospital) {
		return hospitalRepository.save(hospital);
	}
	public List<Hospital> getAllHospital() {
		return hospitalRepository.findAll();
	}
	public Hospital getHospitalById(int id) {
		Optional<Hospital> optional = hospitalRepository.findById(id);
		if(optional.isEmpty()) {
			return null;
		}else {
			return optional.get();
		}
	}
	public String deleteHospital(int id) {
		Optional<Hospital> optional = hospitalRepository.findById(id);
		if(optional.isEmpty()) {
			return "Hospital is not found";
		}else {
			hospitalRepository.delete(optional.get());
			return "Hospital is deleted By Id";
		}
	}
	public Hospital updateHospital(Hospital hospital) {
		Optional<Hospital> optional = hospitalRepository.findById(hospital.getId());
		if(optional.isEmpty()) {
			return null;
		}else {
			return hospitalRepository.save(hospital);
		}
	}
	public List<Branch> getBranchesByHospitalId(int hId){
		Optional<Hospital> optional = hospitalRepository.findById(hId);
		if(optional.isEmpty()) {
			return null;
		}else {
			return branchRepository.findAll();
		}
		
	}
}
