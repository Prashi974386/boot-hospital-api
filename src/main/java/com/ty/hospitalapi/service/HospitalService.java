package com.ty.hospitalapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ty.hospitalapi.dao.HospitalDao;
import com.ty.hospitalapi.dto.Branch;
import com.ty.hospitalapi.dto.Hospital;
import com.ty.hospitalapi.dto.ResponseStracture;
import com.ty.hospitalapi.exception.NoIDFoundException;

@Service
public class HospitalService {
	@Autowired
	HospitalDao hospitalDao;
	
	public ResponseStracture<Hospital> saveHospital(Hospital hospital){
		List<Branch> branchs = hospital.getBranch();
		for(Branch branch : branchs) {
			branch.setHospital(hospital);
		}
		ResponseStracture<Hospital> responseStracture = new ResponseStracture<Hospital>();
		responseStracture.setStatusCode(HttpStatus.CREATED.value());
		responseStracture.setMessage("Hospital saved Successfully..!");
		responseStracture.setData(hospitalDao.saveHospital(hospital));	
		return responseStracture;
	}
	public ResponseStracture<List<Hospital>> getAllHospital(){
		ResponseStracture<List<Hospital>> responseStracture = new ResponseStracture<List<Hospital>>();
		List<Hospital> hospitals = hospitalDao.getAllHospital();
		if(hospitals != null) {
			responseStracture.setStatusCode(HttpStatus.FOUND.value());
			responseStracture.setMessage("All Hospital Details");
			responseStracture.setData(hospitals);
		}else {
			responseStracture.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStracture.setMessage("Hospital Data is Not Found");
			responseStracture.setData(null);
		}
		return responseStracture;
	}
	public ResponseStracture<Hospital> getHospitalById(int id){
		ResponseStracture<Hospital> responseStracture = new ResponseStracture<Hospital>();
		Hospital hospital = hospitalDao.getHospitalById(id);
		if(hospital != null) {
			responseStracture.setStatusCode(HttpStatus.FOUND.value());
			responseStracture.setMessage("Hospital Details By Id");
			responseStracture.setData(hospital);
		}else {
			throw new NoIDFoundException("Hospital Id "+id+" does not exist"); 
		}
		return responseStracture;
	}
	public ResponseStracture<String> deleteHospital(int id){
		ResponseStracture<String> responseStracture = new ResponseStracture<String>();
		Hospital hospital = hospitalDao.getHospitalById(id);
		if(hospital != null) {
			responseStracture.setStatusCode(HttpStatus.FOUND.value());
			responseStracture.setMessage("Hospital Deleted By Given Id");
			responseStracture.setData(hospitalDao.deleteHospital(id));
		}else {
			responseStracture.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStracture.setMessage("Hospital Data is Not Deleted");
			responseStracture.setData(null);
		}
		return responseStracture;
	}
	
	public ResponseStracture<Hospital> updateHospital(Hospital hospital){
		ResponseStracture<Hospital> responseStracture = new ResponseStracture<Hospital>();
		List<Branch> branchs = hospital.getBranch();
		for(Branch branch : branchs) {
			branch.setHospital(hospital);
		}
		Hospital hospitals = hospitalDao.getHospitalById(hospital.getId());
		if(hospitals != null) {
			responseStracture.setStatusCode(HttpStatus.OK.value());
			responseStracture.setMessage("Hospital Data is Updated");
			responseStracture.setData(hospitalDao.updateHospital(hospital ));
		}else {
			throw new NoIDFoundException("Hospital Id "+hospital.getId()+" does not exist"); 
		}
		return responseStracture;
	}
	public ResponseStracture<List<Branch>> getBranchesByHospitalId(int hId){
		ResponseStracture<List<Branch>> responseStracture = new ResponseStracture<List<Branch>>();
		Hospital hospitals = hospitalDao.getHospitalById(hId);
		if(hospitals != null) {
			responseStracture.setStatusCode(HttpStatus.OK.value());
			responseStracture.setMessage("Hospital Data is Updated");
			responseStracture.setData(hospitalDao.getBranchesByHospitalId(hId));
		}else {
			throw new NoIDFoundException("Hospital Id "+hId+" does not exist"); 
		}
		return responseStracture;
	}
}
