package cs3500.music.view;



import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JPanel;


import cs3500.music.model.Note;
import cs3500.music.model.Pitch;


public class MusicPanel extends JPanel {
  public final int NOTE_SIZE = 20;
  public final int BEAT_SIZE = 10;
  IViewModel model;
  private int noteRange;
  private int beatRange;
  private boolean line;
  public int lineX;
  protected Note selectedNote = null;
  private boolean lineMove;
  private ArrayList<Section> toDraw = new ArrayList<>();

  /**
   * Adds the beat strings to the graphics.
   *
   * @param g the current graphics.
   * @return the graphics after the update.
   */
  protected Graphics2D addBeatStrings(Graphics2D g) {
    int counter = 0;
    for (int x = model.getLeastNote().getPitchOctave(); x <= model.getWidthOfMusic()
            + model.getLeastNote().getPitchOctave(); x++) {
      g.drawString(Pitch.toString(x % 12) + ((x / 12) - 1), 15, (100 + NOTE_SIZE) / 2 + counter *
              NOTE_SIZE);
      counter++;
    }
    return g;
  }

  /**
   * Adds the measures to the graphics.
   *
   * @param g the current graphics.
   * @return the graphics after the update.
   */
  protected Graphics2D addMeasures(Graphics2D g) {
    for (int x = 0; x <= beatRange / 4; x++) {
      g.drawString("" + x * 4, 50 + x * 4 * BEAT_SIZE, 45);
    }

    return g;
  }

  /**
   * Adds all the notes to the graphic.
   *
   * @param g the current graphics.
   * @return the graphics after the update.
   */
  protected Graphics2D addAllNote(Graphics2D g) {
    for (Note note : model.getMusicalValues()) {
      if (note.equals(selectedNote)) {
        g = this.addSpecialNote(g, note.getPitchOctave() - model.getLeastNote().getPitchOctave(),
                note.getStartBeat(), note.endsAtBeat());
      } else {
        g = this.addNote(g, note.getPitchOctave() - model.getLeastNote().getPitchOctave(),
                note.getStartBeat(), note.endsAtBeat());
      }

    }
    return g;
  }

  /**
   * Adds an individual note to the graphic.
   *
   * @param g the current graphics.
   * @return the graphics after the update.
   */
  protected Graphics2D addNote(Graphics2D g, int note, int start, int end) {
    g.setColor(Color.red);
    g.fill(new Rectangle(50 + BEAT_SIZE * start, 50 + note * NOTE_SIZE, BEAT_SIZE, NOTE_SIZE));
    g.setColor(Color.cyan);
    g.fill(new Rectangle(50 + BEAT_SIZE + BEAT_SIZE * start,
            50 + note * NOTE_SIZE, (end - start - 1) * BEAT_SIZE, NOTE_SIZE));


    return g;
  }

  /**
   * Adds the selected note to the graphic.
   *
   * @param g the current graphics.
   * @return the graphics after the update.
   */
  protected Graphics2D addSpecialNote(Graphics2D g, int note, int start, int end) {
    g.setColor(Color.black);
    g.fill(new Rectangle(50 + BEAT_SIZE * start, 50 + note * NOTE_SIZE, BEAT_SIZE, NOTE_SIZE));
    g.setColor(Color.green);
    g.fill(new Rectangle(50 + BEAT_SIZE + BEAT_SIZE * start,
            50 + note * NOTE_SIZE, (end - start - 1) * BEAT_SIZE, NOTE_SIZE));


    return g;
  }

  /**
   * Draws the red line to the graphic.
   *
   * @param g the current graphics.
   * @return the graphics after the update.
   */
  protected Graphics2D drawLine(Graphics2D g) {
    g.setColor(Color.red);
    g.drawLine(lineX, 50, lineX, this.getNoteRange() * this.NOTE_SIZE + 50);
    g.setColor(Color.black);
    return g;
  }

  /**
   * Gets the lineMove boolean;
   *
   * @return the boolean representing whether the line is moving.
   */
  protected boolean getLineMove() {
    return this.lineMove;
  }

  /**
   * Resets the line's x coordinate.
   */
  protected void resetLineX() {
    this.lineX = 50;
  }

