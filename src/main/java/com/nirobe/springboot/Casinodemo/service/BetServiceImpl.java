package com.nirobe.springboot.Casinodemo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nirobe.springboot.Casinodemo.business.BetLogic;
import com.nirobe.springboot.Casinodemo.model.Bet;
import com.nirobe.springboot.Casinodemo.model.Game;
import com.nirobe.springboot.Casinodemo.repository.BetRepository;

@Service
public class BetServiceImpl implements BetService{

	@Autowired
	private BetLogic logic;
	
    @Autowired
    private BetRepository betRepository;  

	@Override
	public List<Bet> findAll() {

		return betRepository.findAll();
	}

	@Override
	public Optional<Bet> findById(long id) {

		return betRepository.findById(id);
	}

	@Override
	public void  save(Bet bet) {

	}

	@Override
	public boolean isValidBetAmount(long betAmount) {
		
		return logic.isValidBetAmount(betAmount);
	}

	@Override
	public boolean isTypeBetAccepted(String typeBet) {
		
		return logic.isTypeBetAccepted(typeBet);
	}

	@Override
	public Bet save(long idActiveGame, long id, String typeBet, String typeBet2, long betAmount) {
        Bet bet = new Bet(idActiveGame, id, logic.getColor(typeBet), logic.getNumber(typeBet), betAmount);
        betRepository.save(bet);    
        return bet;
		
	}

	@Override
	public List<Bet> close(Game game) {
		List<Bet> bets = new ArrayList<Bet>();
		
		for (Bet bet : findAll()) {
			if(bet.getGameId()==game.getId()) {
				if(bet.getBetNumber()==game.getNumberResult()) {
					bet.setReturnBetAmount(logic.calculatedNumberBet(bet.getBetNumber()));	
				}else if(bet.getBetColor().equals(game.getColorResult())){
					bet.setReturnBetAmount(logic.calculatedColorBet(bet.getBetNumber()));
				}else {
					bet.setReturnBetAmount(0);
				}
				 betRepository.save(bet); 
				 bets.add(bet);
			}	
		}
		return bets;
	}

}
