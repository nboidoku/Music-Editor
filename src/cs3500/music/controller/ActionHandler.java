package cs3500.music.controller;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;



public class ActionHandler implements ActionListener {


  Runnable addNote;


  public ActionHandler(Runnable addNote) {
    this.addNote = addNote;


  }


  /**
   * Performs the action when button is pressed.
   *
   * @param e the actionEvent.
   */
  public void actionPerformed(ActionEvent e) {

    addNote.run();

  }
}
