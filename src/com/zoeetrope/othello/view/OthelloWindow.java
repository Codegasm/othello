package com.zoeetrope.othello.view;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

import com.zoeetrope.othello.model.Othello;
import com.zoeetrope.othello.model.Player;

public class OthelloWindow extends JFrame implements Observer {

  private static final long serialVersionUID = -8981623483605994236L;
  
  private ArrayList<PlayerView> playerViews;
  private Othello othello;
  private BoardView boardView;
  
  public OthelloWindow(Othello othello, ActionListener listener) {
    super("Othello");
    
    this.othello = othello;
    this.playerViews = new ArrayList<PlayerView>();
    this.boardView = new BoardView(this.othello.getBoard());
    
    setSize(420, 570);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);
    getContentPane().setLayout(null);
    
    JMenuBar menuBar = new OthelloMenuBar(listener);
    setJMenuBar(menuBar);
    
    boardView.setBounds(10, 110, 400, 400);
    boardView.addActionListener(listener);
    getContentPane().add(boardView);
    
    getRootPane().revalidate();
    getContentPane().repaint();
  }

  @Override
  public void update(Observable o, Object arg) {
    if(o instanceof Othello && arg instanceof Player) {
      addPlayerView((Player)arg);
    } else if(o instanceof Othello && arg instanceof String && arg.equals("clearGame")) {
      for(PlayerView pv : playerViews) {
        remove(pv);
      }
      
      playerViews.clear();
      validate();
    }
    
    for(PlayerView pv : playerViews) {
      pv.repaint();
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
