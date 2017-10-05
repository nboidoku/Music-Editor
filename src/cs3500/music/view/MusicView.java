package cs3500.music.view;


import java.awt.BorderLayout;
import java.awt.Dimension;

import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Scanner;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JLabel;

import cs3500.music.model.Note;
import cs3500.music.model.Pitch;

/**
 * This is an implementation of the IView interface
 * that uses Java Swing to draw the results of the
 * model.
 */
public class MusicView extends JFrame implements GuiView, Runnable {
  protected int xMax = 1000;
  protected IViewModel model;
  protected JScrollPane scrollPane = null;
  protected MusicPanel turtlePanel;
  protected JButton accept = new JButton("Add");
  protected JButton repeat = new JButton("Repeat");
  protected JButton vary = new JButton("Vary");
  protected JTextField varyStart = null;
  protected JTextField varyJump = null;
  protected JTextField varyEnd = null;
  protected JTextField repeatStart = null;
  protected JTextField repeatEnd = null;
  protected JTextField startTime = null;
  protected JTextField duration = null;
  protected JTextField volume = null;
  protected JTextField instrument = null;
  protected JComboBox<String> pitchList = null;
  protected ArrayList<Section> toRepeat;


  public MusicView(IViewModel model) {
    super();
    this.model = model;
    turtlePanel = new MusicPanel(model);
  }

  /**
   * Increments the line's position for the music view.
   */
  @Override
  public void run() {

    while (turtlePanel.getLineMove()) {
      try {
        Thread.sleep(model.getTempo() / 10000);
      } catch (InterruptedException e) {
        System.out.println("interrupted");
      }
      int temp = this.turtlePanel.addLineX(1);
      int beatTemp = (temp - 50) / turtlePanel.BEAT_SIZE;
      for (Section section : this.toRepeat) {

        if (section.isEnd(beatTemp) && !section.isPlayed()) {
          if (section.jumpTo(beatTemp) != -1) {
            this.turtlePanel.setLineX(section.jumpTo(beatTemp) * turtlePanel.BEAT_SIZE + 50);
            section.setPlayed(true);
          }
        }
      }
      temp = this.turtlePanel.lineX - scrollPane.getHorizontalScrollBar().getValue();


      while (temp < 0) {

        this.scrollLeft();
        temp = this.turtlePanel.lineX - scrollPane.getHorizontalScrollBar().getValue();
      }
      while (temp > xMax) {

        this.scrollRight();
        temp = this.turtlePanel.lineX - scrollPane.getHorizontalScrollBar().getValue();

      }


      repaint();
    }
  }

  /**
   * Resets the focus to the panel.
   */
  @Override
  public void setPanelFocusable() {
    this.accept.setFocusable(false);
    this.duration.setFocusable(false);
    this.instrument.setFocusable(false);
    this.volume.setFocusable(false);
    this.startTime.setFocusable(false);
    this.repeatStart.setFocusable(false);
    this.repeatEnd.setFocusable(false);
    this.repeat.setFocusable(false);
    this.vary.setFocusable(false);
    this.varyJump.setFocusable(false);
    this.varyStart.setFocusable(false);
    this.varyEnd.setFocusable(false);
    this.turtlePanel.setFocusable(true);

  }

  /**
   * Starts the thread.
   */
  protected void start() {

    Thread thread = new Thread(this);
    thread.start();
  }

