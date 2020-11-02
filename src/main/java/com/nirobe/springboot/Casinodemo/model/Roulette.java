package com.nirobe.springboot.Casinodemo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roulettes")
public class Roulette {

    private long id;
    private boolean active;
    private long activeGameId;
    
    public Roulette() {	
        this.active = false;
        this.activeGameId = 0;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
    	
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
   
    @Column(name = "active", nullable = false)
	public boolean isActive() {
    	
		return active;
	}
	public void setActive(boolean state) {
		this.active = state;
	}

    @Column(name = "id_active_game", nullable = false)
	public long getIdActiveGame() {
    	
		return activeGameId;
	}

	public void setIdActiveGame(long idActiveGame) {
		this.activeGameId = idActiveGame;
	}

}
