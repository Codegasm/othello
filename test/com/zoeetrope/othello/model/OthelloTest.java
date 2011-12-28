package com.zoeetrope.othello.model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class OthelloTest {
  
  private Othello testOthello;
  private Player p1;
  private Player p2;
  
  @Before
  public void setUp() {
    p1 = new Player(PieceColor.BLACK, "Julia");
    p2 = new Player(PieceColor.WHITE, "Zoee");
    
    testOthello = new Othello();
    
    testOthello.addPlayer(p1);
    assertEquals(1, testOthello.getPlayers().size());
    
    testOthello.addPlayer(p2);
    assertEquals(2, testOthello.getPlayers().size());
    
    ArrayList<Player> players = testOthello.getPlayers();
    assertEquals(true, players.contains(p1));
    assertEquals(true, players.contains(p2));
  }

  @Test
  public void testGameState() {
    assertEquals(GameState.BEFORE, testOthello.getGameState());
    
    testOthello.startGame();
    assertEquals(GameState.DURING, testOthello.getGameState());
  }
  
  @Test
  public void testGetNextPiece() {
    assertEquals(null, testOthello.getNextPiece());
    
    testOthello.startGame();
    assertEquals(PieceColor.BLACK, testOthello.getNextPiece().getColor());
    
    testOthello.getBoard().makeMove(new Piece(PieceColor.BLACK, 2, 4));
    assertEquals(PieceColor.WHITE, testOthello.getNextPiece().getColor());
  }

}
