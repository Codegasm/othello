package com.zoeetrope.othello.view;

import java.awt.event.ActionListener;
import java.util.ArrayList;

public abstract class GenericView {
  
  private ArrayList<ActionListener> listeners = new ArrayList<ActionListener>();
  
  public void addListener(ActionListener listener)
  {
     listeners.add(listener);
  }

  public void removeListener(ActionListener listener)
  {
     listeners.remove(listener);
  }
  
}
