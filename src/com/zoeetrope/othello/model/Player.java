package com.zoeetrope.othello.model;

import java.util.Observable;

public class Player extends Observable {

  private PieceColor color;
  private String name;
  private int score;
  private boolean active;
  
  public Player(PieceColor color, String name) {
    this.color = color;
    this.name = name;
    this.score = 0;
  }
  
  public Player(String name) {
    this(PieceColor.BLACK, name);
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
  
  public void setActive(boolean active) {
    this.active = active;
    
    setChanged();
    notifyObservers();
  }
  
  public boolean isActive() {
    return active;
  }
  
  public void setColor(PieceColor color) {
    this.color = color;
  }
  
  public PieceColor getColor() {
    return color;
  }
  
}
