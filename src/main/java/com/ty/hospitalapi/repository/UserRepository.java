package com.ty.hospitalapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ty.hospitalapi.dto.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	@Query ("select u from User u where u.email=:myemail and u.password=:mypassword")
	public User getData(@Param("myemail")String email,@Param("mypassword")String password);
	
}
