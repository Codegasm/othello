package com.zoeetrope.othello.model;

public enum PieceColor {
  BLACK,
  WHITE;
  
  public PieceColor getOpposite() {
    PieceColor color = BLACK;
    
    if(this == BLACK) {
      color = WHITE;
    }
    
    return color;
  }
  
}
