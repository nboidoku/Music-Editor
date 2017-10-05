package cs3500.music.view;

import java.util.ArrayList;

/**
 * Interface representing the sections.
 */
public interface Section {

  boolean isBetween(int start, int end);

  int jumpTo(int end);

  boolean isEnd(int end);

  ArrayList<Integer> getEnd();

  int getStart();

  void reset();

  void setPlayed(boolean played);

  boolean isPlayed();
}
