package com.zoeetrope.othello.ai;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import com.zoeetrope.othello.model.GameState;
import com.zoeetrope.othello.model.Othello;
import com.zoeetrope.othello.model.Piece;
import com.zoeetrope.othello.model.Player;

public class RandomAI extends OthelloAI {

  public RandomAI(Player player, Othello othello) {
    super(player, othello);
  }

  @Override
  public void update(Observable o, Object arg) {
    if(this.model.getCurrentPlayer() == this.player && 
       this.model.getGameState() == GameState.DURING) {
      ArrayList<Piece> validMoves = this.model.getBoard()
          .getValidMoves(this.player.getColor());
      Random r = new Random();
      int randomMove = r.nextInt(validMoves.size()-1);
      
      this.model.getBoard().makeMove(validMoves.get(randomMove));
    }
  }

}
