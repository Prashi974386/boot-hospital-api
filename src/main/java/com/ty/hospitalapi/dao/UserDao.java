package com.ty.hospitalapi.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.hospitalapi.dto.User;
import com.ty.hospitalapi.repository.UserRepository;

@Repository
public class UserDao {
	@Autowired
	UserRepository userRepository;
	
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	public List<User> getAllUser(){
		return userRepository.findAll();
	}
	
	public User getUserById(int id) {
		Optional<User> optional = userRepository.findById(id);
		if(optional.isEmpty()) {
			return null;
		}else {
			return optional.get();
		}
	}
	public String deleteUser(int id) {
		Optional<User> optional = userRepository.findById(id);
		if(optional.isEmpty()) {
			return "No Data is Present";
		}else {
			 userRepository.delete(optional.get());
			 return "Deleted Successfully";
		}
	}
	public User updateUser(User user) {
		Optional<User> optional = userRepository.findById(user.getId());
		if(optional != null) {
			return userRepository.save(user);
		}else {
			return null;
		}
	}
	public User getData(String email,String password) {
		return userRepository.getData(email, password);
	}
}
