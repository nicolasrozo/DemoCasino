package com.nirobe.springboot.Casinodemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nirobe.springboot.Casinodemo.model.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long>{


}
