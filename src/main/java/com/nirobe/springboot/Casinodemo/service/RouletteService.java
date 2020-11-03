package com.nirobe.springboot.Casinodemo.service;

import java.util.List;

import com.nirobe.springboot.Casinodemo.exception.ResourceNotFoundException;
import com.nirobe.springboot.Casinodemo.model.Roulette;

public interface RouletteService {

	public List<Roulette> findAll();
	
	public Roulette findById(long id) throws ResourceNotFoundException ;
	
	public long save();

	public void close(Roulette roulette);
		
}
