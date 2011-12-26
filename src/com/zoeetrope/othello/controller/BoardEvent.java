package com.zoeetrope.othello.controller;

import java.awt.event.ActionEvent;

public class BoardEvent extends ActionEvent {

  private static final long serialVersionUID = 7497664073711875493L;

  public BoardEvent(Object source, int id, String command) {
    super(source, id, command);
  }

}
