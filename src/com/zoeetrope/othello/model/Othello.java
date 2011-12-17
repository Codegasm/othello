package com.zoeetrope.othello.model;

import java.util.ArrayList;
import java.util.Observable;

public class Othello extends Observable {

  private ArrayList<Player> players;
  private Board board;
  private GameState state;
  
  public Othello() {
    board = new Board();
    players = new ArrayList<Player>();
    state = GameState.BEFORE;
  }
  
  public void startGame() {
    state = GameState.DURING;
    
    board.addPiece(new Piece(PieceColor.BLACK, 3, 3));
    board.addPiece(new Piece(PieceColor.BLACK, 4, 4));
    board.addPiece(new Piece(PieceColor.WHITE, 3, 4));
    board.addPiece(new Piece(PieceColor.WHITE, 4, 3));
    
    for(Player p : players) {
      p.setScore(2);
    }
  }
  
  public void addPlayer(Player player) {
    if(state == GameState.BEFORE) {
      if(players.size() < 2) {
        players.add(player);
        
        setChanged();
        notifyObservers(player);
      }
    }
  }
  
  public ArrayList<Player> getPlayers() {
    return this.players;
  }
  
  public Board getBoard() {
    return board;
  }
  
}
