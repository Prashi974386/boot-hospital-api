package com.ty.hospitalapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ty.hospitalapi.dto.Observation;
import com.ty.hospitalapi.dto.ResponseStracture;
import com.ty.hospitalapi.service.ObservationService;

@RestController
public class ObservationController {
	@Autowired
	ObservationService observationService;
	
	@PostMapping("/observations/{id}")
	public ResponseStracture<Observation> saveObservation(@PathVariable int id, @RequestBody Observation observation){
		return observationService.saveObservation(id,observation);
	}
	@GetMapping("/observations")
	public ResponseStracture<List<Observation>> getAllObservation(){
		return observationService.getAllObservation();
	}
	@GetMapping("/observations/{id}")
	public ResponseStracture<Observation> getObservationById(@PathVariable int id){
		return observationService.getObsevationById(id);
	}
	@PutMapping("/observations/{id}")
	public ResponseStracture<Observation> updateObservation(@PathVariable int id,@RequestBody Observation observation){
		return observationService.updateObservation(id,observation);
	}
	@DeleteMapping("/observations/{id}")
	public ResponseStracture<String> deleteObservation(@PathVariable int id){
		return observationService.deleteObservation(id);
	}
}
