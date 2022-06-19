package com.ty.hospitalapi.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.hospitalapi.dto.Person;
import com.ty.hospitalapi.repository.PersonRepository;

@Repository
public class PersonDao {
	@Autowired
	PersonRepository personRepository;

	public Person savePerson(Person person) {
		return personRepository.save(person);
	}

	public List<Person> getAllPerson() {
		return personRepository.findAll();
	}

	public Person getPersonById(int id) {
		Optional<Person> optional = personRepository.findById(id);
		if (optional.isEmpty()) {
			return null;
		} else {
			return optional.get();
		}
	}

	public String deletePerson(int id) {
		Optional<Person> optional = personRepository.findById(id);
		if (optional.isEmpty()) {
			return "Person details is not found";
		} else {
			personRepository.delete(optional.get());
			return "Person Deleted Successfully..!";
		}
	}

	public Person updatePerson(Person person) {
		Optional<Person> optional = personRepository.findById(person.getId());
		if (optional.isEmpty()) {
			return null;
		} else {
			return personRepository.save(optional.get());
		}
	}
}
