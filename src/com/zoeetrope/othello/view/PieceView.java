package com.zoeetrope.othello.view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.zoeetrope.othello.model.Piece;
import com.zoeetrope.othello.model.PieceColor;

public class PieceView extends JPanel {

  private static final long serialVersionUID = 1102029433703727447L;
  private Piece piece;
  private BoardView boardView;
  
  public PieceView(Piece piece, BoardView boardView) {
    this.piece = piece;
    this.boardView = boardView;
    
    setVisible(true);
  }
  
  public void paint(Graphics g) {
    if(piece.getColor() == PieceColor.BLACK) {
      g.setColor(Color.BLACK);
    } else {
      g.setColor(Color.WHITE);
    }
    
    int cellSize = boardView.getCellSize();
    
    setBounds(piece.getX() * cellSize, piece.getY() * cellSize,
        cellSize, cellSize);
    g.fillOval(2, 2, cellSize - 4, cellSize - 4);
    
    g.setColor(Color.BLACK);
    g.drawOval(2, 2, cellSize - 4, cellSize - 4);
  }
  
}