  /**
   * Plays the music in the given view.
   */
  @Override
  public void play() {

    this.setTitle("Nii Boi Music Model");

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //use a borderlayout with drawing panel in center and button panel in south
    this.setLayout(new BorderLayout());

    turtlePanel.setPreferredSize(
            new Dimension(turtlePanel.getBeatRange() * turtlePanel.BEAT_SIZE + 100,
                    turtlePanel.getNoteRange() * turtlePanel.NOTE_SIZE + 70));


    scrollPane = new JScrollPane(turtlePanel);
    scrollPane.setPreferredSize(
            new Dimension(xMax, 650));


    this.add(scrollPane, BorderLayout.CENTER);


    JPanel addPanel = new JPanel();


    startTime = new JTextField("50");

    duration = new JTextField("1000");

    volume = new JTextField("127");
    instrument = new JTextField("1");
    repeatStart = new JTextField("000");
    repeatEnd = new JTextField("000");
    varyStart = new JTextField("000");
    varyJump = new JTextField("000");
    varyEnd = new JTextField("000");


    String[] pitches = new String[this.model.getHighestNote().getPitchOctave() -
            this.model.getLeastNote().getPitchOctave() + 1];

    for (int xx = 0; xx < pitches.length; xx++) {
      pitches[xx] = "" + Pitch.intToPitch(this.model.getLeastNote().getPitchOctave() + xx) +
              (((this.model.getLeastNote().getPitchOctave() + xx) / 12) - 1);
    }
    pitchList = new JComboBox<>(pitches);

    accept = new JButton("Add");


    addPanel.add(pitchList);
    addPanel.add(new JLabel("Start"));
    addPanel.add(startTime);
    addPanel.add(new JLabel("Duration"));
    addPanel.add(duration);
    addPanel.add(new JLabel("Volume"));
    addPanel.add(volume);
    addPanel.add(new JLabel("Instrument"));
    addPanel.add(instrument);
    addPanel.add(accept);
    addPanel.add(new JLabel("Repeat Start"));
    addPanel.add(repeatStart);
    addPanel.add(new JLabel("Repeat End"));
    addPanel.add(repeatEnd);
    addPanel.add(repeat);
    addPanel.add(new JLabel("Vary Start"));
    addPanel.add(varyStart);
    addPanel.add(new JLabel("Vary Jump"));
    addPanel.add(varyJump);
    addPanel.add(new JLabel("Vary End"));
    addPanel.add(varyEnd);
    addPanel.add(vary);


    this.add(addPanel, BorderLayout.SOUTH);

    toRepeat = new ArrayList<>();

    this.setFocusable(true);
    this.pack();
    this.setVisible(true);
    this.start();

  }

  /**
   * Adds mouseListener.
   *
   * @param mouseListener the mouseListener to be added
   */
  @Override
  public void addMouseListener(MouseListener mouseListener) {
    this.turtlePanel.addMouseListener(mouseListener);
  }

  /**
   * Removes mouseListener.
   *
   * @param mouseListener the mouseListener to be removed
   */
  @Override
  public void removeMouseListener(MouseListener mouseListener) {
    this.turtlePanel.removeMouseListener(mouseListener);
  }

  /**
   * Scrolls the scrollPane up.
   */
  @Override
  public void scrollUp() {
    this.scrollPane.getVerticalScrollBar().setValue(this.scrollPane.getVerticalScrollBar()
            .getValue() -
            this.scrollPane.getVerticalScrollBar().getUnitIncrement());
  }

  /**
   * Scrolls the scrollPane down.
   */
  @Override
  public void scrollDown() {
    this.scrollPane.getVerticalScrollBar().setValue(this.scrollPane.getVerticalScrollBar()
            .getValue() +
            this.scrollPane.getVerticalScrollBar().getUnitIncrement());

  }

  /**
   * Scrolls the scrollPane left.
   */
  @Override
  public void scrollLeft() {
    this.scrollPane.getHorizontalScrollBar().setValue(this.scrollPane.getHorizontalScrollBar()
            .getValue() -
            xMax);
  }

  /**
   * Scrolls the scrollPane right.
   */
  @Override
  public void scrollRight() {
    this.scrollPane.getHorizontalScrollBar().setValue(this.scrollPane.getHorizontalScrollBar()
            .getValue() +
            xMax);
  }

  /**
   * Jumps to the end of the view.
   */
  @Override
  public void toEnd() {
    this.scrollPane.getHorizontalScrollBar().setValue(model.getLengthOfMusic() * xMax);
  }

  /**
   * Jumps to the end of the view.
   */
  @Override
  public void toBeginning() {
    this.scrollPane.getHorizontalScrollBar().setValue(0);

  }

  /**
   * Selects a note in the view based on the mouse coordinate.
   *
   * @param x the x coordinate of the note.
   * @param y the y coordinate of the note.
   */
  @Override
  public void getNoteXY(int x, int y) {
    if (x >= 50 || y >= 50 || x < this.scrollPane.getPreferredSize().getWidth() ||
            y < this.scrollPane.getPreferredSize().getHeight()) {
      this.setPanelFocusable();
      int lastNote = model.getLeastNote().getPitchOctave();
      x = (x - 50) / 10;
      y = (y - 50) / 20;

      y += lastNote;
      for (Note note : model.getMusicalValues()) {
        if (y == note.getPitchOctave() &&
                x >= note.getStartBeat() && x <= note.endsAtBeat()) {

          this.turtlePanel.selectedNote = note;
          this.turtlePanel.repaint();
        }
      }

    }
    this.accept.setFocusable(true);
    this.duration.setFocusable(true);
    this.instrument.setFocusable(true);
    this.volume.setFocusable(true);
    this.startTime.setFocusable(true);
    this.repeatStart.setFocusable(true);
    this.repeatEnd.setFocusable(true);
    this.repeat.setFocusable(true);
    this.vary.setFocusable(true);
    this.varyEnd.setFocusable(true);
    this.varyStart.setFocusable(true);
    this.varyJump.setFocusable(true);
  }

