package com.nirobe.springboot.Casinodemo.business;

public class RouletteLogic {
	
	public static final String ROJO= "rojo" ;
	public static final String NEGRO = "negro";
	public static final String EMPTY = "empty";
	
	public static int generateRouletteNumber() {
		
		return  (int) ((Math.random() * (36 - 0)) + 0);
	}
	
	public static String getColorNumber(int number) {
		if(number%2 == 0) {
			
			return ROJO;
		}else {
			
			return NEGRO;
		}
	}

	public static boolean isValidBetNumber(int number) {
		if(0<=number && number<=36) {
			
			return true;
		}else {
			
			return false;
		}
	}
	
	public static boolean isValidBetAmount(long amountBet) {
		if(amountBet <= 10000) {
			
			return true;
		}else {
			
			return false;
		}
	}
	
	public static boolean isTypeBetAccepted(String typeBet) {
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

	public static int getNumber(String typeBet) {
	    try {
	        int number = Integer.parseInt(typeBet);
	      
	        return number;
	    } catch (Exception e) {
	    	
	    	return -1;
	    }
	}
	
	public static String getColor(String typeBet) {
	    try {
	        Integer.parseInt(typeBet);
	        return EMPTY;
	    } catch (Exception e) {
	    	return typeBet.equalsIgnoreCase(ROJO) ? ROJO : NEGRO;
	    }
	}
	
}
