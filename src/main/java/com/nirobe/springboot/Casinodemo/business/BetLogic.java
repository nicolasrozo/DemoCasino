package com.nirobe.springboot.Casinodemo.business;

import org.springframework.stereotype.Component;

@Component
public class BetLogic {
	
	public static final String ROJO= "rojo" ;
	public static final String NEGRO = "negro";
	public static final String EMPTY = "empty";
	
	public BetLogic() {
		
	}
	
	public boolean isValidBetNumber(int number) {
		if(0<=number && number<=36) {
			
			return true;
		}else {
			
			return false;
		}
	}
	
	public boolean isValidBetAmount(long amountBet) {
		if(amountBet <= 10000) {
			
			return true;
		}else {
			
			return false;
		}
	}
	
	public boolean isTypeBetAccepted(String typeBet) {
	    try {
	        int number = Integer.parseInt(typeBet);
	        
	        return isValidBetNumber(number);
	    } catch (Exception e) {
	    	if(typeBet.equalsIgnoreCase(ROJO) || typeBet.equalsIgnoreCase(NEGRO)) {
	    		
	    		return true;
	    	}else {
	    		
	    		return false;
	    	}
	    }
	}

	public int getNumber(String typeBet) {
	    try {
	        int number = Integer.parseInt(typeBet);
	      
	        return number;
	    } catch (Exception e) {
	    	
	    	return -1;
	    }
	}
	
	public String getColor(String typeBet) {
	    try {
	        Integer.parseInt(typeBet);
	        return EMPTY;
	    } catch (Exception e) {
	    	return typeBet.equalsIgnoreCase(ROJO) ? ROJO : NEGRO;
	    }
	}
	
	public long calculatedNumberBet(long amount) {
		return (long) (amount*1.8);
	}
	
	public long calculatedColorBet(long amount) {
		return amount*5;
	}
}
