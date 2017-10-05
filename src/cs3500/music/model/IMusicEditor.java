package cs3500.music.model;

import java.util.List;

/**
 * Interface for the Music Editor.
 */
public interface IMusicEditor {

  /**
   * Adds a note to the Music Editor.
   *
   * @param note the note to be added
   */
  void addNote(Note note);

  /**
   * Removes a note from the Music Editor.
   *
   * @param note the note to be removed
   */
  void removeNote(Note note);

  /**
   * Merges another Music Editor with this Music Editor to be played simultaneously.
   *
   * @param musicToJoin the music editor to be merged with this
   */
  void mergeMusic(IMusicEditor musicToJoin);

  /**
   * Appends another Music Editor to this Music Editor to be played consecutively.
   *
   * @param musicToAppend the music editor to be appended to this
   */
  void appendMusic(IMusicEditor musicToAppend);

  /**
   * Finds the total length of the music piece.
   *
   * @return an int for the last length of the music piece.
   */
  int lengthOfMusic();

  /**
   * Gets the least note in this music model.
   * @return the least note in the model.
   */
  Note getLeastNote();

  /**
   * Returns the range of music values in this music editor.
   *
   * @return the range as an int
   */
  int musicRange();

  /**
   * Returns the set of this music values in order of duration.
   *
   * @return the List of these music values.
   */
  List<Note> musicalValues();


  /**
   * Gets the tempo of the MusicEditor.
   * @return the tempo of the Music Editor.
   */
  int getTempo();

  /**
   * Gets the highest note in this music editor.
   *
   * @return the highest note
   */
  Note getHighestNote();

}
