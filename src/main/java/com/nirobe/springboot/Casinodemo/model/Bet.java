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
    private String betColor;
    private int betNumber;
    private long betAmount;
    private long returnBetAmount;
    
    public Bet() {

    }
    
    public Bet(long gameId, long userId, String colorBet, int number, long amount) {
        this.gameId = gameId;
        this.setUserId(userId);
        this.betColor = colorBet;
        this.betNumber = number;       
        this.betAmount = amount;       
        this.betAmount = 0;
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

    @Column(name = "bet_color", nullable = false)
	public String getBetColor() {
		return betColor;
	}

	public void setBetColor(String betColor) {
		this.betColor = betColor;
	}

    @Column(name = "bet_number", nullable = false)
	public int getBetNumber() {
		return betNumber;
	}

	public void setBetNumber(int betNumber) {
		this.betNumber = betNumber;
	}

    @Column(name = "bet_amount", nullable = false)
	public long getBetAmount() {
		return betAmount;
	}

	public void setBetAmount(long betAmount) {
		this.betAmount = betAmount;
	}

    @Column(name = "return_bet_amount", nullable = false)
	public long getReturnBetAmount() {
		return returnBetAmount;
	}

	public void setReturnBetAmount(long returnBetAmount) {
		this.returnBetAmount = returnBetAmount;
	}

    @Column(name = "user_id", nullable = false)
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

    
}
