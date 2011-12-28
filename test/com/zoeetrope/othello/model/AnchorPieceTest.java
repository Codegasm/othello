package com.zoeetrope.othello.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class AnchorPieceTest {
  
  private Othello testOthello;
  private Player p1;
  private Player p2;
  private Board testBoard;
  private Piece piece1;
  private Piece piece2;
  private Piece piece3;
  private Piece piece4;
  
  @Before
  public void setUp() {
    testOthello = new Othello();
    testBoard = testOthello.getBoard();
    
    piece1 = new Piece(PieceColor.BLACK, 3, 3);
    piece2 = new Piece(PieceColor.BLACK, 4, 4);
    
    piece3 = new Piece(PieceColor.WHITE, 4, 3);
    piece4 = new Piece(PieceColor.WHITE, 3, 4);
    
    p1 = new Player(PieceColor.BLACK, "Julia");
    p2 = new Player(PieceColor.WHITE, "Zoee");
    
    testOthello.addPlayer(p1);
    testOthello.addPlayer(p2);
    
    testBoard.addPiece(piece1);
    testBoard.addPiece(piece2);
    testBoard.addPiece(piece3);
    testBoard.addPiece(piece4);
    
    testOthello.startGame();
  }

  @Test
  public void testGetCapturedPieces() {
    Piece p = new Piece(PieceColor.BLACK, 2, 4);
    ArrayList<Piece> capturedPieces = p.getCapturedPieces(testBoard);
    
    assertEquals(1, capturedPieces.size());
    
    p = new Piece(PieceColor.BLACK, 2, 3);
    capturedPieces = p.getCapturedPieces(testBoard);
    assertEquals(0, capturedPieces.size());
  }

}
