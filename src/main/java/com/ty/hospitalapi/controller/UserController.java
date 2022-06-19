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

import com.ty.hospitalapi.dto.Login;
import com.ty.hospitalapi.dto.ResponseStracture;
import com.ty.hospitalapi.dto.User;
import com.ty.hospitalapi.service.UserService;

@RestController
public class UserController {
	@Autowired
	UserService userService;

	@PostMapping("/users")
	public ResponseStracture<User> saveUser(@RequestBody User user) {
		return userService.saveUser(user);
	}
	@GetMapping("/users")
	public ResponseStracture<List<User>> getAllUser(){
		return userService.getAllUser();
	}
	@GetMapping("/users/{id}")
	public ResponseStracture<User> getUserById(@PathVariable int id){
		return userService.getUserById(id);
	}
	@DeleteMapping("/users/{id}")
	public ResponseStracture<String> deleteUser(@PathVariable int id){
		return userService.deleteUser(id);
	}
	@PutMapping("/users")
	public ResponseStracture<User> udateUser(@RequestBody User user){
		return userService.updateUser(user);
	}
	@PostMapping("/users/login")
	public ResponseStracture<User> getemailpassword(@RequestBody Login login){
		return userService.getemailpassword(login.getEmail(), login.getPassword());
	}
}