  /**
   * Returns am array of textfields.
   *
   * @return an array of textfields.
   */
  @Override
  public JTextField[] getTextfields() {
    JTextField[] x = new JTextField[4];
    x[0] = this.startTime;
    x[1] = this.duration;
    x[2] = this.volume;
    x[3] = this.instrument;
    return x;
  }

  /**
   * Returns the combo box for the pitch and octave.
   *
   * @return the combo box.
   */

  @Override
  public JComboBox getComboBox() {
    return this.pitchList;
  }

  /**
   * Returns the currently selected note in the turtlePanel.
   *
   * @return the currently selected note.
   */

  @Override
  public Note getSelectedNote() {
    return this.turtlePanel.selectedNote;
  }

  /**
   * Starts and pauses the view.
   */
  @Override
  public void startAndPause() {
    this.turtlePanel.setLineMove();

    this.start();

  }

  /**
   * Resets the view.
   */
  @Override
  public void reset() {
    for (Section section : this.toRepeat) {
      section.reset();
    }
    this.turtlePanel.resetLineX();
    this.scrollPane.getHorizontalScrollBar().setValue(0);
    this.repaint();
  }

  /**
   * Refreshes the view as to update any changes to the model.
   */
  @Override
  public void refresh() {
    this.repaint();
    this.scrollPane.setFocusable(true);
  }


  /**
   * Gets the array of buttons.
   */
  @Override
  public JButton[] getButton() {
    JButton[] x = new JButton[3];
    x[0] = this.accept;
    x[1] = this.repeat;
    x[2] = this.vary;
    return x;
  }


  /**
   * Returns the pause state of the view.
   *
   * @return the boolean representing the pause state.
   */
  @Override
  public boolean pauseState() {
    return this.turtlePanel.getLineMove();
  }

  @Override
  public void addRepeat() {
    int start = Integer.parseInt(repeatStart.getText());
    int end = Integer.parseInt(repeatEnd.getText());
    this.addToRepeat(start, end);


  }


  private void addToRepeat(int start, int end) {
    for (Section section : this.toRepeat) {
      if (section.isBetween(start, end) || start < 0 ||
              end < 0 || end <= start || start > this.model.getLengthOfMusic() ||
              end > this.model.getLengthOfMusic()) {
        throw new IllegalArgumentException("That section is invalid (either too large , negatve" +
                "or there exists an overlap in that section.");
      }


    }
    this.toRepeat.add(new RepeatSections(start, end));
    this.turtlePanel.updateToDraw(this.toRepeat);
    this.repaint();
  }

  @Override
  public void addVary() {
    int start = Integer.parseInt(varyStart.getText());
    int jump = Integer.parseInt(varyJump.getText());
    Scanner s = new Scanner(varyEnd.getText());
    ArrayList<Integer> toAdd = new ArrayList<>();
    while (s.hasNextInt()) {
      toAdd.add(s.nextInt());
    }
    this.addToVary(start, jump, toAdd);


  }

  public void addToVary(int start, int toJump, ArrayList<Integer> end) {
    int lastInt = 0;
    if (end.size() == 0) {
      throw new IllegalArgumentException("Please enter one or more endings");
    }
    for (int toAdd : end) {
      for (Section section : this.toRepeat) {

        if (start < 0 || toAdd < 0 || toJump < 0) {
          throw new IllegalArgumentException("No Negatives numbers!");
        }
        if (start > this.model.getLengthOfMusic() || toJump > this.model.getLengthOfMusic() ||
                toAdd > this.model.getLengthOfMusic()) {
          throw new IllegalArgumentException("That beat is out of range!");
        }
        if (toAdd <= start || toAdd <= lastInt || toJump <= start || toAdd <= toJump) {
          throw new IllegalArgumentException
                  ("Each subsequent section must be smaller than the last.");
        }
        if (section.isBetween(start, toAdd)) {

          throw new IllegalArgumentException("There exists an overlap in that section!");

        }
        lastInt = toAdd;
      }
    }

    ArrayList<RepeatSections> sectionsToAdd = new ArrayList<>();
    for (int toAdd1 : end) {

      sectionsToAdd.add(new RepeatSections(start, toAdd1));
    }

    this.toRepeat.add(new VariedSections(sectionsToAdd, start, toJump));
    this.turtlePanel.updateToDraw(this.toRepeat);
    this.repaint();
  }


}


