package com.zoeetrope.othello.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Observable;

public class Board extends Observable {

  private Piece[][] pieces;
  
  public Board() {
    this.pieces = new Piece[8][8];
  }
  
  public Board(Piece[][] pieces) {
    this.pieces = pieces;
  }
  
  public Board getDeepCopy() {
    Piece[][] piecesCopy = new Piece[8][8];
    
    for(int x = 0; x < pieces.length; x++) {
      Piece[] row = pieces[x];
      
      for(int y = 0; y < row.length; y++) {
        if(row[y] != null) {
          piecesCopy[x][y] = row[y].getDeepCopy();
        }
      }
    }
    
    return new Board(piecesCopy);
  }
  
  public int getPieceCount(PieceColor color) {
    int count = 0;
    
    for(Piece[] row : pieces) {
      for(Piece p : row) {
        if(p != null && p.getColor() == color) {
          count++;
        }
      }
    }
    
    return count;
  }
  
  public ArrayList<Piece> getPieces(PieceColor color) {
    ArrayList<Piece> pieces = new ArrayList<Piece>();
    
    for(Piece[] row : this.pieces) {
      for(Piece p : row) {
        if(p != null && p.getColor() == color) {
          pieces.add(p);
        }
      }
    }
    
    return pieces;
  }
  
  public Piece getPiece(int x, int y) {
    if(placeInRange(x, y)) {
      return pieces[x][y];
    }
    return null;
  }
  
  public void addPiece(Piece piece) {    
    pieces[piece.getX()][piece.getY()] = piece;
    
    setChanged();
    notifyObservers(piece);
  }
  
  public void removePiece(Piece piece) {
    pieces[piece.getX()][piece.getY()] = null;
    
    setChanged();
    notifyObservers();
  }
  
  public void clear() {
    this.pieces = new Piece[8][8];
    
    setChanged();
    notifyObservers("clearGame");
  }
  
  public void makeMove(Piece piece) {
    if(validMove(piece)) {
      ArrayList<Piece> captured = piece.getCapturedPieces(this);
      
      for(Piece p : captured) {
        p.setColor(p.getColor().getOpposite());
      }
      
      addPiece(piece);
    }
  }
  
  public boolean isPlaceFree(Piece piece) {
    if(placeInRange(piece.getX(), piece.getY())) {
      return pieces[piece.getX()][piece.getY()] == null;
    } else {
      return false;
    }
  }
  
  public boolean isPlaceFree(int x, int y) {
    if(placeInRange(x, y)) {
      return pieces[x][y] == null;
    } else {
      return false;
    }
  }
  
  public boolean placeHasColor(int x, int y, PieceColor color) {
    if(placeInRange(x, y)) {
      Piece piece = pieces[x][y];
      
      if(piece != null && piece.getColor() == color) {
        return true;
      }
    }
    
    return false;
  }
  
  public boolean placeInRange(int x, int y) {
    return x >= 0 && x < 8 && y >= 0 && y < 8;
  }
  
  public ArrayList<Piece> getValidMoves(PieceColor color) {
    HashSet<Piece> moveSet = new HashSet<Piece>();
    ArrayList<Piece> moves = new ArrayList<Piece>();
    ArrayList<Piece> opposingPieces = getPieces(color.getOpposite());
    
    for(Piece opposingPiece : opposingPieces) {
      ArrayList<Piece> freeNeighbors = opposingPiece.getEmptyNeighbors(this);
      for(Piece free : freeNeighbors) {
        free.setColor(color);
        if(free.getCapturedPieces(this).size() > 0) {
          moveSet.add(free);
        }
      }
    }
    
    moves.addAll(moveSet);
    return moves;
  }
  
  private boolean validMove(Piece piece) {
    // Just ignore moves on spots that are already taken.
    if(pieces[piece.getX()][piece.getY()] != null) {
      return false;
    }
    
    ArrayList<Piece> moves = getValidMoves(piece.getColor());
    
    if(!moves.contains(piece)) {
      return false;
    }
    
    return true;
  }

}
