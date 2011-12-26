package com.zoeetrope.othello.model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class PieceTest {

  private Board testBoard;
  private Piece piece1;
  private Piece piece2;
  private Piece piece3;
  private Piece piece4;
  
  @Before
  public void setUp() {
    testBoard = new Board();
    
    piece1 = new Piece(PieceColor.BLACK, 3, 3);
    piece2 = new Piece(PieceColor.BLACK, 4, 4);
    
    piece3 = new Piece(PieceColor.WHITE, 4, 3);
    piece4 = new Piece(PieceColor.WHITE, 3, 4);
  }
  
  @Test
  public void testPieceColor() {
    Piece testPiece = new Piece();
    
    assertEquals(PieceColor.BLACK, testPiece.getColor());
    testPiece.setColor(PieceColor.WHITE);
    assertEquals(PieceColor.WHITE, testPiece.getColor());
    
    assertEquals(PieceColor.BLACK, piece1.getColor());
    assertEquals(PieceColor.WHITE, piece3.getColor());
  }
  
  public void testLocation() {
    Piece testPiece = new Piece(PieceColor.BLACK);
    
    assertEquals(0, testPiece.getX());
    assertEquals(0, testPiece.getY());
    
    testPiece.setLocation(2, 2);
    
    assertEquals(2, testPiece.getX());
    assertEquals(2, testPiece.getY());
  }
  
  public void testGetEmptyNeighbors() {
    testBoard.addPiece(piece1);
    testBoard.addPiece(piece2);
    testBoard.addPiece(piece3);
    testBoard.addPiece(piece4);
    
    ArrayList<Piece> emptySpots = piece1.getEmptyNeighbors(testBoard);
    assertEquals(5, emptySpots.size());
  }
  
  public void testGetOpposingNeighbors() {
    Piece testPiece = new Piece(PieceColor.BLACK, 2, 4);
    
    testBoard.addPiece(piece1);
    testBoard.addPiece(piece2);
    testBoard.addPiece(piece3);
    testBoard.addPiece(piece4);
    
    ArrayList<Piece> emptySpots = testPiece.getOpposingNeighbors(testBoard);
    assertEquals(1, emptySpots.size());
    assertEquals(piece3, emptySpots.get(0));
  }
  
  public void testGetCapturedPieces() {
    Piece testPiece = new Piece(PieceColor.BLACK, 2, 4);
    
    testBoard.addPiece(piece1);
    testBoard.addPiece(piece2);
    testBoard.addPiece(piece3);
    testBoard.addPiece(piece4);
    
    ArrayList<Piece> capturedPieces = testPiece.getCapturedPieces(testBoard);
    assertEquals(1, capturedPieces.size());
    assertEquals(piece3, capturedPieces.get(0));
  }
  
  public void testEquals() {
    Piece testPiece = new Piece(PieceColor.BLACK, 3, 3);
    
    assertEquals(true, testPiece.equals(piece1));
    testPiece.setColor(PieceColor.WHITE);
    assertEquals(false, testPiece.equals(piece1));
  }

}
