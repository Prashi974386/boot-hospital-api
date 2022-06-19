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

import com.ty.hospitalapi.dto.Encounter;
import com.ty.hospitalapi.dto.ResponseStracture;
import com.ty.hospitalapi.service.EncounterService;

@RestController
public class EncounterController {
	@Autowired
	EncounterService encounterService;
	
	@PostMapping("/encounters")
	public ResponseStracture<Encounter> saveEncounter(@PathVariable int pid,@PathVariable int bid,@RequestBody Encounter encounter){
		return encounterService.saveEncounter(pid,bid,encounter);
	}
	@GetMapping("/encounters")
	public ResponseStracture<List<Encounter>> getAllEncounter(){
		return encounterService.getAllEncounter();
	}
	@GetMapping("/encounters/{id}")
	public ResponseStracture<Encounter> getEncounterById(@PathVariable int id){
		return encounterService.getEncounterById(id);
	}
	@PutMapping("/encounters/{pid}/{bid}")
	public ResponseStracture<Encounter> updateEncounter(@PathVariable int pid,@PathVariable int bid,@RequestBody Encounter encounter){
		return encounterService.updateEncounter(pid, bid, encounter);
	}
	@DeleteMapping("/encounters/{id}")
	public ResponseStracture<String> deleteEncounter(@PathVariable int id){
		return encounterService.deleteEncounter(id);
	}
}
