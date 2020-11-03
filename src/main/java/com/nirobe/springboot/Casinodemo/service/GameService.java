package com.nirobe.springboot.Casinodemo.service;

import java.util.List;
import java.util.Optional;

import com.nirobe.springboot.Casinodemo.model.Game;


public interface GameService {

	public List<Game> findAll();
	
	public Optional<Game> findById(long id);

	void save(long rouletteId);
		
}
