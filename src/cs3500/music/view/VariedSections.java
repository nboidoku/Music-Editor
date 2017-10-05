package cs3500.music.view;

import java.util.ArrayList;


/**
 * Represents the varied section class.
 */
public class VariedSections implements Section {

  private ArrayList<RepeatSections> toRepeat;
  private int toStart;
  private int toJump;
  private int lastAccessedEnd;

  public VariedSections(ArrayList<RepeatSections> toRepeat, int toStart, int toJump) {
    this.toRepeat = toRepeat;

    this.toStart = toStart;
    this.toJump = toJump;
    this.lastAccessedEnd = toJump;


  }

  public boolean isBetween(int start, int end) {
    for (RepeatSections section : toRepeat) {
      if (!((start >= section.getEnd().get(0) && end >= section.getEnd().get(0)) ||
              (start <= section.getStart() && end <= section.getStart()))) {

        return true;
      }
    }

    return false;
  }

  public int getStart() {
    return this.toStart;
  }

  public ArrayList<Integer> getEnd() {
    ArrayList<Integer> d = new ArrayList<>();
    d.add(this.toJump);
    for (RepeatSections section : this.toRepeat) {
      d.add(section.getEnd().get(0));

    }
    return d;
  }

  public int jumpTo(int end) {
    if (end == this.toJump) {
      if (toJump == lastAccessedEnd) {
        return toJump;
      }
      return this.lastAccessedEnd;
    }
    for (RepeatSections section : toRepeat) {
      if (!section.isPlayed() && end == section.getEnd().get(0)) {
        section.setPlayed(true);
        this.lastAccessedEnd = section.getEnd().get(0);

        return this.toStart;
      }

    }


    return -1;
  }

  public boolean isEnd(int end) {

    if (end == this.toJump && lastAccessedEnd != toJump) {

      return true;
    }
    for (RepeatSections section : toRepeat) {
      if (!section.isPlayed() && end == section.getEnd().get(0)) {

        return true;
      }
    }
    return false;

  }


  public void reset() {

    for (RepeatSections section : toRepeat) {
      section.setPlayed(false);
    }

    lastAccessedEnd = toJump;

  }

  public void setPlayed(boolean played) {
    //Does nothing as jumpTo sets the played value
  }

  public boolean isPlayed() {
    return false;
    //This returns false always since the final jump pass this section
  }


}
