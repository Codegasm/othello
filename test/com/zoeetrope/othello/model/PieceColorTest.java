package com.zoeetrope.othello.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class PieceColorTest {

  @Test
  public void testOpposite() {
    PieceColor color = PieceColor.BLACK;
    
    assertEquals(PieceColor.WHITE, color.getOpposite());
  }

}
