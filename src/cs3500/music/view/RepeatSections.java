package cs3500.music.view;

import java.util.ArrayList;

/**
 * Represents a repeated section in the music.
 */
public class RepeatSections implements Section {

  private int startBeat;
  private int endBeat;
  private boolean played = false;

  /**
   * A repeat section in the music.
   * @param startBeat - the beat to start at.
   * @param endBeat - the beat to end at.
   */
  public RepeatSections(int startBeat, int endBeat) {
    this.startBeat = startBeat;
    this.endBeat = endBeat;
  }

  public int jumpTo(int end) {
    return this.startBeat;
  }

  public boolean isEnd(int end) {
    return this.endBeat == end;
  }

  public ArrayList<Integer> getEnd() {
    ArrayList<Integer> d = new ArrayList<>();
    d.add(this.endBeat);
    return d;
  }

  public int getStart() {
    return this.startBeat;
  }

  public boolean isBetween(int start, int end) {
    return !((startBeat >= start && startBeat >= end) || (endBeat <= start && endBeat <= end) );
  }

  public boolean isPlayed() {
    return played;
  }

  public void setPlayed(boolean played) {
    this.played = played;
  }

  public void reset() {
    this.setPlayed(false);

  }
}
