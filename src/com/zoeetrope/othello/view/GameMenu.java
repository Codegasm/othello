package com.zoeetrope.othello.view;

import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class GameMenu extends JMenu {
  
  private static final long serialVersionUID = 1L;
  
  public GameMenu(ActionListener listener) {
    super("Game");
    
    JMenuItem newGame = new JMenuItem("New game");
    newGame.setActionCommand("newGame");
    newGame.addActionListener(listener);
    add(newGame);
   
    JMenuItem quitGame = new JMenuItem("Quit");
    quitGame.setActionCommand("quitGame");
    quitGame.addActionListener(listener);
    add(quitGame);
  }

}
