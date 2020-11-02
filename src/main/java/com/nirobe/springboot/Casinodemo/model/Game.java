package com.nirobe.springboot.Casinodemo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "games")
public class Game {

    private long id;
    private long rouletteId;
    private long totalAmountBet;
    private String colorResult;
    private int numberResult;
    private long totalAmountPaid;
    
    public Game() {

    }
  
    public Game(long rouletteId) {
    	this.rouletteId = rouletteId;
        this.totalAmountBet = 0;
        this.colorResult = "";
        this.numberResult = -1;
        this.totalAmountPaid = 0;
    }

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "roulette_id", nullable = false)
	public long getRouletteId() {
		return rouletteId;
	}


	public void setRouletteId(long rouletteId) {
		this.rouletteId = rouletteId;
	}

    @Column(name = "total_amount_bet", nullable = false)
	public long getTotalAmountBet() {
		return totalAmountBet;
	}


	public void setTotalAmountBet(long totalAmountBet) {
		this.totalAmountBet = totalAmountBet;
	}

    @Column(name = "color_result", nullable = true)
	public String getColorResult() {
		return colorResult;
	}


	public void setColorResult(String colorResult) {
		this.colorResult = colorResult;
	}


    @Column(name = "number_result", nullable = true)
	public int getNumberResult() {
		return numberResult;
	}


	public void setNumberResult(int numberResult) {
		this.numberResult = numberResult;
	}

    @Column(name = "total_amount_paid", nullable = false)
	public long getTotalAmountPaid() {
		return totalAmountPaid;
	}


	public void setTotalAmountPaid(long totalAmountPaid) {
		this.totalAmountPaid = totalAmountPaid;
	}
  
    
}
