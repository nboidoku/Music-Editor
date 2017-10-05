package cs3500.music.controller;

import java.awt.event.KeyEvent;

import javax.swing.*;

import cs3500.music.model.IMusicEditor;
import cs3500.music.model.Note;
import cs3500.music.model.Pitch;
import cs3500.music.view.GuiView;


/**
 * GuiController.
 */
public class GUIController implements IController {
  private GuiView view;
  private IMusicEditor model;

  public GUIController(GuiView view, IMusicEditor model) {
    this.model = model;
    this.view = view;
  }

  /**
   * Returns the note based on the text fields;
   *
   * @return the new note.
   */
  private Note getNote() {
    JTextField start = view.getTextfields()[0];
    JTextField duration = view.getTextfields()[1];
    JTextField volume = view.getTextfields()[2];
    JTextField instrument = view.getTextfields()[3];
    JComboBox pitchList = view.getComboBox();
    int x1 = Integer.parseInt(start.getText());
    int x2 = Integer.parseInt(duration.getText());
    int x3 = Integer.parseInt(volume.getText());
    int x4 = Integer.parseInt(instrument.getText());
    if (x3 < 0 || x4 < 0 || x1 < 0 || x2 < 0 || x1 + x2 > this.model.lengthOfMusic() ||
            x2 > this.model.lengthOfMusic()) {
      throw new IllegalArgumentException("Negative values or " +
              "the duration is longer than the current" +
              " max duration");
    }
    String s = String.valueOf(pitchList.getSelectedItem());
    int octave = 0;

    switch (s.substring(0, 1)) {
      case "C":
        if (s.substring(1, 2).equals("S")) {
          octave = Integer.parseInt(s.substring(2)) * 12 + 12;
          octave += 1;
        } else {
          octave = Integer.parseInt(s.substring(1)) * 12 + 12;
        }

        break;
      case "D":
        if (s.substring(1, 2).equals("S")) {
          octave = Integer.parseInt(s.substring(2)) * 12 + 12;
          octave += 3;
        } else {
          octave = Integer.parseInt(s.substring(1)) * 12 + 12;
          octave += 2;
        }
        break;

      case "E":

        octave = Integer.parseInt(s.substring(1)) * 12 + 12;
        octave += 4;

        break;

      case "F":
        if (s.substring(1, 2).equals("S")) {
          octave = Integer.parseInt(s.substring(2)) * 12 + 12;
          octave += 6;
        } else {
          octave = Integer.parseInt(s.substring(1)) * 12 + 12;
          octave += 5;
        }
        break;

      case "G":
        if (s.substring(1, 2).equals("S")) {
          octave = Integer.parseInt(s.substring(2)) * 12 + 12;
          octave += 8;
        } else {
          octave = Integer.parseInt(s.substring(1)) * 12 + 12;
          octave += 7;
        }
        break;

      case "A":
        if (s.substring(1, 2).equals("S")) {
          octave = Integer.parseInt(s.substring(2)) * 12 + 12;
          octave += 10;
        } else {
          octave = Integer.parseInt(s.substring(1)) * 12 + 12;
          octave += 9;
        }
        break;

      case "B":

        octave = Integer.parseInt(s.substring(1)) * 12 + 12;
        octave += 11;

        break;

      default:
        break;
    }

    return new Note(Pitch.intToPitch(octave), octave / 12, x1, x2, x3, x4);

  }

  /**
   * Executes the view.
   */

  public void execute() {
    this.view.addKeyListener(this.keyboardHandler);

    this.view.play();

    this.view.addMouseListener(this.mouseHandler);

    this.view.getButton()[0].addActionListener(this.actionHandler);

    this.view.getButton()[1].addActionListener(this.repeatHandler);

    this.view.getButton()[2].addActionListener(this.varyHandler);
  }


  /**
   * Lambda for scrolling up.
   */
  private final Runnable scrollUp = () -> view.scrollUp();


  /**
   * Lambda for scrolling down.
   */
  private Runnable scrollDown = () -> view.scrollDown();

  /**
   * Lambda for scrolling left.
   */
  private Runnable scrollRight = () -> view.scrollRight();

  /**
   * Lambda for scrolling right.
   */
  private Runnable scrollLeft = () -> view.scrollLeft();

  /**
   * Lambda for jumping to the end of the music.
   */
  private Runnable jumpToEnd = () -> view.toEnd();

  /**
   * Lambda for jumping to beginning of music.
   */
  private Runnable jumpToBeginning = () -> view.toBeginning();

  /**
   * Lambda for deleting the selected note.
   */
  private Runnable deleteNote = () -> {
    if (!view.pauseState()) {
      this.model.removeNote(this.view.getSelectedNote());
      this.view.refresh();
    }
  };

  /**
   * Re-adds the selected note.
   */
  private Runnable undoSelectedNoteDelete = () -> {
    if (this.view.getSelectedNote() != null && !view.pauseState()) {
      this.model.addNote(this.view.getSelectedNote());
      this.view.refresh();
    }
  };

  /**
   * Re-adds the selected note.
   */
  private Runnable startAndPause = () -> this.view.startAndPause();

  /**
   * Resets the line.
   */
  private Runnable reset = () -> this.view.reset();
  /**
   * Adds a new note to the model.
   */
  private Runnable addNewNote = () -> {
    if (!view.pauseState()) {
      this.model.addNote(this.getNote());
      this.view.refresh();

    }
  };

  /**
   * Adds a new repeat section to the model.
   */
  private Runnable addRepeatSection = () -> {
    if (!view.pauseState()) {
      this.view.addRepeat();

    }
  };

  /**
   * Adds a new vary section to the model.
   */
  private Runnable addVarySection = () -> {
    if (!view.pauseState()) {
      this.view.addVary();

    }
  };

  /**
   * Lambda for selecting a note.
   */
  private Runnable noteSelect = () -> {

    view.getNoteXY(this.mouseHandler.getMouseEvent().getX(),
            this.mouseHandler.getMouseEvent().getY());
  };


  private final ActionHandler actionHandler = new ActionHandler(addNewNote);
  private final ActionHandler repeatHandler = new ActionHandler(addRepeatSection);
  private final ActionHandler varyHandler = new ActionHandler(addVarySection);
  private final MouseHandler mouseHandler = new MouseHandler(noteSelect);


  /**
   * keyboard handler for the view
   */
  private final KeyboardHandler keyboardHandler = new KeyboardHandler.Builder()
          .addKeyPressed(KeyEvent.VK_UP, this.scrollUp)
          .addKeyPressed(KeyEvent.VK_DOWN, this.scrollDown)
          .addKeyPressed(KeyEvent.VK_RIGHT, this.scrollRight)
          .addKeyPressed(KeyEvent.VK_LEFT, this.scrollLeft)
          .addKeyPressed(KeyEvent.VK_2, this.jumpToEnd)
          .addKeyPressed(KeyEvent.VK_1, this.jumpToBeginning)
          .addKeyPressed(KeyEvent.VK_D, this.deleteNote)
          .addKeyPressed(KeyEvent.VK_A, this.undoSelectedNoteDelete)
          .addKeyPressed(KeyEvent.VK_P, this.startAndPause)
          .addKeyPressed(KeyEvent.VK_R, this.reset)
          .build();
}