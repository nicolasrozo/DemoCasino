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

import com.nirobe.springboot.Casinodemo.business.RouletteLogic;
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


@RestController
@RequestMapping("/api/")
public class RouletteController {

    @Autowired
    private RouletteRepository rouletteRepository;
	@Autowired
	private UserRepository userRepository;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private BetRepository betRepository;  
    
	@PostMapping("/roulettes")
    public long createRoulette() {
		Roulette roulette = new Roulette();
		
        return rouletteRepository.save(roulette).getId();
    }

	@GetMapping("/open/{id}")
	public String String(@PathVariable(value = "id") long rouletteId)  
			throws ResourceNotFoundException  {
		
        Roulette roulette = rouletteRepository.findById(rouletteId)
                .orElseThrow(() -> new ResourceNotFoundException("Operacion Denegada"));
        if(!roulette.isActive()) {
            Game game = new Game(roulette.getId());
            long idGame = gameRepository.save(game).getId();
            roulette.setActive(true);
            roulette.setIdActiveGame(idGame);
            rouletteRepository.save(roulette);       	
        }
        
        return "Operacion exitosa";
	}
	
	@PostMapping("/bet/{id}")
	public Bet betRoulette(@PathVariable(value = "id") Long rouletteId, @RequestParam String amountBet,  
			@RequestParam String typeBet, @RequestHeader("idUsuario") long userId)
			throws ResourceNotFoundException {
		long betAmount = 0;
		
		// validacion ruleta
		Roulette roulette = rouletteRepository.findById(rouletteId)
                .orElseThrow(() -> new ResourceNotFoundException("Numero de ruleta no encontrada"));
		if(!roulette.isActive()) 
			new ResourceNotFoundException("Ruleta no abierta");
		
	    //validaciones apuesta
		try {
	        betAmount = Long.parseLong(amountBet);
	        if(!RouletteLogic.isValidBetAmount(betAmount)) {
	        	new ResourceNotFoundException("El monto apostado sobre pasa el maximo monto para apostar");
	        }
	    } catch (Exception e) {
	    	new ErrorTypeException("No se ingreso un numero de monto para apostar");
	    }
        if(!RouletteLogic.isTypeBetAccepted(typeBet)) {
        	new ResourceNotFoundException("Error en el tipo de apuesta ingresado");
        }
        
        //usuario
		User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Id de usuario no valido"));       
        if(user.getCredit()<betAmount) {
        	new ErrorTypeException("El valor apostado es menor al credito disponible");
        }
        
        //operaciones repositorios
        user.setCredit(user.getCredit()-betAmount);
        userRepository.save(user);
        	
        Bet bet = new Bet(roulette.getIdActiveGame(), user.getId(), RouletteLogic.getColor(typeBet), RouletteLogic.getNumber(typeBet), betAmount);
        return betRepository.save(bet);
        
	}		
	
	
	@GetMapping("/roulettes")
	public List <Roulette> getRouletteBets() {
		
		return rouletteRepository.findAll();	
	}	
	
}
