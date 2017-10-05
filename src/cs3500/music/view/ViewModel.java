package cs3500.music.view;

import java.util.List;

import cs3500.music.model.IMusicEditor;
import cs3500.music.model.Note;

/**
 * A final for the views to access the model without being able to mutate it.
 */
public final class ViewModel implements IViewModel {

  private IMusicEditor model;

  public ViewModel(IMusicEditor model) {
    this.model = model;
  }

  @Override
  public int getLengthOfMusic() {
    return model.lengthOfMusic();
  }

  @Override
  public int getWidthOfMusic() {
    return model.musicRange();
  }

  @Override
  public Note getLeastNote() {
    return model.getLeastNote();
  }

  @Override
  public Note getHighestNote() {
    return model.getHighestNote();
  }

  @Override
  public List<Note> getMusicalValues() {
    return model.musicalValues();
  }

  @Override
  public boolean isEmpty() {
    try {
      model.musicRange();
      model.lengthOfMusic();
    } catch (IllegalArgumentException e) {
      return true;
    }
    return false;
  }

  @Override
  public int getTempo() {
    return this.model.getTempo();
  }

}
