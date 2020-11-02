package com.nirobe.springboot.Casinodemo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bets")
public class Bet {

    private long id;
    private long gameId;
    private long userId;
    private String colorBet;
    private int number;
    private long amount;
    private long returnAmount;
    
    public Bet() {

    }
    
    public Bet(long gameId, long userId, String colorBet, int number, long amount) {
        this.gameId = gameId;
        this.userId = userId;
        this.colorBet = colorBet;
        this.number = number;       
        this.amount = amount;       
        this.setAmountReturn(0);
    }   
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
    	
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "game_id", nullable = false)
	public long getGameId() {
		return gameId;
	}

	public void setGameId(long gameId) {
		this.gameId = gameId;
	}

    @Column(name = "user_id", nullable = false)
	public long getUserId() {
    	
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

    @Column(name = "color_bet", nullable = true)
	public String getColorBet() {
    	
		return colorBet;
	}

	public void setColorBet(String colorBet) {
		this.colorBet = colorBet;
	}

    @Column(name = "numbet_bet", nullable = true)
	public int getNumberBet() {
    	
		return number;
	}

	public void setNumberBet(int numberBet) {
		this.number = numberBet;
	}
	
    @Column(name = "amount", nullable = false)
	public long getAmountBet() {
    	
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

    @Column(name = "return_amount", nullable = false)
	public long getReturnAmount() {
		
		return returnAmount;
	}

	public void setAmountReturn(long amountReturn) {
		this.returnAmount = amountReturn;
	}
  
    
}
