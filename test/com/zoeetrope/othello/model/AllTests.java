package com.zoeetrope.othello.model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
  PieceTest.class, BoardTest.class,
  PieceColorTest.class, PlayerTest.class,
  OthelloTest.class, AnchorPieceTest.class })

public class AllTests {

}
