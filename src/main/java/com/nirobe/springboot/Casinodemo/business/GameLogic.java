package com.nirobe.springboot.Casinodemo.business;

import org.springframework.stereotype.Component;

@Component
public class GameLogic {
	
	public GameLogic() {
		
	}
	public int generateRouletteNumber() {
		
		return  (int) ((Math.random() * (36 - 0)) + 0);
	}
	
	public String getColorNumber(int number) {
		if(number%2 == 0) {
			
			return BetLogic.ROJO;
		}else {
			
			return BetLogic.NEGRO;
		}
	}
}
