package cs3500.music.view;

import java.util.List;

import cs3500.music.model.Note;

/**
 * ViewModel Interface for accessing the model.
 */
public interface IViewModel {


  int getLengthOfMusic();

  int getWidthOfMusic();

  Note getLeastNote();

  List<Note> getMusicalValues();

  boolean isEmpty();

  Note getHighestNote();

  int getTempo();
}
