package cs3500.music.view;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import cs3500.music.model.Note;

/**
 * Sub interface of the IView to allow for GUI editing.
 */
public interface GuiView extends IView {

  /**
   * Adds a keyListener to the object.
   *
   * @param keyListener the keyListener to be added
   */
  void addKeyListener(KeyListener keyListener);

  /**
   * Adds a mouse Listener to the object.
   *
   * @param mouseListener the mouseListener to be added
   */
  void addMouseListener(MouseListener mouseListener);

  /**
   * Removes a mouse Listener from the object.
   *
   * @param mouseListener mouse Listener to be removed.
   */
  void removeMouseListener(MouseListener mouseListener);

  /**
   * Scrolls Up.
   */
  void scrollUp();

  /**
   * Scrolls down.
   */
  void scrollDown();

  /**
   * Scrolls Left.
   */
  void scrollLeft();

  /**
   * Scrolls Right.
   */
  void scrollRight();

  /**
   * Jumps to the end of the piece.
   */
  void toEnd();

  /**
   * Jumps to the beginning of the piece.
   */
  void toBeginning();

  /**
   * Selects a note based on coordinates.
   */
  void getNoteXY(int x, int y);

  /**
   * Gets the selected note.
   */
  Note getSelectedNote();

  /**
   * Repaints the view().
   */
  void refresh();

  /**
   * Pauses and starts the line.
   */
  void startAndPause();

  /**
   * Resets the piece.
   */
  void reset();

  /**
   * Returns the state of whether the piece is paused.
   */
  boolean pauseState();

  /**
   * Returns the view's button.
   */
  JButton[] getButton();


  /**
   * Returns am array of textfields.
   *
   * @return an array of textfields.
   */
  JTextField[] getTextfields();

  /**
   * Returns the combo box for the pitch and octave.
   *
   * @return the combo box.
   */
  JComboBox getComboBox();

  /**
   * Resets the focus to the panel.
   */
  void setPanelFocusable();


  /**
   * add repeat section to the editor
   */
  void addRepeat();

  /**
   * add a vary section to the editor
   */
  void addVary();
}
