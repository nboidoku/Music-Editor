package cs3500.music.view;


import cs3500.music.model.Note;
import cs3500.music.model.Pitch;


/**
 * A console rendering of the model.
 */
public class ConsoleView implements IView {

  private IViewModel model;

  public ConsoleView(IViewModel model) {
    this.model = model;
  }

  /**
   * Renders a console view of the Music Model.
   */
  public void play() {
    if (model.isEmpty()) {
      System.out.println("Empty Editor");
    } else {
      int length = model.getLengthOfMusic();
      int width = model.getWidthOfMusic();
      Note least = model.getLeastNote();
      int leastPitch = least.getPitchOctave();
      int maxNumberTimeSpaces = Integer.toString(length).length();
      StringBuilder result = new StringBuilder(String.format("%" + maxNumberTimeSpaces + "s", ""));
      result = addNotes(createSpace(result, leastPitch, width), maxNumberTimeSpaces);

      System.out.print(result.toString());

    }
  }

  /**
   * Creates the space for the Notes.
   *
   * @param result     the StringBuilder
   * @param leastPitch the least pitch
   * @param width      the width of the music
   * @return the StringBuilder
   */
  private StringBuilder createSpace(StringBuilder result, int leastPitch, int width) {
    String theNote;
    for (int c = leastPitch; c <= leastPitch + width; c = c + 1) {
      theNote = Pitch.toString(c % 12) + c / 12;
      result.append(String.format("%-5s", theNote));
    }
    return result;
  }

  /**
   * Creates the space for the Notes.
   *
   * @param result              the StringBuilder
   * @param maxNumberTimeSpaces the digit length of the Notes
   * @return the StringBuilder
   */
  private StringBuilder addNotes(StringBuilder result, int maxNumberTimeSpaces) {
    for (int c = 0; c < model.getLengthOfMusic(); c = c + 1) {
      result.append(String.format("\n%" + maxNumberTimeSpaces + "d ", c)).append(doTheLines(c,
              maxNumberTimeSpaces));
    }
    return result;
  }

  /**
   * Put the notes into the right indices.
   *
   * @param beat   the current line
   * @param offset the offset based on how many digits the beat is
   * @return the StringBuilder
   */
  private StringBuilder doTheLines(int beat, int offset) {
    int setIt = (5 * model.getWidthOfMusic()) + offset + 1;
    StringBuilder result = new StringBuilder(String.format("%" + setIt + "s", ""));
    for (Note note : model.getMusicalValues()) {
      int index = 5 * (note.getPitchOctave() - model.getLeastNote().getPitchOctave()) + 1;
      if (note.getStartBeat() == beat) {
        result.setCharAt(index, 'X');
      } else if (beat > note.getStartBeat() && beat < note.endsAtBeat()) {
        result.setCharAt(index, '|');
      }
    }
    return result;
  }

}
