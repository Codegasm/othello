package com.zoeetrope.othello.view;

import java.awt.event.ActionListener;

import javax.swing.JMenuBar;

public class OthelloMenuBar extends JMenuBar {

  private static final long serialVersionUID = 5730753927853368911L;
  
  public OthelloMenuBar(ActionListener listener) {
    add(new GameMenu(listener));
  }
  
}
