package com.zoeetrope.othello.view;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import com.zoeetrope.othello.model.Othello;
import com.zoeetrope.othello.model.Player;

public class OthelloWindow extends JFrame implements Observer {

  private static final long serialVersionUID = -8981623483605994236L;
  
  private ArrayList<PlayerView> playerViews;
  private Othello othello;
  private BoardView boardView;
  
  public OthelloWindow(Othello othello) {
    this.othello = othello;
    this.playerViews = new ArrayList<PlayerView>();
    this.boardView = new BoardView(this.othello.getBoard());
    
    setSize(420, 550);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);
    getContentPane().setLayout(null);
    
    boardView.setBounds(10, 110, 400, 400);
    getContentPane().add(boardView);
  }

  @Override
  public void update(Observable o, Object arg) {
    System.out.println("OthelloWindow update");
    
    if(o instanceof Othello && arg instanceof Player) {
      System.out.println(((Player) arg).getName());
      addPlayerView((Player)arg);
    }
  }
  
  private void addPlayerView(Player player) {
    if(playerViews.size() < 2) {
      PlayerView view = new PlayerView(player);
      playerViews.add(view);
      
      if(playerViews.size() == 1) {
        view.setLocation(10, 10);
      } else {
        view.setLocation(220, 10);
      }
      
      getContentPane().add(view);
    }
  }

}
