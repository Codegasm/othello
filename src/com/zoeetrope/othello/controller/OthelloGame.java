package com.zoeetrope.othello.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.zoeetrope.othello.model.Othello;
import com.zoeetrope.othello.model.Piece;
import com.zoeetrope.othello.model.PieceColor;
import com.zoeetrope.othello.model.Player;
import com.zoeetrope.othello.view.OthelloWindow;

public class OthelloGame implements ActionListener {

  private Othello othello;
  private OthelloWindow window;
  
  public OthelloGame() {
    this.othello = new Othello();
    this.window = new OthelloWindow(this.othello, this);
    
    this.othello.addObserver(this.window);
    
    this.othello.addPlayer(new Player(PieceColor.BLACK, "Julia"));
    this.othello.addPlayer(new Player(PieceColor.WHITE, "Zoee"));
    this.othello.startGame();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    Pattern p = Pattern.compile("move:(\\d)/(\\d)");
    Matcher m = p.matcher(e.getActionCommand());
    
    if(m.find()) {
      Piece piece = othello.getNextPiece();
      piece.setLocation(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)));
      
      othello.getBoard().makeMove(piece);
    }
    
    System.out.println(e.getActionCommand());
  }
  
}
