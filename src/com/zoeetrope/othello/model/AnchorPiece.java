package com.zoeetrope.othello.model;

import java.util.ArrayList;

public class AnchorPiece {
  
  private Piece origin;
  private Piece anchor;
  private ArrayList<Piece> captured;
  
  public AnchorPiece(Piece piece) {
    this.origin = piece;
    this.captured = new ArrayList<Piece>();
  }

  public ArrayList<Piece> getCapturedPieces(Board board) {
    int x = origin.getX(), y = origin.getY();
    int dirX = origin.getX() < anchor.getX() ? 1 : -1;
    dirX = origin.getX() == anchor.getX() ? 0 : dirX;
    int dirY = origin.getY() < anchor.getY() ? 1 : -1;
    dirY = origin.getY() == anchor.getY() ? 0 : dirY;
    
    Piece step = new Piece(origin.getColor().getOpposite());
    
    while(step != null && step.getColor() == origin.getColor().getOpposite() && 
          board.placeInRange(x, y)) {
      x += dirX;
      y += dirY;
      step = board.getPiece(x, y);
      
      if(step.getColor() == origin.getColor().getOpposite()) {
        captured.add(step);
      }
    }
    
    return captured;
  }
  
  public void setAnchor(Piece piece) {
    anchor = piece;
  }
  
  public void addCaptured(Piece piece) {
    captured.add(piece);
  }

}
