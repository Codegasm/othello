package com.zoeetrope.othello.model;

import static org.junit.Assert.assertEquals;

import java.util.Observable;
import java.util.Observer;

import org.junit.Before;
import org.junit.Test;

public class PlayerTest {
  
  private Player testPlayer;
  
  @Before
  public void setUp() {
    testPlayer = new Player(PieceColor.BLACK, "Julia");
  }

  @Test
  public void testBase() {
    assertEquals("Julia", testPlayer.getName());
    assertEquals(PieceColor.BLACK, testPlayer.getColor());
  }
  
  @Test
  public void testScore() {
    assertEquals(0, testPlayer.getScore());
    
    testPlayer.addObserver(new Observer() {
      @Override
      public void update(Observable o, Object arg) {
        assertEquals(Player.class, o.getClass());
        assertEquals(2, ((Player)o).getScore());
      }
    });
    
    testPlayer.setScore(2);
  }
  
  @Test
  public void testActive() {
    assertEquals(false, testPlayer.isActive());
    
    testPlayer.addObserver(new Observer() {
      @Override
      public void update(Observable o, Object arg) {
        assertEquals(Player.class, o.getClass());
        assertEquals(true, ((Player)o).isActive());
      }
    });
    
    testPlayer.setActive(true);
  }

}
