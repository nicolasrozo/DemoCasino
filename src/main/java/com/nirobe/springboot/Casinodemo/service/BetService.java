package com.nirobe.springboot.Casinodemo.service;

import java.util.List;
import java.util.Optional;

import com.nirobe.springboot.Casinodemo.model.Bet;
import com.nirobe.springboot.Casinodemo.model.Game;


public interface BetService {

	public List<Bet> findAll();
	
	public Optional<Bet> findById(long id);

	public boolean isValidBetAmount(long betAmount);

	public boolean isTypeBetAccepted(String typeBet);

	public Bet save(long idActiveGame, long id, String typeBet, String typeBet2, long betAmount);

	void save(Bet bet);

	public List<Bet> close(Game game);
	
	
}
