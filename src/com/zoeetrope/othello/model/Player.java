package com.zoeetrope.othello.model;

import java.util.Observable;

public class Player extends Observable {

  private PieceColor color;
  private String name;
  private int score;
  
  public Player(PieceColor color, String name) {
    this.color = color;
    this.name = name;
    this.score = 0;
  }

  public String getName() {
    return name;
  }
  
  public void setScore(int score) {
    this.score = score;
    
    setChanged();
    notifyObservers();
  }
  
  public int getScore() {
    return score;
  }
  
  public PieceColor getColor() {
    return color;
  }
  
}
