package cs3500.music.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


/**
 * Class for MouseHandler.
 */
class MouseHandler implements MouseListener {
  private Runnable leftButton;
  private MouseEvent mouseEvent;

  /**
   * Returns the most recent mouseEvent.
   *
   * @return a mouse event.
   */
  MouseEvent getMouseEvent() {
    return this.mouseEvent;
  }

  public MouseHandler(Runnable leftButton) {

    this.leftButton = leftButton;
  }

  /**
   * Handles the mouse clicked event.
   *
   * @param e a mouse event.
   */
  @Override
  public void mouseClicked(MouseEvent e) {
    // This has to kept because of the implementation.
  }

  /**
   * Handles the mouse pressed event.
   *
   * @param e a mouse event.
   */
  @Override
  public void mousePressed(MouseEvent e) {
    if (e.getButton() == MouseEvent.BUTTON1) {
      this.mouseEvent = e;
      leftButton.run();
    }
  }

  /**
   * Handles the mouse released event.
   *
   * @param e a mouse event.
   */
  @Override
  public void mouseReleased(MouseEvent e) {
    // This has to kept because of the implementation.

  }

  /**
   * Handles the mouse entered event.
   *
   * @param e a mouse event.
   */
  @Override
  public void mouseEntered(MouseEvent e) {
    // This has to kept because of the implementation.

  }

  /**
   * Handles the mouse exited event.
   *
   * @param e a mouse event.
   */
  @Override
  public void mouseExited(MouseEvent e) {
    // This has to kept because of the implementation.

  }


}
