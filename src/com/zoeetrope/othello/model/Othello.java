package com.zoeetrope.othello.model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Othello extends Observable implements Observer {

  private Player currentPlayer;
  private ArrayList<Player> players;
  private Board board;
  private GameState state;
  
  public Othello() {
    board = new Board();
    players = new ArrayList<Player>();
    state = GameState.BEFORE;
    
    board.addObserver(this);
  }
  
  public void startGame() {
    // TODO: Check that we have two players.
    
    board.addPiece(new Piece(PieceColor.BLACK, 3, 3));
    board.addPiece(new Piece(PieceColor.BLACK, 4, 4));
    board.addPiece(new Piece(PieceColor.WHITE, 3, 4));
    board.addPiece(new Piece(PieceColor.WHITE, 4, 3));
    
    for(Player p : players) {
      p.setScore(2);
    }
    
    currentPlayer = players.get(0);
    currentPlayer.setActive(true);
    
    state = GameState.DURING;
    
    setChanged();
    notifyObservers();
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
  
  public Piece getNextPiece() {
    return new Piece(currentPlayer.getColor());
  }

  @Override
  public void update(Observable o, Object arg) {
    if(state == GameState.DURING && arg instanceof Piece) {
      // A piece was placed.
      
      switchPlayer();
      
      if(board.getValidMoves(currentPlayer.getColor()).size() == 0) {
        // The other player can't play, switch back.
        switchPlayer();
      }
      
      if(board.getValidMoves(currentPlayer.getColor()).size() == 0) {
        // Game over man!
        state = GameState.AFTER;
      }
      
      updateScores();
    }
  }
  
  private void switchPlayer() {
    System.out.println("switch player");
    // This ugly construct serves to switch the current player.
    for(Player p : players) {
      if(currentPlayer != p) {
        currentPlayer = p;
        break;
      }
    }
  }
  
  private void updateScores() {
    for(Player p : players) {
      p.setScore(board.getPieceCount(p.getColor()));
      p.setActive(p == currentPlayer);
    }
  }
}
