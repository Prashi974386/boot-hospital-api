package com.ty.hospitalapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ty.hospitalapi.dto.Branch;
import com.ty.hospitalapi.dto.Hospital;
import com.ty.hospitalapi.dto.ResponseStracture;
import com.ty.hospitalapi.service.HospitalService;

@RestController
public class HospitalController {
	@Autowired
	HospitalService hospitalService;
	
	@PostMapping("/hospitalss")
	public ResponseStracture<Hospital> saveHospital(@RequestBody Hospital hospital){
		return hospitalService.saveHospital(hospital);
	}
	@GetMapping("/hospitals/{id}")
	public ResponseStracture<Hospital> getHospitlaById(@PathVariable int id){
		return hospitalService.getHospitalById(id);
	}
	@GetMapping("/hospitals")
	public ResponseStracture<List<Hospital>> getAllHospital(){
		return hospitalService.getAllHospital();
	}
	@PutMapping("/hospitals")
	public ResponseStracture<Hospital> updateHospital(@RequestBody Hospital hospital){
		return hospitalService.updateHospital(hospital);
	}
	@DeleteMapping("/hospitals/{id}")
	public ResponseStracture<String> deleteHospital(@PathVariable int id){
		return hospitalService.deleteHospital(id);
	}
	@GetMapping("/hospitals/{id}/branches")
	public ResponseStracture<List<Branch>> getBranchByHospitalid(@PathVariable int id){
		return hospitalService.getBranchesByHospitalId(id);
	}
}
