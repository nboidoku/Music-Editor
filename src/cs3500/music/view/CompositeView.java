package cs3500.music.view;

/**
 * Composite view class.
 */
public class CompositeView extends MusicView {

  private MidiView midiView;

  /**
   * Plays the music in the given view.
   */
  @Override
  public void play() {
    super.play();
    midiView.play();
    midiView.stopSeq(this.midiView.getTickPosition());


  }

  /**
   * Starts and pauses the view as well the midi.
   */

  @Override
  public void startAndPause() {
    super.startAndPause();


    if (this.midiView.getSeqState()) {
      this.midiView.stopSeq(this.midiView.getTickPosition());
    } else {
      this.midiView.startSeq();

    }
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
          int jump = section.jumpTo(beatTemp);
          if (jump >= 0) {
            this.turtlePanel.setLineX(jump * turtlePanel.BEAT_SIZE + 50);
            this.midiView.setTickPosition(jump * 20);
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
   * Resets the view.
   */
  @Override
  public void reset() {
    super.reset();
    this.turtlePanel.updateToDraw(toRepeat);
    this.midiView.restartSeq();
    if (this.pauseState()) {
      this.midiView.startSeq();
    }
  }

  /**
   * Refreshes the view as to update any changes to the model.
   */
  @Override
  public void refresh() {
    super.refresh();
    long tempTick = this.midiView.getTickPosition();
    this.midiView = new MidiView(this.model, tempTick);
  }

  /**
   * Creates a composite view.
   */
  public CompositeView(IViewModel model) {
    super(model);

    midiView = new MidiView(model);

  }


}
