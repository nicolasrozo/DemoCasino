package com.nirobe.springboot.Casinodemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nirobe.springboot.Casinodemo.exception.ResourceNotFoundException;
import com.nirobe.springboot.Casinodemo.model.Roulette;
import com.nirobe.springboot.Casinodemo.repository.RouletteRepository;

@Service
public class RouletteServiceImpl  implements RouletteService{

	@Autowired
    private RouletteRepository rouletteRepository;
    
	@Override
	public List<Roulette> findAll() {
		
		return rouletteRepository.findAll();
	}

	@Override
	public Roulette findById(long id) throws ResourceNotFoundException  {
        Roulette roulette = rouletteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Operacion Denegada"));
        
        return roulette;
	}

	@Override
	public long save() {
		Roulette roulette = new Roulette();
		
        return rouletteRepository.save(roulette).getId();
		
	}

	public void updateRouletter(Roulette roulette, long idGame) {
        roulette.setActive(true);
        roulette.setIdActiveGame(idGame);
        rouletteRepository.save(roulette);
	}

	@Override
	public void close(Roulette roulette) {
		roulette.setActive(false);
		roulette.setIdActiveGame(0);
		rouletteRepository.save(roulette);
		
	}
}
