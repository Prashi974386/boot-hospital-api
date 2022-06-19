package com.ty.hospitalapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ty.hospitalapi.dao.PersonDao;
import com.ty.hospitalapi.dto.Person;
import com.ty.hospitalapi.dto.ResponseStracture;

@Service
public class PersonService {
	@Autowired
	PersonDao personDao;
	
	public ResponseStracture<Person> savePerson(Person person){
		ResponseStracture<Person> responseStracture = new ResponseStracture<Person>();
		responseStracture.setStatusCode(HttpStatus.CREATED.value());
		responseStracture.setMessage("Person Data is Saved");
		responseStracture.setData(personDao.savePerson(person));
		return responseStracture;
	}
	
	public ResponseStracture<List<Person>> getAllPerson(){
		ResponseStracture<List<Person>> responseStracture = new ResponseStracture<List<Person>>();
		List<Person> persons = personDao.getAllPerson();
		if(persons!=null) {
			responseStracture.setStatusCode(HttpStatus.FOUND.value());
			responseStracture.setMessage("All The Person Details");
			responseStracture.setData(persons);
			return responseStracture;
		}else {
			responseStracture.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStracture.setMessage("Person is Not found");
			responseStracture.setData(null);
			return responseStracture;
		}
	}
	
	public ResponseStracture<Person> getPersonById(int id){
		ResponseStracture<Person> responseStracture = new ResponseStracture<Person>();
		Person person = personDao.getPersonById(id);
		if(person !=null) {
			responseStracture.setStatusCode(HttpStatus.FOUND.value());
			responseStracture.setMessage("Person Details By Id");
			responseStracture.setData(person);
			return responseStracture;
		}else {
			responseStracture.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStracture.setMessage("Person is Not found");
			responseStracture.setData(null);
			return responseStracture;
		}
	}
	
	public ResponseStracture<String> deletePerson(int id){
		ResponseStracture<String> responseStracture = new ResponseStracture<String>();
		Person person = personDao.getPersonById(id);
		if(person !=null) {
			responseStracture.setStatusCode(HttpStatus.FOUND.value());
			responseStracture.setMessage("Person Data Deleted By Id");
			responseStracture.setData(personDao.deletePerson(id));
			return responseStracture;
		}else {
			responseStracture.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStracture.setMessage("Person is Not found");
			responseStracture.setData(null);
			return responseStracture;
		}
	}
	public ResponseStracture<Person> updatePerson(Person person){
		ResponseStracture<Person> responseStracture = new ResponseStracture<Person>();
		responseStracture.setStatusCode(HttpStatus.OK.value());
		responseStracture.setMessage("Person Data is Updated");
		responseStracture.setData(personDao.updatePerson(person));
		return responseStracture;
	}
}
