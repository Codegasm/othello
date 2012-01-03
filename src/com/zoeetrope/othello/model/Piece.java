package com.zoeetrope.othello.model;

import java.util.ArrayList;

public class Piece {

  private PieceColor color;
  private int x;
  private int y;
  
  public Piece(PieceColor color) {
    this.color = color;
  }

  public Piece(PieceColor color, int x, int y) {
    this(color);
    
    this.x = x;
    this.y = y;
  }
  
  public Piece(int x, int y) {
    this(PieceColor.BLACK, x, y);
  }
  
  public Piece() {
    this(PieceColor.BLACK);
  }
  
  public Piece(Piece piece) {
    this(piece.getColor(), piece.getX(), piece.getY());
  }
  
  public Piece getDeepCopy() {
    return new Piece(this);
  }
  
  public void setLocation(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public void setColor(PieceColor color) {
    this.color = color;
  }
  
  public PieceColor getColor() {
    return color;
  }
  
  public int getX() {
    return x;
  }
  
  public int getY() {
    return y;
  }
  
  public ArrayList<Piece> getEmptyNeighbors(Board board) {
    ArrayList<Piece> neighbors = new ArrayList<Piece>();
    
    for(int x = this.getX() - 1, y = this.getY() - 1; y <= this.getY() + 1; x++) {
      
      if(board.isPlaceFree(x, y)) {
        neighbors.add(new Piece(x, y));
      }
      
      if(x > this.getX()) {
        x = this.getX() - 2;
        y++;
      }
    }
    
    return neighbors;
  }
  
  public ArrayList<Piece> getOpposingNeighbors(Board board) {
    ArrayList<Piece> neighbors = new ArrayList<Piece>();
    
    for(int x = this.getX() - 1, y = this.getY() - 1; y <= this.getY() + 1; x++) {
      
      if(board.placeHasColor(x, y, this.getColor().getOpposite())) {
        neighbors.add(board.getPiece(x, y));
      }
      
      if(x > this.getX()) {
        x = this.getX() - 2;
        y++;
      }
    }
    
    return neighbors;
  }
  
  public ArrayList<Piece> getCapturedPieces(Board board) {
    ArrayList<Piece> captured = new ArrayList<Piece>();
    ArrayList<AnchorPiece> anchors = getAnchorPieces(board);
    
    for(AnchorPiece ac : anchors) {
      captured.addAll(ac.getCapturedPieces(board));
    }
    
    return captured;
  }
  
  private ArrayList<AnchorPiece> getAnchorPieces(Board board) {
    ArrayList<AnchorPiece> anchors = new ArrayList<AnchorPiece>();
    ArrayList<Piece> opposingPieces = getOpposingNeighbors(board);
    
    for(Piece p : opposingPieces) {
      int x = this.getX(), y = this.getY();
      int dirX = p.getX() - this.getX();
      int dirY = p.getY() - this.getY();
      Piece step = new Piece(p.getColor());
      
      while(step != null && step.getColor() == p.getColor() && board.placeInRange(x, y)) {
        x += dirX;
        y += dirY;
        step = board.getPiece(x, y);
      }
      
      if(step != null && step.getColor() == this.color) {
        AnchorPiece anchor = new AnchorPiece(this);
        anchor.setAnchor(step);
        anchors.add(anchor);
      }
    }
    
    return anchors;
  }
  
  @Override
  public boolean equals(Object o) {
    if(o instanceof Piece) {
      Piece p = (Piece)o;
      
      if(p.getX() == getX() && p.getY() == getY() && p.getColor() == getColor()) {
        return true;
      }
    }
    
    return false;
  }
  
  @Override
  public int hashCode() {
    return getX() + getY() + getColor().ordinal();
  }
  
  @Override
  public String toString() {
    return "x:" + getX() + " y:" + getY() + " color:" + getColor();
  }
  
}
