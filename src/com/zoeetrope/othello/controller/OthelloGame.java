package com.zoeetrope.othello.controller;

import com.zoeetrope.othello.model.Othello;
import com.zoeetrope.othello.model.PieceColor;
import com.zoeetrope.othello.model.Player;
import com.zoeetrope.othello.view.OthelloWindow;

public class OthelloGame {

  private Othello othello;
  private OthelloWindow window;
  
  public OthelloGame() {
    this.othello = new Othello();
    this.window = new OthelloWindow(this.othello);
    
    this.othello.addObserver(this.window);
    
    this.othello.addPlayer(new Player(PieceColor.BLACK, "Julia"));
    this.othello.addPlayer(new Player(PieceColor.WHITE, "Zoee"));
    this.othello.startGame();
  }
  
}
