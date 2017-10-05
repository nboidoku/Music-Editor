package cs3500.music.controller;


import cs3500.music.view.IView;

/**
 * A controller for my Music Editor.
 */
public class Controller implements IController {
  private IView view;

  public Controller(IView view) {
    this.view = view;
  }


  /**
   * Executes the view.
   */
  public void execute() {
    this.view.play();
  }

}
