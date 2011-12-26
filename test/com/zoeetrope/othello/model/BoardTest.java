package com.zoeetrope.othello.model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class BoardTest {
  
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
  public void testAddPiece() {
    ArrayList<Piece> pieces;
    
    // Test getPieceCount.
    assertEquals(0, testBoard.getPieceCount(PieceColor.BLACK));
    testBoard.addPiece(piece1);
    assertEquals(1, testBoard.getPieceCount(PieceColor.BLACK));
    testBoard.addPiece(piece2);
    assertEquals(2, testBoard.getPieceCount(PieceColor.BLACK));
    
    // Test getPieces.
    pieces = testBoard.getPieces(PieceColor.BLACK);
    assertEquals(2, pieces.size());
    for(Piece p : pieces) {
      assertEquals(PieceColor.BLACK, p.getColor());
    }
  }
  
  @Test
  public void testRemovePiece() {
    testBoard.addPiece(piece1);
    assertEquals(1, testBoard.getPieceCount(PieceColor.BLACK));
    assertEquals(piece1, testBoard.getPiece(piece1.getX(), piece1.getY()));
    
    testBoard.removePiece(piece1);
    assertEquals(0, testBoard.getPieceCount(PieceColor.BLACK));
    assertEquals(null, testBoard.getPiece(piece1.getX(), piece1.getY()));
  }
  
  @Test
  public void testMakeMove() {
    Piece testPiece = new Piece(PieceColor.BLACK, 2, 4);
    
    testBoard.addPiece(piece1);
    testBoard.addPiece(piece2);
    testBoard.addPiece(piece3);
    testBoard.addPiece(piece4);
    
    assertEquals(2, testBoard.getPieceCount(PieceColor.BLACK));
    testBoard.makeMove(testPiece);
    assertEquals(4, testBoard.getPieceCount(PieceColor.BLACK));
  }
  
  @Test
  public void testGetValidMoves() {
    testBoard.addPiece(piece1);
    testBoard.addPiece(piece2);
    testBoard.addPiece(piece3);
    testBoard.addPiece(piece4);
    
    ArrayList<Piece> validMoves = testBoard.getValidMoves(PieceColor.BLACK);
    assertEquals(4, validMoves.size());
    
    validMoves = testBoard.getValidMoves(PieceColor.WHITE);
    assertEquals(4, validMoves.size());
  }
  
  @Test 
  public void testIsPlaceFree() {
    assertEquals(true, testBoard.isPlaceFree(piece1.getX(), piece1.getY()));
    assertEquals(true, testBoard.isPlaceFree(piece1));
    
    testBoard.addPiece(piece1);
    assertEquals(false, testBoard.isPlaceFree(piece1.getX(), piece1.getY()));
    assertEquals(false, testBoard.isPlaceFree(piece1));
  }
  
  @Test
  public void testPlaceHasColor() {
    assertEquals(false, testBoard.placeHasColor(piece1.getX(), piece1.getY(), 
        piece1.getColor()));
    
    testBoard.addPiece(piece1);
    assertEquals(true, testBoard.placeHasColor(piece1.getX(), piece1.getY(), 
        piece1.getColor()));
  }

}
