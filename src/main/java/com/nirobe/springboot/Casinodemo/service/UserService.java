package com.nirobe.springboot.Casinodemo.service;

import java.util.List;

import com.nirobe.springboot.Casinodemo.exception.ResourceNotFoundException;
import com.nirobe.springboot.Casinodemo.model.User;

public interface UserService {

	public List<User> findAll();
	
	public User findById(long id) throws ResourceNotFoundException;
	
	public void save();

	void updateUserCredit(User user, long betAmount);
}
