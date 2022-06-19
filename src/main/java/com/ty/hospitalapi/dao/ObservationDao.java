package com.ty.hospitalapi.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.hospitalapi.dto.Encounter;
import com.ty.hospitalapi.dto.Observation;
import com.ty.hospitalapi.repository.ObservationRepository;

@Repository
public class ObservationDao {
	@Autowired
	ObservationRepository observationRepository;
	@Autowired
	EncounterDao encounterDao;
	
	public Observation saveObservation(int encounter_id,Observation observation) {
		Encounter encounter = encounterDao.getEncounterById(encounter_id);
		if(encounter != null) {
			observation.setEncounter(encounter);
		}
		return observationRepository.save(observation);
	}
	public List<Observation> geAllObservation(){
		return observationRepository.findAll();
	}
	public Observation getObservationById(int id) {
		Optional<Observation> optional = observationRepository.findById(id);
		if(optional.isEmpty()) {
			return null;
		}else {
			return optional.get();
		}
	}
	public String deleteObservation(int id) {
		Optional<Observation> optional = observationRepository.findById(id);
		if(optional.isEmpty()) {
			return "No data is exist";
		}else {
			observationRepository.delete(optional.get());
			return "Observation data deleted By Id";
		}
	}
	public Observation updateObservation(int encounter_id,Observation observation) {
		Encounter encounter = encounterDao.getEncounterById(encounter_id);
		if(encounter != null) {
			observation.setEncounter(encounter);
		}
		return observationRepository.save(observation);
	}
}
