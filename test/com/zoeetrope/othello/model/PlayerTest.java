package com.zoeetrope.othello.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerTest {

  @Test
  public void testName() {
    Player player = new Player(PieceColor.BLACK, "Julia");
    assertEquals("Julia", player.getName());
  }

}
