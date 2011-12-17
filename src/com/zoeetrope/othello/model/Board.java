package com.zoeetrope.othello.model;

import java.util.ArrayList;
import java.util.Observable;

public class Board extends Observable {

  private ArrayList<Piece> pieces;
  
  public Board() {
    this.pieces = new ArrayList<Piece>();
  }
  
  public ArrayList<Piece> getPieces() {
    return pieces;
  }
  
  public void addPiece(Piece piece) {
    pieces.add(piece);
    
    setChanged();
    notifyObservers(piece);
  }
  
}
