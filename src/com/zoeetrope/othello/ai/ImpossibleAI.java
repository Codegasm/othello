package com.zoeetrope.othello.ai;

import java.util.ArrayList;
import java.util.Observable;

import com.zoeetrope.othello.model.Board;
import com.zoeetrope.othello.model.GameState;
import com.zoeetrope.othello.model.Othello;
import com.zoeetrope.othello.model.Piece;
import com.zoeetrope.othello.model.PieceColor;
import com.zoeetrope.othello.model.Player;

public class ImpossibleAI extends OthelloAI {

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
        int moveIndex = testMoves(this.model.getBoard().getDeepCopy(), validMoves, true);
        
        this.model.getBoard().makeMove(validMoves.get(moveIndex));
      }
    }
  }
  
  public int testMoves(Board testBoard, ArrayList<Piece> moves, Boolean topLevel) {
    int index = -2;
    PieceColor myColor = this.player.getColor();
    PieceColor theirColor = myColor.getOpposite();
    
    for(Piece move : moves) {
      testBoard = testBoard.getDeepCopy();
      
      testBoard.makeMove(move);
      int index2 = testMoves(testBoard, testBoard.getValidMoves(move.getColor().getOpposite()), false);
      
      if(index2 == -1 && !topLevel) {
        return -1;
      } else if(index2 == -1 && topLevel) {
        return moves.indexOf(move);
      }
      
      if(index2 >= -2) {
        index = moves.indexOf(move);
        break;
      }
    }
    
    if(moves.size() == 0 && testBoard.getPieceCount(myColor) > testBoard.getPieceCount(theirColor)) {
      index = -1;
//      System.out.println("found good end " + testBoard.getPieceCount(myColor) +"/"+ testBoard.getPieceCount(theirColor));
    } else if(moves.size() == 0) {
      index = -2;
//      System.out.println("found end " + testBoard.getPieceCount(myColor) +"/"+ testBoard.getPieceCount(theirColor));
    }
    
    return index;
  }

}
