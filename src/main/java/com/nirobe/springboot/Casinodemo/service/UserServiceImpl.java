package com.nirobe.springboot.Casinodemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nirobe.springboot.Casinodemo.exception.ResourceNotFoundException;
import com.nirobe.springboot.Casinodemo.model.User;
import com.nirobe.springboot.Casinodemo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<User> findAll() {

		return userRepository.findAll();
	}

	@Override
	public User findById(long id) throws ResourceNotFoundException {
		User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id de usuario no valido"));       
		return user;
	}

	@Override
	public void save() {
		User user = new User();
		
        userRepository.save(user);
	}

	@Override
	public void updateUserCredit(User user, long betAmount) {
        user.setCredit(user.getCredit()-betAmount);
        userRepository.save(user);
	}

}
