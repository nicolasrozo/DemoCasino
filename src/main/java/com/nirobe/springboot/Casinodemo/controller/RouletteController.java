package com.nirobe.springboot.Casinodemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nirobe.springboot.Casinodemo.business.BetLogic;
import com.nirobe.springboot.Casinodemo.exception.ErrorTypeException;
import com.nirobe.springboot.Casinodemo.exception.ResourceNotFoundException;
import com.nirobe.springboot.Casinodemo.model.Bet;
import com.nirobe.springboot.Casinodemo.model.Game;
import com.nirobe.springboot.Casinodemo.model.Roulette;
import com.nirobe.springboot.Casinodemo.model.User;
import com.nirobe.springboot.Casinodemo.repository.BetRepository;
import com.nirobe.springboot.Casinodemo.repository.GameRepository;
import com.nirobe.springboot.Casinodemo.repository.RouletteRepository;
import com.nirobe.springboot.Casinodemo.repository.UserRepository;
import com.nirobe.springboot.Casinodemo.service.BetService;
import com.nirobe.springboot.Casinodemo.service.GameServiceImpl;
import com.nirobe.springboot.Casinodemo.service.RouletteService;
import com.nirobe.springboot.Casinodemo.service.UserService;
import com.nirobe.springboot.Casinodemo.service.UserServiceImpl;


@RestController
@RequestMapping("/api/")
public class RouletteController {

    @Autowired
    private RouletteService rouletteService;
	@Autowired
	private UserServiceImpl userService;;
    @Autowired
    private GameServiceImpl gameService;
    @Autowired
    private BetService betService;  
    
	@PostMapping("/roulettes")
    public long createRoulette() {
		
        return rouletteService.save();
    }

	@GetMapping("/open/{id}")
	public String String(@PathVariable(value = "id") long rouletteId) throws ResourceNotFoundException{
		Roulette roulette = rouletteService.findById(rouletteId);
        if(!roulette.isActive()) {
            gameService.save(roulette.getId());     	
        }
        
        return "Operacion exitosa";
	}
	
	@PostMapping("/bet/{id}")
	public Bet betRoulette(@PathVariable(value = "id") long rouletteId, @RequestParam(value = "amountBet") String amountBet,  
			@RequestParam(value = "typeBet") String typeBet, @RequestHeader("idUsuario") long userId)
			throws ResourceNotFoundException, ErrorTypeException {
		
		long betAmount = 0;
		Roulette roulette = rouletteService.findById(rouletteId);
		if(!roulette.isActive()) 
			new ResourceNotFoundException("Ruleta no abierta");
		try {
	        betAmount = Long.parseLong(amountBet);
	        if(!betService.isValidBetAmount(betAmount)) {
	        	new ResourceNotFoundException("El monto apostado sobre pasa el maximo monto para apostar");
	        }
	    } catch (Exception e) {
	    	new ErrorTypeException("No se ingreso un numero de monto para apostar");
	    }
        if(!betService.isTypeBetAccepted(typeBet)) {
        	new ResourceNotFoundException("Error en el tipo de apuesta ingresado");
        }
		User user = userService.findById(userId);
		if(user.getCredit()<betAmount) {
        	new ErrorTypeException("El valor apostado es menor al credito disponible");
        }
		userService.updateUserCredit(user, betAmount);
		
		return betService.save(roulette.getIdActiveGame(), user.getId(), typeBet, typeBet, betAmount);
	}	
	
	
	@PostMapping("/close/{id}")
	public List<Bet> betRoulette(@PathVariable(value = "id") Long rouletteId) 
			throws ResourceNotFoundException {
		Roulette roulette = rouletteService.findById(rouletteId);
		if(!roulette.isActive()) 
			new ResourceNotFoundException("Ruleta no abierta");
		Game game = gameService.close(roulette);
		rouletteService.close(roulette);
		return betService.close(game);
		
	}
	
	@GetMapping("/roulettes")
	public List<Roulette> getRouletteBets() {
		
		return rouletteService.findAll();	
	}	
	
}
