package com.nirobe.springboot.Casinodemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nirobe.springboot.Casinodemo.business.GameLogic;
import com.nirobe.springboot.Casinodemo.exception.ResourceNotFoundException;
import com.nirobe.springboot.Casinodemo.model.Game;
import com.nirobe.springboot.Casinodemo.model.Roulette;
import com.nirobe.springboot.Casinodemo.model.User;
import com.nirobe.springboot.Casinodemo.repository.GameRepository;

@Service
public class GameServiceImpl implements GameService{

    @Autowired
    private GameLogic gameLogic;
    
    @Autowired
    private GameRepository gameRepository;
    
	@Override
	public List<Game> findAll() {
		
		return gameRepository.findAll();
	}

	@Override
	public Optional<Game> findById(long id) {
		
		return gameRepository.findById(id);
	}

	@Override
	public void save(long rouletteId) {
        Game game = new Game(rouletteId);
        gameRepository.save(game);
	}

	public Game close(Roulette roulette) throws ResourceNotFoundException {
		
		Game game = gameRepository.findById(roulette.getIdActiveGame())
                .orElseThrow(() -> new ResourceNotFoundException("Id de usuario no valido"));       
		int gameNumber = gameLogic.generateRouletteNumber();
		game.setNumberResult(gameNumber);	
		game.setColorResult(gameLogic.getColorNumber(gameNumber));
		gameRepository.save(game);
		
		return game;	
	}

}
