package com.ty.hospitalapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ty.hospitalapi.dao.UserDao;
import com.ty.hospitalapi.dto.ResponseStracture;
import com.ty.hospitalapi.dto.User;
import com.ty.hospitalapi.exception.InvalidUserException;
import com.ty.hospitalapi.exception.NoIDFoundException;

@Service
public class UserService {
	@Autowired
	UserDao userDao;

	public ResponseStracture<User> saveUser(User user) {
		ResponseStracture<User> responseStracture = new ResponseStracture<User>();
		responseStracture.setStatusCode(HttpStatus.CREATED.value());
		responseStracture.setMessage("User is Saved Successfully..!");
		responseStracture.setData(userDao.saveUser(user));
		return responseStracture;
	}

	public ResponseStracture<List<User>> getAllUser() {
		ResponseStracture<List<User>> responseStracture = new ResponseStracture<List<User>>();
		List<User> user = userDao.getAllUser();
		if (user != null) {
			responseStracture.setStatusCode(HttpStatus.FOUND.value());
			responseStracture.setMessage("All the User Details");
			responseStracture.setData(user);
			return responseStracture;
		} else {
			responseStracture.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStracture.setMessage("User Not Found");
			responseStracture.setData(null);
			return responseStracture;
		}
	}

	public ResponseStracture<User> getUserById(int id) {
		ResponseStracture<User> responseStracture = new ResponseStracture<User>();
		User user = userDao.getUserById(id);
		if (user != null) {
			responseStracture.setStatusCode(HttpStatus.FOUND.value());
			responseStracture.setMessage("User Details By Id");
			responseStracture.setData(user);
			return responseStracture;
		} else {
//			responseStracture.setStatusCode(HttpStatus.NOT_FOUND.value());
//			responseStracture.setMessage("User Not Found");
//			responseStracture.setData(null);
//			return responseStracture;
			throw new NoIDFoundException("User Id " + id + " does not exist");
		}
	}

	public ResponseStracture<String> deleteUser(int id) {
		ResponseStracture<String> responseStracture = new ResponseStracture<String>();
		User user = userDao.getUserById(id);
		if (user != null) {
			responseStracture.setStatusCode(HttpStatus.FOUND.value());
			responseStracture.setMessage("User Deleted By Id");
			responseStracture.setData(userDao.deleteUser(id));
			return responseStracture;
		} else {
			responseStracture.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStracture.setMessage("User Not Found");
			responseStracture.setData(null);
			return responseStracture;
		}
	}

	public ResponseStracture<User> updateUser(User user) {
		ResponseStracture<User> responseStracture = new ResponseStracture<User>();
		responseStracture.setStatusCode(HttpStatus.OK.value());
		responseStracture.setMessage("User is Updated Successfully..!");
		responseStracture.setData(userDao.updateUser(user));
		return responseStracture;
	}

	public ResponseStracture<User> getemailpassword(String email, String password) {
		ResponseStracture<User> responseStracture = new ResponseStracture<User>();
		User user = userDao.getData(email, password);
		if (user != null) {
			responseStracture.setStatusCode(HttpStatus.OK.value());
			responseStracture.setMessage("User is login Successfully..!");
			responseStracture.setData(user);
			return responseStracture;
		}else {
			throw new InvalidUserException("Email and password mismatched");
		}
	}
}
