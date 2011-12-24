package com.zoeetrope.othello.view;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import com.zoeetrope.othello.controller.BoardEvent;
import com.zoeetrope.othello.model.Board;
import com.zoeetrope.othello.model.Piece;

public class BoardView extends JPanel implements Observer, MouseListener {

  private static final long serialVersionUID = 3723154022189180922L;
  private Board board;
  private ArrayList<PieceView> pieceViews;
  private int cellSize = 50;
  private ArrayList<ActionListener> listeners;
  
  public BoardView(Board board) {
    this.board = board;
    this.pieceViews = new ArrayList<PieceView>();
    this.listeners = new ArrayList<ActionListener>();
    
    this.board.addObserver(this);
    setVisible(true);
    
    this.addMouseListener(this);
  }

  @Override
  public void update(Observable o, Object arg) {
    if(arg instanceof Piece) {
      PieceView v = new PieceView((Piece)arg, this);
      add(v);
      revalidate();
    }
    
    repaint();
  }
  
  public void paint(Graphics g) {
    super.paint(g);
    cellSize = (this.getWidth() / 8) - 1;
    
    for(int x = 0, row = 0; row <= 8; x += cellSize, row++) {
      g.drawLine(0, x, cellSize * 8, x);
    }
    
    for(int y = 0, col = 0; col <= 8; y += cellSize, col++) {
      g.drawLine(y, 0, y, cellSize * 8);
    }
    
    for(PieceView pv : pieceViews) {
      pv.repaint();
    }
  }
  
  public int getCellSize() {
    return cellSize;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
//    board.addPiece(new Piece(PieceColor.BLACK, 4, 2));
    int cellSize = getCellSize();
    int x = e.getX() / cellSize;
    int y = e.getY() / cellSize;
    
    broadcastAction(new BoardEvent(this, 0, "move:" + x + "/" + y));
  }

  @Override
  public void mousePressed(MouseEvent e) {
  }

  @Override
  public void mouseReleased(MouseEvent e) {
  }

  @Override
  public void mouseEntered(MouseEvent e) {
  }

  @Override
  public void mouseExited(MouseEvent e) {
  }

  public void addActionListener(ActionListener listener) {
    this.listeners.add(listener);
  }
  
  private void broadcastAction(ActionEvent event) {
    for(ActionListener l : listeners) {
      l.actionPerformed(event);
    }
  }

}
