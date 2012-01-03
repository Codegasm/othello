package com.zoeetrope.othello.ai;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.TreeMap;

import com.zoeetrope.othello.model.Board;
import com.zoeetrope.othello.model.GameState;
import com.zoeetrope.othello.model.Othello;
import com.zoeetrope.othello.model.Piece;
import com.zoeetrope.othello.model.PieceColor;
import com.zoeetrope.othello.model.Player;

public class ImpossibleAI extends OthelloAI {
  
  private static int depth = 0;

  public ImpossibleAI(Player player, Othello othello) {
    super(player, othello);
  }
  
  @Override
  public void update(Observable o, Object arg) {
    if(this.model.getCurrentPlayer() == this.player && 
        this.model.getGameState() == GameState.DURING) {
      ArrayList<Piece> validMoves = this.model.getBoard()
          .getValidMoves(this.player.getColor());
      
      if(validMoves.size() > 0) {
        depth = 0;
        int moveIndex = getBestMove(this.model.getBoard().getDeepCopy(), validMoves);
        
        this.model.getBoard().makeMove(validMoves.get(moveIndex));
      }
    }
  }
  
  public int getBestMove(Board testBoard, ArrayList<Piece> moves) {
    int bestMove = 0;
    TreeMap<Double, Integer> ratios = new TreeMap<Double, Integer>();
    
    for(int i = 0; i < moves.size(); i++) {
      double result = testMove(testBoard, moves.get(i));
      if(ratios.get(result) == null) {
        ratios.put(result, i);
      }
    }
    
    Entry<Double, Integer> entry = ratios.lastEntry();
    
    if(entry != null) {
      bestMove = entry.getValue();
    }
    
    return bestMove;
  }
  
  public double testMove(Board testBoard, Piece move) {
    PieceColor myColor = this.player.getColor();
    PieceColor theirColor = myColor.getOpposite();
    testBoard = testBoard.getDeepCopy();
    ArrayList<Piece> validMoves = new ArrayList<Piece>();
    ArrayList<Piece> nextValidMoves = new ArrayList<Piece>();
    int maxDepth = 30;
    int movesLeft = (64 - (testBoard.getPieceCount(myColor) + testBoard.getPieceCount(theirColor))) / 2;
    
    if(maxDepth > movesLeft) {
      maxDepth = movesLeft - 1;
    }
    
    testBoard.makeMove(move);
    validMoves = testBoard.getValidMoves(move.getColor().getOpposite());
    nextValidMoves = testBoard.getValidMoves(move.getColor());
    
    if(validMoves.size() == 0 && nextValidMoves.size() > 0) {
      validMoves = nextValidMoves;
    }
    
    if(depth > maxDepth) {
      return (double)testBoard.getPieceCount(myColor) / (double)testBoard.getPieceCount(theirColor);
    }
    depth++;
    
    if(validMoves.size() > 0) {
      int bestMove = 0;
      
      if(validMoves.size() > 1) {
        bestMove = getBestMove(testBoard, validMoves);
      }
      
      return testMove(testBoard, validMoves.get(bestMove));
    } else {
      return (double)testBoard.getPieceCount(myColor) / (double)testBoard.getPieceCount(theirColor);
    }
  }

}
