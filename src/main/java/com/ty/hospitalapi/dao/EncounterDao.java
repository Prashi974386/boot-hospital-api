package com.ty.hospitalapi.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.hospitalapi.dto.Branch;
import com.ty.hospitalapi.dto.Encounter;
import com.ty.hospitalapi.dto.Person;
import com.ty.hospitalapi.repository.BranchRepository;
import com.ty.hospitalapi.repository.EncounterRepository;
import com.ty.hospitalapi.repository.PersonRepository;

@Repository
public class EncounterDao {
	@Autowired
	EncounterRepository encounterRepository;
	@Autowired
	PersonRepository personRepository;
	@Autowired
	BranchRepository branchRepository;
	
	public Encounter saveEncounter(int person_id,int branch_id,Encounter encounter) {
		Optional<Person> optional1 = personRepository.findById(person_id); 
		Optional<Branch> optional2 = branchRepository.findById(branch_id); 
		if(optional1.isPresent() && optional2.isPresent()){
			encounter.setPerson(optional1.get());
			encounter.setBranch(optional2.get());
		}
		return encounterRepository.save(encounter);
	}
	public List<Encounter> getAllEncounter(){
		return encounterRepository.findAll();
	}
	public Encounter getEncounterById(int id) {
		Optional<Encounter> optional = encounterRepository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}else {
			return null;
		}
	}
	public String deleteEncounter(int id) {
		Optional<Encounter> optional = encounterRepository.findById(id);
		if(optional != null) {
			encounterRepository.delete(optional.get());
			return "Encounter Deleted successfully..!";
		}else {
			return "Encounter is Not Found";
		}
	}
	public Encounter updateEncounter(int person_id,int branch_id,Encounter encounter) {
		Optional<Person> optional1 = personRepository.findById(person_id); 
		Optional<Branch> optional2 = branchRepository.findById(branch_id); 
		if(optional1.isPresent() && optional2.isPresent()){
			encounter.setPerson(optional1.get());
			encounter.setBranch(optional2.get());
		}
		return encounterRepository.save(encounter);
	}
}
