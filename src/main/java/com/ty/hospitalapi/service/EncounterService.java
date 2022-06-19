package com.ty.hospitalapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ty.hospitalapi.dao.EncounterDao;
import com.ty.hospitalapi.dto.Encounter;
import com.ty.hospitalapi.dto.ResponseStracture;
import com.ty.hospitalapi.exception.NoIDFoundException;

@Service
public class EncounterService {
	@Autowired
	EncounterDao encounterDao;
	
	public ResponseStracture<Encounter> saveEncounter(int person_id,int branch_id,Encounter encounter){
		ResponseStracture<Encounter> responseStracture = new ResponseStracture<Encounter>(); 
		responseStracture.setStatusCode(HttpStatus.ACCEPTED.value());
		responseStracture.setMessage("Encounter Data Saved Successfully");
		responseStracture.setData(encounterDao.saveEncounter(person_id, branch_id, encounter));
		return responseStracture;
	}
	public ResponseStracture<List<Encounter>> getAllEncounter(){
		ResponseStracture<List<Encounter>> responseStracture = new ResponseStracture<List<Encounter>>();
		List<Encounter> encounters = encounterDao.getAllEncounter();
		if(encounters != null) {
			responseStracture.setStatusCode(HttpStatus.FOUND.value());
			responseStracture.setMessage("All Encounter Details");
			responseStracture.setData(encounters);
		}else {
			responseStracture.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStracture.setMessage("Encounter Data is not found");
			responseStracture.setData(null);
		}
		return responseStracture;
	}
	public ResponseStracture<Encounter> getEncounterById(int id){
		ResponseStracture<Encounter> responseStracture = new ResponseStracture<Encounter>();
		Encounter encounter = encounterDao.getEncounterById(id);
		if(encounter != null) {
			responseStracture.setStatusCode(HttpStatus.FOUND.value());
			responseStracture.setMessage("Encounter Details By Id");
			responseStracture.setData(encounter);
		}else {
			throw new NoIDFoundException("Encounter Id "+id+" does not exist");
		}
		return responseStracture;
	}
	public ResponseStracture<String> deleteEncounter(int id){
		ResponseStracture<String> responseStracture = new ResponseStracture<String>();
		Encounter encounter = encounterDao.getEncounterById(id);
		if(encounter != null) {
			responseStracture.setStatusCode(HttpStatus.FOUND.value());
			responseStracture.setMessage("Encounter Details By Id");
			responseStracture.setData(encounterDao.deleteEncounter(id));
		}else {
			throw new NoIDFoundException("Encounter Id "+id+" does not exist");
		}
		return responseStracture;
	}
	public ResponseStracture<Encounter> updateEncounter(int person_id,int branch_id,Encounter encounter){
		ResponseStracture<Encounter> responseStracture = new ResponseStracture<Encounter>();
		Encounter encounters = encounterDao.getEncounterById(encounter.getId());
		if(encounters != null) {
			responseStracture.setStatusCode(HttpStatus.FOUND.value());
			responseStracture.setMessage("Encounter Details By Id");
			responseStracture.setData(encounterDao.updateEncounter(person_id, branch_id, encounters));
		}else {
			throw new NoIDFoundException("Encounter Id "+encounter.getId()+" does not exist");
		}
		return responseStracture;
	}
}
