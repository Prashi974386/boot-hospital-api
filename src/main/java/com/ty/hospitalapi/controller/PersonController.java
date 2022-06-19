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

import com.ty.hospitalapi.dto.Person;
import com.ty.hospitalapi.dto.ResponseStracture;
import com.ty.hospitalapi.service.PersonService;

@RestController
public class PersonController {
	@Autowired
	PersonService personService ;
	
	@PostMapping("/persons")
	public ResponseStracture<Person> savePerson(@RequestBody Person person){
		return personService.savePerson(person);
	}
	@GetMapping("/persons")
	public ResponseStracture<List<Person>> getAllPerson(){
		return personService.getAllPerson();
	}
	@GetMapping("/persons/{id}")
	public ResponseStracture<Person> getPersonById(@PathVariable int id){
		return personService.getPersonById(id);
	}
	@PutMapping("/persons")
	public ResponseStracture<Person> updatePerson(@RequestBody Person person){
		return personService.updatePerson(person);
	}
	@DeleteMapping("/persons/{id}")
	public ResponseStracture<String> deletePerson(@PathVariable int id){
		return personService.deletePerson(id);
	}
}
