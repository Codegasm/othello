package com.zoeetrope.othello.ai;

import java.util.Observable;
import java.util.Observer;

import com.zoeetrope.othello.model.Othello;
import com.zoeetrope.othello.model.Player;

public abstract class OthelloAI implements Observer {
  
  protected Player player;
  protected Othello model;
  
  public OthelloAI(Player player, Othello othello) {
    this.player = player;
    this.model = othello;
    
    this.model.addObserver(this);
  }

  @Override
  public void update(Observable o, Object arg) {
    System.out.println("I'm no AI, you'd best override this function!");
  }
  
  public void stopAI() {
    this.model.deleteObserver(this);
  }
  
}
