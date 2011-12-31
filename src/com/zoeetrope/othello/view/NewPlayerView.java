package com.zoeetrope.othello.view;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class NewPlayerView {
  
  JComponent[] inputs;
  private JTextField nameField = new JTextField();
  private JComboBox typeField;
  
  public NewPlayerView() {
    String[] availableTypes = {"Human", "Random CPU", "Impossible CPU"};
    typeField = new JComboBox(availableTypes);
    
    inputs = new JComponent[] {
        new JLabel("Name:"),
        nameField,
        new JLabel("Type:"),
        typeField
    };
  }
  
  public String getName() {
    return nameField.getText();
  }
  
  public String getType() {
    return (String)typeField.getSelectedItem();
  }
  
  public JComponent[] getInputs() {
    return inputs;
  }
  
}
