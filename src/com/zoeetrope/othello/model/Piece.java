package com.zoeetrope.othello.model;

public class Piece {

  private PieceColor color;
  private int x;
  private int y;
  
  public Piece(PieceColor color, int x, int y) {
    this.color = color;
    this.x = x;
    this.y = y;
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
  
}
