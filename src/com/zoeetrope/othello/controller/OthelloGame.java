package com.zoeetrope.othello.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

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
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    Pattern p1 = Pattern.compile("move:(\\d)/(\\d)");
    Pattern p2 = Pattern.compile("addPlayer:(\\s)");
    Matcher m1 = p1.matcher(e.getActionCommand());
    Matcher m2 = p2.matcher(e.getActionCommand());
    
    if(m1.find()) {
      Piece piece = othello.getNextPiece();
      piece.setLocation(Integer.parseInt(m1.group(1)), Integer.parseInt(m1.group(2)));
      
      othello.getBoard().makeMove(piece);
    } else if(e.getActionCommand().equals("newGame")) {
      String player1 = (String)JOptionPane.showInputDialog("Player 1:");
      String player2 = (String)JOptionPane.showInputDialog("Player 2:");
      
      this.othello.clearGame();
      this.othello.addPlayer(new Player(PieceColor.BLACK, player1));
      this.othello.addPlayer(new Player(PieceColor.WHITE, player2));
      this.othello.startGame();
    } else if(e.getActionCommand().equals("quitGame")) {
      System.exit(0);
    } else if(m2.find()) {
      this.othello.addPlayer(new Player(m2.group(1)));
    }
  }
  
}