  /**
   * Adds a number to the line's x coordinate.
   *
   * @param line the number to be added.
   * @return the current x coordinate.
   */
  protected int addLineX(int line) {
    if (lineX < this.getBeatRange() * this.BEAT_SIZE + 50) {
      this.lineX += line;
      return lineX;
    }
    return lineX;
  }

  /**
   * Sets a number to the line's x coordinate.
   *
   * @param line the number to be set.
   */
  protected void setLineX(int line) {
    this.lineX = line;
  }

  /**
   * Stops or starts the line depending on its current state.
   */
  protected void setLineMove() {
    this.lineMove = !(lineMove);
  }

  /**
   * Gets the note range of the view.
   *
   * @return an int representing note range.
   */
  public int getNoteRange() {
    return noteRange;
  }

  /**
   * Gets the beatrange of the view.
   *
   * @return an int representing beat range.
   */
  public int getBeatRange() {
    return beatRange;
  }

  /**
   * Draws the panel to the view.
   *
   * @return the graphics representing the panel.
   */
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2 = (Graphics2D) g;
    g2.setStroke(new BasicStroke(3));


    g2 = this.addMeasures(g2);
    g2 = this.addBeatStrings(g2);
    g2 = this.addAllNote(g2);
    g2.setColor(Color.BLACK);


    //Draws the vertical lines
    for (int x = 0; x < (beatRange / 4) + 1; x++) {
      g.drawLine(50 + 4 * BEAT_SIZE * x,
              50, 50 + 4 * BEAT_SIZE * x, 50 + noteRange * NOTE_SIZE);

    }


    // Draws the horizontal lines
    for (int x = 0; x < noteRange + 1; x++) {
      g.drawLine(50, 50 + NOTE_SIZE * x, 50 + beatRange * BEAT_SIZE, 50 + NOTE_SIZE * x);
    }
    if (line) {
      g2 = this.drawLine(g2);
    }

    for (Section sections : this.toDraw) {
      if (sections instanceof VariedSections) {
        g2.setColor(Color.RED);
        g.drawLine(sections.getStart() * 10 + 50, 25, sections.getStart() * 10 + 50,
                this.getNoteRange() * this.NOTE_SIZE + 50);
        int prevSection = 0;
        int sectionNum = 0;
        for (int toDraw : sections.getEnd()) {
          g.drawLine(toDraw * 10 + 50, 25, toDraw * 10 + 50, this.getNoteRange() *
                  this.NOTE_SIZE + 500);
          if (sectionNum != 0) {
            ((Graphics2D) g).drawString("" + sectionNum,
                    ((toDraw + prevSection) / 2) * 10 + 50, 20);
          }
          g.drawLine(sections.getStart() * 10 + 50, 25, toDraw * 10 + 50, 25);
          prevSection = toDraw;
          sectionNum++;
        }
        g.drawLine(prevSection * 10 + 50, 25, prevSection * 10 + 100, 25);

      } else {
        g2.setColor(Color.BLUE);
        g.drawLine(sections.getStart() * 10 + 50, 25, sections.getStart() * 10 + 50,
                this.getNoteRange() * this.NOTE_SIZE + 50);
        for (int toDraw : sections.getEnd()) {
          g.drawLine(toDraw * 10 + 50, 25, toDraw * 10 + 50, this.getNoteRange() *
                  this.NOTE_SIZE + 500);
          g.drawLine(sections.getStart() * 10 + 50, 25, toDraw * 10 + 50, 25);
        }
      }
    }


  }

  public void updateToDraw(ArrayList<Section> toDraw) {
    this.toDraw = toDraw;
  }

  /**
   * Constructor for the MusicPanel.
   *
   * @param model the viewmodel
   */
  public MusicPanel(IViewModel model) {
    this.model = model;
    this.line = true;
    this.lineX = 50;
    this.noteRange = model.getWidthOfMusic() + 1;
    this.lineMove = false;
    this.beatRange = model.getLengthOfMusic();
    if (beatRange % 4 != 0) {
      beatRange = 4 * ((beatRange / 4) + 1);
    }

  }


}