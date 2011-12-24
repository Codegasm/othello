package com.zoeetrope.othello.view;

import java.awt.Font;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import com.zoeetrope.othello.model.Player;

public class PlayerView extends JPanel implements Observer {

  private static final long serialVersionUID = -8150868056464069300L;
  private Player player;

  public PlayerView(Player player) {
    player.addObserver(this);
    this.player = player;
    
    setSize(200, 100);
    setVisible(true);
  }
  
  @Override
  public void update(Observable o, Object arg) {
    repaint();
  }
  
  @Override
  public void paint(Graphics g) {
    super.paint(g);
    
    int style = Font.PLAIN;
    
    if(player.isActive()) {
      style = Font.BOLD;
    }
    
    g.setFont(new Font("Arial", style, 12));
    g.drawString(player.getName(), 20, 20);
    g.drawString("Score: " + player.getScore(), 20, 35);
  }

  public Player getPlayer() {
    return player;
  }

}
